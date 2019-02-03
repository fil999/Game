package edu.ssu.netcracker.course.fil;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Image;

import java.io.File;
import java.io.Serializable;

/**
 * Created by --- on 11.12.2018.
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

    @Override
    public String toString() {
        return String.valueOf(number) + String.valueOf(suit);
    }
}

/*public class Card {


    private static final String path = "ui\\src\\main\\resources\\images\\";

    private String face;
    private char suit;
    private int number;

    public Card() {
    }

    public Card(String face, char suit, int number){
        this.face = face;
        this.suit = suit;
        this.number = number;
    }

    public Image image(){
        Image imageCard = new Image(null, new FileResource(new File(path + face + "\\" + number + suit + ".png")));
        imageCard.setWidth("80px");
        imageCard.addStyleName("card");
        return imageCard;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object object){
        Card card = (Card) object;
        if ((card.getNumber() == number) && (card.getSuit() == suit)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(number) + String.valueOf(suit);
    }
}*/
