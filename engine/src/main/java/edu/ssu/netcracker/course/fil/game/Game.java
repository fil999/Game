package edu.ssu.netcracker.course.fil.game;

import edu.ssu.netcracker.course.fil.service.GameService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by --- on 18.12.2018.
 */
public class Game{

    private long id;
    private List<Card> cards;           //колода
    private Card trump;                 //козырь
    private int count;                  //количество игроков
    private List<PlayerGame> players;   //игроки
    private List<PlayerGame> exitplayer;
    private int fighting;               //кто отбивается
    private List<Card> onTable;         //карты на столе
    private Card uncovered;             //непокрытая карта
    private int uncoveredNum;
    private int exit;                   //количество ответивших
    private boolean friend;
    private boolean canToss;

    public Game(long id, int count, boolean friend){
        this.id = id;
        this.count = count;
        this.friend = friend;
        cards = new LinkedList<>();
        trump = null;
        players = new ArrayList<>();
        exitplayer = new ArrayList<>();
        fighting = new Random().nextInt(count);
        onTable = new ArrayList<>();
        uncovered = null;
        uncoveredNum = 0;
        exit = 0;
        canToss = true;
        mixed();
        setTrump();
    }

    public long getId() {
        return id;
    }

    public void addPlayer(PlayerGame playerGame){
        players.add(playerGame);
    }

    public int getCountPlayers(){
        return players.size();
    }

    public int getCount(){
        return count;
    }

    public List<PlayerGame> getPlayers() {
        return players;
    }

    private List<Card> createcards(){
        List<Card> cards1 = new ArrayList<>();
        synchronized (cards1) {
            char suit;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        suit = 'b';
                        break;
                    case 1:
                        suit = 'c';
                        break;
                    case 2:
                        suit = 'k';
                        break;
                    case 3:
                        suit = 'p';
                        break;
                    default:
                        suit = 'p';
                }
                for (int j = 6; j < 15; j++) {
                    cards1.add(new Card(j, suit));
                }
            }
        }
        return cards1;
    }

    public void mixed(){
        List<Card> cardsArray = createcards();
        synchronized (cards) {
            Random random = new Random();
            for (int i = 36; i > 0; i--) {
                cards.add(cardsArray.remove(random.nextInt(i)));
            }
        }
    }

    private void sendCountCards(PlayerGame playerGame){
        for (int i = 0; i < players.size(); i++){
            try {
                players.get(i).getOutputStream().write(10);
                players.get(i).getOutputStream().write(playerGame.getNum());
                players.get(i).getOutputStream().write(playerGame.getCards().size());
                players.get(i).getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Card> requestCards(int count, long idPlayer) {
        PlayerGame playerGame = getPlayer(idPlayer).getPlayerGame();
        List<Card> cards1 = new ArrayList<>();
        synchronized (cards) {
            int c = Math.min(count, cards.size());
            for (int i = 0; i < c; i++) {
                cards1.add(cards.remove(0));
            }
            playerGame.addCards(cards1);
            /*for (int i = 0; i < players.size(); i++){
                try {
                    players.get(i).getOutputStream().write(10);
                    players.get(i).getOutputStream().write(playerGame.getNum());
                    players.get(i).getOutputStream().write(playerGame.getCards().size());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }*/
            sendCountCards(playerGame);
        }
        return cards1;
    }

    private void setTrump() {
        synchronized (cards) {
            this.trump = cards.remove(0);
        }
    }

    public Card requestTrump(){
        return trump;
    }

   /* public boolean isCover(Card under, Card over){
        if (over.getSuit() == trump.getSuit()){
            if (under.getNumber() < over.getNumber()){
                return true;
            }
            if (under.getSuit() != trump.getSuit()){
                return true;
            }
        }
        if (over.getSuit() == under.getSuit() && over.getNumber() > under.getNumber()){
            return true;
        }
        return false;
    }

    private boolean haveNumber(List<Card> array, Card card){
        int number = card.getNumber();
        for (Card card1: array){
            if (card1.getNumber() == number){
                return true;
            }
        }
        return false;
    }

    public boolean isPut(List<Card> alreadyOn, Card wantsPut){
        return haveNumber(alreadyOn, wantsPut);
    }*/

    private DataPlayer getPlayer(long id){
        for (int i = 0; i < players.size(); i++){
            if (id == players.get(i).getId()){
                return new DataPlayer(players.get(i), i);
            }
        }
        return null;
    }

    public void startGame(){
        try {
            OutputStream stream = players.get((fighting-1+count)%count).getOutputStream();
            stream.write(2);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean canPut(Card card){      //можно ли положить карту
        int number = card.getNumber();
        for (Card card1: onTable){
            if (card1.getNumber() == number){
                return true;
            }
        }
        return false;
    }

    private void sendCard(Card card, int number, int whatDo){       //отправить карту всем
        for (PlayerGame playerGame : players){
            try {
                OutputStream stream = playerGame.getOutputStream();
                stream.write(1);
                stream.write((int)card.getSuit());
                stream.write(card.getNumber());
                stream.write(number);
                stream.write(whatDo);
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void canBeCovered(){    //можно крыть
        for (int i = 0; i < players.size(); i++){
            PlayerGame playerGame = players.get(i);
            try {
                OutputStream stream = playerGame.getOutputStream();
                if (playerGame.getNum() == fighting){
                    stream.write(4);
                } else {
                    stream.write(6);
                }
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exitPlayer(PlayerGame playerGame){
        synchronized (players) {
            if (playerGame.getCards().isEmpty() && cards.isEmpty()) {
                playerGame.setNumberExit(0);
                players.remove(playerGame);
                exitplayer.add(playerGame);
                count--;
                try {
                    playerGame.getOutputStream().write(8);
                    playerGame.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean putCard(Card card, long idPlayer){      //пойти/подбросить
        synchronized (onTable) {
            if (canToss) {
                if (onTable.size() < 12) {
                    if (onTable.isEmpty() || canPut(card)) {
                        DataPlayer dataPlayer = getPlayer(idPlayer);
                        PlayerGame playerGame = dataPlayer.getPlayerGame();
                        playerGame.deleteCard(card);
                        onTable.add(card);
                        uncovered = card;
                        exit = 0;
                        sendCard(card, dataPlayer.getIndex(), 0);
                        exitPlayer(playerGame);
                        canBeCovered();
                        canToss = false;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isCover(Card card){     //можно ли покрыть
        if (card.getSuit() == trump.getSuit()){
            if (uncovered.getNumber() < card.getNumber()){
                return true;
            }
            if (uncovered.getSuit() != trump.getSuit()){
                return true;
            }
        }
        if (card.getSuit() == uncovered.getSuit() && card.getNumber() > uncovered.getNumber()){
            return true;
        }
        return false;
    }

    private void canToss(){     //возможность подкидывать
        for (int i = 0; i < players.size(); i++){
            PlayerGame playerGame = players.get(i);
            try {
                OutputStream stream = playerGame.getOutputStream();
                canToss = true;
                if (playerGame.getNum() == fighting){
                    stream.write(6);
                } else {
                    stream.write(2);
                }
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean cover(Card card, long idPlayer){        //покрыть
        synchronized (onTable) {
            if (isCover(card)) {
                PlayerGame playerGame = getPlayer(idPlayer).getPlayerGame();
                playerGame.deleteCard(card);
                onTable.add(card);
                canToss = true;
                if (uncoveredNum > 0) {
                    int index = onTable.indexOf(uncovered);
                    if (index < uncoveredNum) {
                        uncovered = onTable.get(++index);
                        if (index == 1) {
                            sendCard(card, fighting, 3);
                        } else {
                            sendCard(card, fighting, 2);
                        }
                        return true;
                    } else {
                        uncoveredNum = 0;
                        /*uncovered = null;
                        sendCard(card, fighting, 2);
                        exitPlayer(playerGame);
                        exitGame();
                        canToss();
                        return true;*/
                    }
                }
                uncovered = null;
                sendCard(card, fighting, 0);
                exitPlayer(playerGame);
                exitGame();
                canToss();
                return true;

            }
            return false;
        }
    }

    private void exitGame() {
        synchronized (players) {
            if (players.size() < 2) {
                if (players.size() == 1) {
                    PlayerGame playerGame = players.get(0);
                    playerGame.setNumberExit(1);
                    players.remove(playerGame);
                    exitplayer.add(playerGame);
                    try {
                        playerGame.getOutputStream().write(9);
                        playerGame.getSocket().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                PlayerGame player = null;
                for (PlayerGame playerGame1 : exitplayer){
                    if (playerGame1.getNumberExit() == 1){
                        player = playerGame1;
                        break;
                    }
                }
                int num = exitplayer.size();
                Requests requests = new Requests(friend);
                if (player == null){
                    requests.rezult(exitplayer.get(num-1).getId(), exitplayer.get(num-2).getId(), 0);
                    requests.rezult(exitplayer.get(num-2).getId(), exitplayer.get(num-1).getId(), 0);
                    num -= 2;
                } else {
                    requests.rezult(exitplayer.get(num-1).getId(), exitplayer.get(0).getId(), 2);
                    num--;
                }
                player = exitplayer.get(num-1);
                for (int i = 0; i < num; i++){
                    requests.rezult(exitplayer.get(i).getId(), player.getId(), 1);
                }
                new GameService().exitGame(this);
            }
        }

    }

    private void transitionFighting(boolean flag){      //переход хода
        onTable = new ArrayList<>();
        uncovered = null;
        uncoveredNum = 0;
        canToss = true;
        if (players.isEmpty() || players.size() == 1){
            exitGame();
        }
        if (flag){          //если предыдущий раскрылся
            fighting = (fighting + 1) % count;
        } else {            //если взял
            fighting = (fighting + 2) % count;
        }
        for (int i = 0; i < players.size(); i++){
            PlayerGame playerGame = players.get(i);
            try {
                OutputStream stream = playerGame.getOutputStream();
                stream.write(7);
                if (playerGame.getNum() == ((fighting - 1 + count) % count)) {
                    stream.write(2);
                } else {
                    stream.write(6);
                }
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Card> takeCards(long idPlayer){    //взять карты
        PlayerGame playerGame = getPlayer(idPlayer).getPlayerGame();
        playerGame.addCards(onTable);
        List<Card> cards = onTable;
        sendCountCards(playerGame);
        transitionFighting(false);
        return cards;
    }

    public void setExit(){      //сигнал бито
        exit++;
        if (exit + 1 == count){
            transitionFighting(true);
        }
    }

    public boolean transfer(Card card, long idPlayer) {
        if (card.getNumber() == onTable.get(0).getNumber()) {
            if (players.get((fighting + 1) % count).getCards().size() > onTable.size()) {
                fighting = (fighting + 1) % count;
                DataPlayer dataPlayer = getPlayer(idPlayer);
                PlayerGame playerGame = dataPlayer.getPlayerGame();
                playerGame.deleteCard(card);
                onTable.add(card);
                uncoveredNum++;
                exit = 0;
                sendCard(card, dataPlayer.getIndex(), 1);
                exitPlayer(playerGame);
                canBeCovered();
                return true;
            }
        }
        return false;
    }


    private class DataPlayer{
        private PlayerGame playerGame;
        private int index;

        public DataPlayer(PlayerGame playerGame, int index) {
            this.playerGame = playerGame;
            this.index = index;
        }

        public PlayerGame getPlayerGame() {
            return playerGame;
        }

        public int getIndex() {
            return index;
        }
    }

}