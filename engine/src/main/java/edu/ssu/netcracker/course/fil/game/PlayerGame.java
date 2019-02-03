package edu.ssu.netcracker.course.fil.game;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 06.01.2019.
 */
public class PlayerGame {
    private long id;
    private int numberExit;
    private Socket socket;
    private List<Card> cards;
    private int num;

    public PlayerGame(long id, Socket socket, int num) {
        this.id = id;
        this.socket = socket;
        this.num = num;
        this.cards = new ArrayList<>();
    }

    public int getNum() {
        return num;
    }

    public int getNumberExit() {
        return numberExit;
    }

    public void setNumberExit(int numberExit) {
        this.numberExit = numberExit;
    }

    public long getId() {
        return id;
    }



    public Socket getSocket() {
        return socket;
    }

    public List<Card> getCards() {
        return cards;
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void deleteCard(Card card){
        cards.remove(cards.indexOf(card));
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void addCards(List<Card> cardList){
        for (Card card : cardList){
            if (card != null){
                cards.add(card);
            }
        }
    }
}
