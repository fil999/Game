package edu.ssu.netcracker.course.fil.game;

import java.io.Serializable;

/**
 * Created by --- on 18.12.2018.
 */
public class Card implements Serializable {

    private int number;
    private char suit;

    public Card(int number, char suit){
        this.number = number;
        this.suit = suit;
    }

    public Card() {
    }


    public int getNumber() {
        return number;
    }

    public char getSuit() {
        return suit;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object object){
        Card card = (Card) object;
        if ((card.getNumber() == number) && (card.getSuit() == suit)){
            return true;
        }
        return false;
    }
}
