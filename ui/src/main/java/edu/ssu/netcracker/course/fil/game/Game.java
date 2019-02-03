package edu.ssu.netcracker.course.fil.game;

import edu.ssu.netcracker.course.fil.Card;
import edu.ssu.netcracker.course.fil.entity.Player;
import edu.ssu.netcracker.course.fil.view.GameView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by --- on 12.01.2019.
 */
public class Game {

    private long idPlayer;      //игрок
    private long idGame;
    private boolean transfer;
    private GameView gameView;
    private boolean canCovered; //можно крыть
    private boolean canToss;    //можно подкидывать
    private List<Card> cards;
    private boolean startGame;
    private int myNum;
    private int count;
    private boolean first;

    private String[] places;
    private int numberPlace;


    public Game(long idPlayer, boolean transfer) {
        this.idPlayer = idPlayer;
        this.idGame = -1;
        this.transfer = transfer;
        this.canCovered = false;
        this.canToss = false;
        this.startGame = false;
        cards = new ArrayList<>();
        numberPlace = 0;
        first = true;
        createPlaces();
    }


    private void createPlaces(){
        places = new String[12];
        int top = 200;
        for (int i = 0; i < 2; i++){
            int left = 300;
            for (int j = 0; j < 3; j++){
                int below = 0;
                for (int k = 0; k < 2; k++) {
                    places[i * 6 + j*2 + k] = "left:" + left + "px; top:" + (top + below) + "px";
                    below += 30;
                }
                left += 150;
            }
            top += 200;
        }
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String nextPlaces(){
        return places[numberPlace++];
    }

    public void beginPlaces(){
        numberPlace = 0;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public int getMyNum() {
        return myNum;
    }

    public void setMyNum(int myNum) {
        this.myNum = myNum;
    }

    public void cardOnTable(Card card, int num, int whatDo){
        first = false;
        if (whatDo == 1){
            numberPlace++;
        }
        if (whatDo == 3){
            numberPlace = 0;
            numberPlace++;
        }
        gameView.cardOnTable(card, num, nextPlaces());
        if (whatDo > 1){
            numberPlace++;
        }
    }

    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {
        this.idGame = idGame;
    }

    public long getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public boolean isCanCovered() {
        return canCovered;
    }

    public void setCanCovered(boolean canCovered) {
        this.canCovered = canCovered;
        while (gameView == null) {

        }
        if (canCovered) {
            canToss = false;
            gameView.isCanCovered();
        } else {

        }
    }

    public boolean isCanToss() {
        return canToss;
    }

    public void setCanToss(boolean canToss) {
        this.canToss = canToss;
        while (gameView == null){

        }
        if (canToss) {
            canCovered = false;
            gameView.isCatToss();
        }
    }

    public void waiting(){
        this.canToss = false;
        this.canCovered = false;
        gameView.waiting();
    }

    public void isExit(){
        first = true;
        gameView.isExit();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int countCards(){
        return cards.size();
    }

    public void addCards(List<Card> cards1){
        cards.addAll(cards1);
    }

    public void addCard(Card card){
        synchronized (cards) {
            cards.add(card);
        }
    }

    public void deleteCard(Card card) {
        synchronized (cards) {
            cards.remove(cards.indexOf(card));
        }
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
        while (gameView == null){

        }
        gameView.setOtherPlayers(count-1);
        gameView.waiting();

    }

    public boolean isStartGame(){
        while(!startGame){
        }
        return true;
    }

    public void win(){
        gameView.win();
    }

    public void lose(){
        gameView.lose();
    }

    public void updateOtherCards(int num, int countNum){
        gameView.updateOtherCards(num, countNum);
    }
}
