package edu.ssu.netcracker.course.fil.view;

import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import edu.ssu.netcracker.course.fil.Card;
import edu.ssu.netcracker.course.fil.data.DataPlayer;
import edu.ssu.netcracker.course.fil.entity.Back;
import edu.ssu.netcracker.course.fil.entity.Face;
import edu.ssu.netcracker.course.fil.entity.GamingTable;
import edu.ssu.netcracker.course.fil.game.Game;
import edu.ssu.netcracker.course.fil.game.GameRequest;
import edu.ssu.netcracker.course.fil.ui.MainUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 11.12.2018.
 */

public class GameView extends VerticalLayout implements View{
    public static final String NAME = "game";
    private static final String path = "ui\\src\\main\\resources\\images\\";
    private String face = "faceDefault\\";
    private String back = "backDefault.png";
    private Game game;
    private DataPlayer player;
    private GameRequest gameRequest;
    private AbsoluteLayout absoluteLayout = new AbsoluteLayout();
    private Button buttonPlayer;
    private Button transferButton;
    private boolean transfer;
    private List<Image> playerCards;
    private List<Image> cardsOnTable;
    private int[] otherPlayer = new int[3];
    private List<Image>[] otherCards = new ArrayList[3];
    private UI ui;


    public GameView(){
        ui = UI.getCurrent();
        Page.getCurrent().setTitle("Игра");
        game = (Game)VaadinRequest.getCurrent().getAttribute("game");
        player = (DataPlayer)VaadinRequest.getCurrent().getAttribute("player");
        transfer = false;
        for (int i = 0; i < 3; i++){
            otherPlayer[i] = 0;
            otherCards[i] = new ArrayList<>();
        }

        absoluteLayout.setStyleName("back");
        absoluteLayout.setWidth("1024px");
        absoluteLayout.setHeight("768px");
        Image background = new Image();
        if (player!= null) {
            if (!player.isLoged() || player.getPlayer().getPlayerTable() == 0) {
                background.setSource(new FileResource(new File(path + "tabledefault.jpg")));
            } else {
                int index = player.getPlayer().getTables().indexOf(new GamingTable(player.getPlayer().getPlayerTable()));
                if (index != -1) {
                    GamingTable table = player.getPlayer().getTables().get(index);
                    background.setSource(new FileResource(new File(path + table.getPatch())));
                }
            }
            if (player.isLoged()) {
                if (player.getPlayer().getPlayerFace() != 0) {
                    int index = player.getPlayer().getFaces().indexOf(new Face(player.getPlayer().getPlayerFace()));
                    if (index != -1) {
                        Face face1 = player.getPlayer().getFaces().get(index);
                        face = face1.getPatch() + "\\";
                    }
                }
                if (player.getPlayer().getPlayerBack() != 0) {
                    int index = player.getPlayer().getBacks().indexOf(new Back(player.getPlayer().getPlayerBack()));
                    if (index != -1) {
                        Back back1 = player.getPlayer().getBacks().get(index);
                        back = back1.getPatch();
                    }
                }
            }
        }
        absoluteLayout.addComponent(background);

        Image exit = new Image(null, new FileResource(new File(path + "exit.png")));
        exit.addStyleName("clickable");
        exit.addClickListener(clickEvent ->
                MainUI.getCurrent().getNavigator().navigateTo(MainView.NAME)
        );
        absoluteLayout.addComponent(exit, "top:20px; right:20px");

        addComponent(absoluteLayout);
        setComponentAlignment(absoluteLayout, Alignment.TOP_CENTER);

        if (game != null) {
            allData();
        }
    }


    private void allData(){
        game.setGameView(this);
        cardsOnTable = new ArrayList<>();
        playerCards = new ArrayList<>();
        buttonPlayer = new Button();
        buttonPlayer.addClickListener(new ButtonPlayerListener());
        buttonPlayer.addStyleName("clickable");
        if (game.isTransfer()) {
            transferButton = new Button("Перевести");
            transferButton.addStyleName("clickable");
            transferButton.addClickListener(new TransferListener());
            absoluteLayout.addComponent(transferButton, "bottom:80px; right:50px");
        }

        gameRequest = new GameRequest(game.getIdGame());
        Card trump = gameRequest.requestTrump();
        Image trump1 = new Image(null, new  FileResource(new File(path + face+ trump.toString()+".png")));
        trump1.setWidth("70px");
        absoluteLayout.addComponent(trump1, "top:100px; right:20px");

        Image trump2 = new Image(null, new  FileResource(new File( path + face + trump.toString()+".png")));
        trump2.setWidth("70px");
        trump2.addStyleName("rotate");
        absoluteLayout.addComponent(trump2, "top:100px; right:140px");
        Image koloda = new Image(null, new  FileResource(new File(path + back)));
        koloda.setWidth("80px");
        absoluteLayout.addComponent(koloda, "top:100px; right:120px");

        absoluteLayout.addComponent(buttonPlayer, "bottom:20px; right:50px");

        List<Card> cardPlayer = gameRequest.requestCards(6, game.getIdPlayer());
        game.addCards(cardPlayer);
        showCardsPlayer();
        //  player2();
        //  player3();
        //  player4();
    }

    public void setOtherPlayers(int count){
        for (int i = 0; i < count; i++){
            otherPlayer[i] = 6;
        }
    }

    public void isCatToss(){
        ui.access(()-> {
            while (buttonPlayer == null) {

            }
            buttonPlayer.setVisible(true);
            buttonPlayer.setCaption("Бито");

        });
    }

    public void isCanCovered() {
        ui.access(() -> {
            while (buttonPlayer == null) {

            }
            transferButton.setVisible(true);
            transferButton.setCaption("Перевести");
            buttonPlayer.setVisible(true);
            buttonPlayer.setCaption("Взять");
        });
    }

    public void waiting() {
        ui.access(() -> {
            while (buttonPlayer == null || transferButton == null) {

            }
            transfer = false;
            transferButton.setVisible(false);
            buttonPlayer.setVisible(false);
        });
    }


    private void showCardsPlayer(){
        ui.access(()->{
            deleteCards(playerCards);
            playerCards.clear();
            int left = 1024/2 - ((game.countCards() + 1)* 30) / 2 - 100;
            List<Card> cardList = game.getCards();
            synchronized (playerCards) {
                for (Card card : cardList) {
                    Image imageCard = new Image(null, new FileResource(new File(path + face + card.getNumber() + card.getSuit() + ".png")));
                    imageCard.setWidth("80px");
                    imageCard.addStyleName("card");
                    imageCard.addClickListener(new CardListener(card, imageCard));
                    playerCards.add(imageCard);
                    absoluteLayout.addComponent(imageCard, "bottom:20px; left:" + left + "px");
                    left += 30;
                }
            }
        });

    }

    private void player2(){
        ui.access(()-> {
            deleteCards(otherCards[0]);
            otherCards[0].clear();
            int left = 1024 / 2 - ((otherPlayer[0] + 1) * 20) / 2 - 100;
            for (int i = 0; i < otherPlayer[0]; i++) {
                Image card = new Image(null, new FileResource(new File(path + back)));
                card.setWidth("60px");
                otherCards[0].add(card);
                absoluteLayout.addComponent(card, "top:20px; left:" + left + "px");
                left += 20;
            }
        });
    }
    private void player3(){
        ui.access(()-> {
            deleteCards(otherCards[1]);
            otherCards[1].clear();
            int top = 1024 / 2 - ((otherPlayer[1] + 1) * 20) / 2 - 100;
            for (int i = 0; i < otherPlayer[1]; i++) {
                Image card = new Image(null, new FileResource(new File(path + "backDefaultRotate.png")));
                card.setWidth("80px");
                otherCards[1].add(card);
                absoluteLayout.addComponent(card, "left:20px; top:" + top + "px");
                top += 20;
            }
        });
    }

    private void player4(){
        ui.access(()-> {
            deleteCards(otherCards[2]);
            otherCards[2].clear();
            int top = 1024 / 2 - ((otherPlayer[2] + 1) * 20) / 2 - 100;
            for (int i = 0; i < otherPlayer[2]; i++) {
                Image card = new Image(null, new FileResource(new File(path + "backDefaultRotate.png")));
                card.setWidth("80px");
                otherCards[2].add(card);
                absoluteLayout.addComponent(card, "right:20px; top:" + top + "px");
                top += 20;
            }
        });
    }

    public void cardOnTable(Card card, int num, String place){
        ui.access(()->{
            synchronized (cardsOnTable) {
                Image imageCard = new Image(null, new FileResource(new File(path + face + card.getNumber() + card.getSuit() + ".png")));
                imageCard.setWidth("80px");
                absoluteLayout.addComponent(imageCard, place);
                cardsOnTable.add(imageCard);
                int n = (num + game.getCount() - game.getMyNum())%game.getCount();
                if (n != 0){
                    absoluteLayout.removeComponent(otherCards[n-1].remove(0));
                    otherPlayer[n-1]--;
                }

            }
        });
    }

    private void updateCards(int num){
        switch (num){
            case 0:
                player2();
                break;
            case 1:
                player3();
                break;
            case 2:
                player4();
                break;
        }
    }


    public void updateOtherCards(int num, int countNum){
        int n = (num + game.getCount() - game.getMyNum()) % game.getCount();
        if (n != 0){
            otherPlayer[n-1] = countNum;
            updateCards(n-1);
        }
        // updateCards(n-1);
    }

    public void deleteCards(List<Image> cardList){
        synchronized (cardList) {
            for (Image image : cardList) {
                absoluteLayout.removeComponent(image);
            }
        }
    }


    public void isExit() {
        ui.access(() -> {
            deleteCards(cardsOnTable);
            cardsOnTable.clear();
            game.beginPlaces();
            int countCards = game.countCards();
            if (countCards < 6) {
                List<Card> cardList = gameRequest.requestCards(6 - countCards, game.getIdPlayer());
                game.addCards(cardList);
            }
            showCardsPlayer();
        });
    }

    public void win() {
        ui.access(() -> {
            deleteCards(playerCards);
            Image win = new Image(null, new FileResource(new File(path + "win.png")));
            absoluteLayout.addComponent(win, "bottom:100px; left:" + 500 + "px");
        });
    }

    public void lose() {
        ui.access(() -> {
            deleteCards(playerCards);
            Image lose = new Image(null, new FileResource(new File(path + "lose.png")));
            absoluteLayout.addComponent(lose, "bottom:100px; left:" + 500 + "px");
        });
    }


    private class CardListener implements MouseEvents.ClickListener {

        private Card card;
        private Image image;

        public CardListener(Card card, Image image) {
            this.card = card;
            this.image = image;
        }

        @Override
        public void click(MouseEvents.ClickEvent clickEvent) {
            if (transfer){
                if (gameRequest.transfer(card, game.getIdPlayer())) {
                    Image cardImage = playerCards.get(playerCards.indexOf(image));
                    absoluteLayout.removeComponent(cardImage);
                    //cardsOnTable.add(cardImage);
                    game.deleteCard(card);
                    //absoluteLayout.addComponent(cardImage, game.nextPlaces());
                }
            } else {
                if (game.isCanCovered()) {
                    if (gameRequest.cover(card, game.getIdPlayer())) {
                        Image cardImage = playerCards.get(playerCards.indexOf(image));
                        absoluteLayout.removeComponent(cardImage);
                        //cardsOnTable.add(cardImage);
                        game.deleteCard(card);
                        //absoluteLayout.addComponent(cardImage, game.nextPlaces());
                    }
                } else if (game.isCanToss()) {
                    if (gameRequest.putCard(card, game.getIdPlayer())) {
                        Image cardImage = playerCards.get(playerCards.indexOf(image));
                        absoluteLayout.removeComponent(cardImage);
                        //cardsOnTable.add(cardImage);
                        game.deleteCard(card);
                        //absoluteLayout.addComponent(cardImage, game.nextPlaces());
                    }

                }
            }
        }
    }

    private class ButtonPlayerListener implements  Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            String buttonName = clickEvent.getButton().getCaption();
            if (buttonName.equals("Бито")){
                if (!cardsOnTable.isEmpty()) {
                    gameRequest.setExit();
                }
            } else if (buttonName.equals("Взять")){
                List<Card> cardList = gameRequest.takeCards(game.getIdPlayer());
                game.addCards(cardList);
                showCardsPlayer();
            }
        }
    }


    private class TransferListener implements  Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            String buttonName = clickEvent.getButton().getCaption();
            if (buttonName.equals("Перевести")){
                transfer = true;
                clickEvent.getButton().setCaption("Покрыть");
            } else {
                transfer = false;
                clickEvent.getButton().setCaption("Перевести");
            }
        }
    }

  /*  private void player2(){
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

    }*/
}
