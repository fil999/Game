package edu.ssu.netcracker.course.fil.view;

import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import edu.ssu.netcracker.course.fil.Card;
import edu.ssu.netcracker.course.fil.ui.MainUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 11.12.2018.
 */
public class GameView extends VerticalLayout implements View {
    public static final String NAME = "game";
    private static final String path = "ui\\src\\main\\resources\\images\\";
    AbsoluteLayout absoluteLayout = new AbsoluteLayout();

    public GameView(){

        Page.getCurrent().setTitle("Игра");

        absoluteLayout.setStyleName("back");
        absoluteLayout.setWidth("1024px");
        absoluteLayout.setHeight("768px");
        absoluteLayout.addComponent(new Image(null, new FileResource(new File(path + "tabledefault.jpg"))));

        Image exit = new Image(null, new FileResource(new File(path + "exit.png")));
        exit.addStyleName("clickable");
        exit.addClickListener(clickEvent ->
                MainUI.getCurrent().getNavigator().navigateTo(MainView.NAME)
        );
        absoluteLayout.addComponent(exit, "top:20px; right:20px");
        Image trump = new Image(null, new  FileResource(new File(path + "faceDefault\\10c.png")));
        trump.setWidth("70px");
        absoluteLayout.addComponent(trump, "top:100px; right:20px");

        Image trump2 = new Image(null, new  FileResource(new File( path + "faceDefault\\10c.png")));
        trump2.setWidth("70px");
        trump2.addStyleName("rotate");
        absoluteLayout.addComponent(trump2, "top:100px; right:140px");
        Image koloda = new Image(null, new  FileResource(new File(path + "koloda.png")));
        koloda.setWidth("80px");
        absoluteLayout.addComponent(koloda, "top:100px; right:120px");

        List<Card> player = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            player.add(new Card("faceDefault", "c", 8));
            player.add(new Card("faceDefault", "p", 12));
            player.add(new Card("faceDefault", "b", 7));
            player.add(new Card("faceDefault", "k", 14));
            player.add(new Card("faceDefault", "b", 9));
            player.add(new Card("faceDefault", "p", 11));
            player.add(new Card("faceDefault", "c", 6));
            player.add(new Card("faceDefault", "k", 7));
        }


        int left = 1024/2 - ((player.size() + 1)* 30) / 2 - 100;
        for (Card card: player) {
            absoluteLayout.addComponent(card.image(), "bottom:20px; left:" + left + "px");
            left += 30;
        }


        player2();
        player3();
        player4();
        game();

        addComponent(absoluteLayout);
        setComponentAlignment(absoluteLayout, Alignment.TOP_CENTER);

    }


    private void player2(){
        int top = 768/2 - ((20 + 1)* 20) / 2 + 100;
        for (int i = 0; i < 20; i++){
            Image card = new Image(null, new  FileResource(new File(path + "backDefaultRotate.png")));
            card.setWidth("80px");
            absoluteLayout.addComponent(card, "left:20px; top:" + top + "px");
            top += 20;
        }
    }

    private void player3(){
        int left = 1024/2 - ((20 + 1)* 20) / 2 - 100;
        for (int i = 0; i < 20; i++){
            Image card = new Image(null, new  FileResource(new File(path + "backDefault.png")));
            card.setWidth("60px");
            absoluteLayout.addComponent(card, "top:20px; left:" + left + "px");
            left += 20;
        }

        Image text = new Image(null, new  FileResource(new File(path + "text.png")));
        absoluteLayout.addComponent(text, "top:80px; left:" + (left + 50) + "px");

    }

    private void player4(){
        int top = 768/2 - ((20 + 1)* 20) / 2 + 100;
        for (int i = 0; i < 20; i++){
            Image card = new Image(null, new  FileResource(new File(path + "backDefaultRotate.png")));
            card.setWidth("80px");
            absoluteLayout.addComponent(card, "right:20px; top:" + top + "px");
            top += 20;
        }
    }

    private void game(){
        int top = 200;
        for (int i = 0; i < 2; i++){
            int left = 300;
            for (int j = 0; j < 3; j++){
                Image card = new Image(null, new  FileResource(new File(path + "faceDefault\\12c.png")));
                card.setWidth("80px");
                absoluteLayout.addComponent(card, "left:" + left + "px; top:" + top + "px");
                Image card1 = new Image(null, new  FileResource(new File(path + "faceDefault\\12c.png")));
                card1.setWidth("80px");
                absoluteLayout.addComponent(card1, "left:" + left + "px; top:" + (top + 30) + "px");
                left += 150;
            }
            top += 200;
        }

    }
}
