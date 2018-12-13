package edu.ssu.netcracker.course.fil;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Image;

import java.io.File;

/**
 * Created by --- on 11.12.2018.
 */
public class Card {


    private static final String path = "ui\\src\\main\\resources\\images\\";

    private String face;
    private String suit;
    private int number;

    public Card(String face, String suit, int number){
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


}
