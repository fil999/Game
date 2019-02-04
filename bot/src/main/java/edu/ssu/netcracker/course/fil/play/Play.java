package edu.ssu.netcracker.course.fil.play;

import edu.ssu.netcracker.course.fil.forgame.Card;
import edu.ssu.netcracker.course.fil.request.Requests;
import edu.ssu.netcracker.course.fil.service.BotService;
import edu.ssu.netcracker.course.fil.socket.BotGameSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by --- on 03.02.2019.
 */
public class Play {

    private long idGame;
    protected long idPlayer;
    private BotGameSocket botGameSocket;
    protected Requests requests;
    private Random random;

    protected Card trump;
    protected List<Card> cards;
    protected List<Card> cardsOnTable;
    protected int countTransfer;
    private boolean canToss;

    public Play(long idGame) {
        this.idGame = idGame;
        cardsOnTable = new ArrayList<>();
        canToss = false;
        botGameSocket = new BotGameSocket(false, idGame, this);
        requests = new Requests(idGame);
        random = new Random();
        countTransfer = 0;
        new Thread(botGameSocket).start();
    }

    public void setIdPlayer(long idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void startGame(){
        trump = requests.requestTrump();
        cards = requests.requestCards(6, idPlayer);
    }

    public void cardOnTable(Card card, int whatDo){
        if (whatDo == 1){
            countTransfer++;
        }
        cardsOnTable.add(card);
    }

    public void waiting(){
        canToss = false;
    }

    protected void sleep(){
        int rand = random.nextInt(5) + 2;
        try {
            TimeUnit.SECONDS.sleep(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Card firstToss(){
        Card min = cards.get(0);
        for (Card card : cards){
            if ((card.getNumber() <= min.getNumber()) && (card.getSuit() != trump.getSuit())){
                min = card;
            }
        }
        if (min.getSuit() != trump.getSuit()){
            return min;
        } else {
            for (Card card : cards){
                if ((card.getNumber() <= min.getNumber())){
                    min = card;
                }
            }
        }
        return min;
    }

    private Card nextToss(){
        Card min = null;
        for (Card card : cards){
            if (card.getSuit() != trump.getSuit()){
                for (Card onTable : cardsOnTable){
                    if (card.getNumber() == onTable.getNumber()){
                        if (min == null){
                            min = card;
                        } else {
                            if (card.getNumber() < min.getNumber()){
                                min = card;
                            }
                        }
                    }
                }
            }
        }
        return min;
    }

    public void setCanToss(){
        //sleep();
        canToss = true;
        if (cardsOnTable.isEmpty()){
            Card card = firstToss();
            requests.putCard(card, idPlayer);
            cards.remove(card);
            //cardsOnTable.add(card);
        } else {
            Card card = nextToss();
            if (card == null){
                requests.setExit();
            } else {
                boolean b = requests.putCard(card, idPlayer);
                if (b) {
                    cards.remove(card);
                    //cardsOnTable.add(card);
                }
            }
        }
    }

    protected Card coveredTrump(Card card){
        Card min = null;
        for (Card card1 : cards) {
            if (card1.getSuit() == card.getSuit()){
                if (card1.getNumber() > card.getNumber()){
                    if (min == null){
                        min = card1;
                    } else {
                        if (card1.getNumber() < min.getNumber()){
                            min = card1;
                        }
                    }
                }
            }
        }
        return min;
    }

    protected Card coveredNoTrump(Card card){
        Card min = null;
        Card minTrump = null;
        for (Card card1 : cards){
            if (card1.getSuit() == trump.getSuit()){
                if (minTrump == null){
                    minTrump = card1;
                } else {
                    if (card1.getNumber() < minTrump.getNumber()){
                        minTrump = card1;
                    }
                }
            } else {
                if (card1.getSuit() == card.getSuit()){
                    if (card1.getNumber() > card.getNumber()){
                        if (min == null){
                            min = card1;
                        } else {
                            if (card1.getNumber() < min.getNumber()){
                                min = card1;
                            }
                        }
                    }
                }
            }
        }
        if (min == null){
            return minTrump;
        } else {
            return min;
        }
    }

    protected void covered(Card card){
        //sleep();
        Card min;
        if (card.getSuit() == trump.getSuit()){
            min = coveredTrump(card);
        } else {
            min = coveredNoTrump(card);
        }
        if (min == null){
            List<Card> cards = requests.takeCards(idPlayer);
            cards.addAll(cards);
        } else {
            requests.cover(min, idPlayer);
            cards.remove(min);
          //  cardsOnTable.add(min);
        }

    }

    public void setCanCovered(){
        Card card = cardsOnTable.get(cardsOnTable.size()-1);
        covered(card);
    }

    public void isExit(){
        countTransfer = 0;
        cardsOnTable.clear();
        if (cards.size() < 6){
            List<Card> cardList = requests.requestCards(6 - cards.size(), idPlayer);
            cards.addAll(cardList);
        }
    }

    public void exitGame(){
        new BotService().deleteBot(this);
    }

    @Override
    public boolean equals(Object other){
        Play o = (Play) other;
        if (idGame == o.idGame && idPlayer == o.idPlayer){
            return true;
        } else {
            return false;
        }
    }
}
