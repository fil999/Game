package edu.ssu.netcracker.course.fil.view;

import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import edu.ssu.netcracker.course.fil.MainSocket;
import edu.ssu.netcracker.course.fil.data.DataPlayer;
import edu.ssu.netcracker.course.fil.data.DataRequests;
import edu.ssu.netcracker.course.fil.entity.*;
import edu.ssu.netcracker.course.fil.game.Game;
import edu.ssu.netcracker.course.fil.game.GameRequest;
import edu.ssu.netcracker.course.fil.game.GameSocket;
import edu.ssu.netcracker.course.fil.ui.MainUI;
import org.springframework.hateoas.Resource;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 08.12.2018.
 */
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "";
    private static final String path = "ui\\src\\main\\resources\\images\\";
    private boolean loggedIn = false;

    private AbsoluteLayout absoluteLayout;
    private Image background;
    private DataPlayer player;
    private UI ui;

    private int numberFriend = 0;
    private HorizontalLayout friendLayout = new HorizontalLayout();

    public MainView() {
        ui = UI.getCurrent();
        player = new DataPlayer();
        setSizeFull();
        addStyleName("white");
        absoluteLayout = new AbsoluteLayout();
        absoluteLayout.addStyleName("back");
        absoluteLayout.setWidth("1024px");
        absoluteLayout.setHeight("768px");


        addComponent(absoluteLayout);
        setComponentAlignment(absoluteLayout, Alignment.TOP_CENTER);
        allData();

    }

    private void allData(){
        background = new Image(null, new FileResource(new File(path + "tabledefault.jpg")));
        absoluteLayout.addComponent(background);


        Image settings = new Image(null, new FileResource(new File(path + "settings2.png")));
        settings.addStyleName("clickable");
        settings.setWidth("50");
        settings.addClickListener(new SettingsListener());

        Image play = new Image(null, new FileResource(new File(path + "play.png")));
        play.addStyleName("clickable");
        play.setHeight("50px");
        play.addClickListener(new PlayListener(false));
        Image play1 = new Image(null, new FileResource(new File(path + "play.png")));
        play1.setHeight("50px");
        play1.addStyleName("clickable");
        play1.addClickListener(new PlayListener(true));
        absoluteLayout.addComponent(play, "top:300px; left:300px");
        absoluteLayout.addComponent(play1, "top:300px; left:600px");

        absoluteLayout.addComponent(settings, "top:20px; right:20px");
        loging();
    }


    private void loging(){
        if (player.isLoged()){
            itLogged();
        } else {
            loggedIn = false;
        }
    /*    if (player.isLoged()){
            itLogged();
        } else {
            String idString = Cookies.getCookie("idPlayer");
            if (idString.isEmpty()) {
                loggedIn = false;
            } else {
                itLogged();

            }
        }*/
    }




    private void itLogged(){
        loggedIn = true;
        new Thread(new MainSocket(this, player.getPlayer().getId())).start();
        int index = player.getPlayer().getTables().indexOf(new GamingTable(player.getPlayer().getPlayerTable()));
        if (index != -1){
            GamingTable table = player.getPlayer().getTables().get(index);
            background.setSource(new FileResource(new File(path + table.getPatch())));
        }

        Image shop = new Image(null, new FileResource(new File(path + "shop4.png")));
        shop.addStyleName("clickable");
        shop.setWidth("50");
        shop.addClickListener(new ShopListener());
        absoluteLayout.addComponent(shop, "top:90px; right:20px");

        absoluteLayout.addComponent(friendLayout, "bottom:20px; right:20px");
        friends();

        absoluteLayout.addComponent(playerResult(), "bottom:20px; left:20px");
        absoluteLayout.addComponent(player(), "top:20px; left:20px");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Label(String.valueOf(player.getPlayer().getBalance())));
        horizontalLayout.addComponent(new Image(null, new FileResource(new File(path + "coin.png"))));
        absoluteLayout.addComponent(horizontalLayout, "top:20px; left:400px");

    }


    private void updateData(){
        absoluteLayout.removeAllComponents();
        allData();
    }

   /* private void friends() {
        if (player.getPlayer().getFriends().size() > 0) {
            Button button = new Button("С друзьями");
            button.addStyleName("clickable");
            absoluteLayout.addComponent(button, "bottom:350px; right:20px");
            button.addClickListener(new friendListener());
            HorizontalLayout layout = new HorizontalLayout();
           // layout.setWidth("550px");
            List<Friend> friends = player.getPlayer().getFriends();
            for (Friend friend1 : friends) {
                layout.addComponent(friend(friend1));
            }
            absoluteLayout.addComponent(layout, "bottom:20px; right:20px");
        }
    }*/

   private void friends(){
       if (player.getPlayer().getFriends().size() > 0) {
           Button button = new Button("С друзьями");
           button.addStyleName("clickable");
           button.addClickListener(new FriendListener());
           absoluteLayout.addComponent(button, "bottom:350px; right:20px");
           updateFriends();
       }
   }

   private void updateFriends(){
       friendLayout.removeAllComponents();
       Image left = new Image(null, new FileResource(new File(path + "left.png")));
       left.addStyleName("clickable");
       left.setWidth("80px");
       left.addClickListener((MouseEvents.ClickListener) clickEvent -> {
           if (numberFriend > 0) {
               numberFriend--;
               updateFriends();
           }
       });
       Image right = new Image(null, new FileResource(new File(path + "right.png")));
       right.addStyleName("clickable");
       right.setWidth("80px");
       right.addClickListener(clickEvent -> {
           if (numberFriend < player.getPlayer().getFriends().size() - 3) {
               numberFriend++;
               updateFriends();
           }
       });
       friendLayout.addComponent(left);
       int min = Math.min(player.getPlayer().getFriends().size(), 3);
       for (int i = 0; i < min; i++) {
           friendLayout.addComponent(friend(player.getPlayer().getFriends().get(i + numberFriend)));
       }
       friendLayout.addComponent(right);
       friendLayout.setComponentAlignment(left, Alignment.MIDDLE_CENTER);
       friendLayout.setComponentAlignment(right, Alignment.MIDDLE_CENTER);
   }

    private VerticalLayout friend(Friend friend){
        int win = 0;
        int lose = 0;
        int tie = 0;
        for (ResultsFriends resultsFriends: friend.getResultsFriends()){
            switch (resultsFriends.getResult()){
                case 0:
                    tie++;
                    break;
                case 1:
                    win++;
                    break;
                case 2:
                    lose++;
            }
        }
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName("colorVertical");
        layout.setWidth("150px");
        layout.setHeight("300px");
        layout.addComponent(new Label(friend.getPlayer2().getName()));
        layout.addComponent(new Label(friend.getPlayer2().getEmail()));
        layout.addComponent(new Label("Побед: " + friend.getWinsPlayer1()));
        layout.addComponent(new Label("Поражений: " + friend.getWinsPlayer2()));
        layout.addComponent(new Label("Вничью: " + friend.getTie()));
        layout.addComponent(new Label("Среди последних 10 игр:"));
        layout.addComponent(new Label("Побед: " + win));
        layout.addComponent(new Label("Поражений: " + lose));
        layout.addComponent(new Label("Вничью: " + tie));

      /*  Button play = new Button("Сыграть");
        play.addStyleName("buttonplay");
        play.addClickListener(clickEvent -> {

        });
        layout.addComponent(play);*/
        return layout;
    }




    private VerticalLayout playerResult(){
        VerticalLayout layout = new VerticalLayout();
        Player player1 = player.getPlayer();
        if (!player1.getLastResults().isEmpty()) {
            layout.setWidth("150px");
            layout.setHeight("300px");
            layout.addStyleName("colorVertical");
            for (int i = 0; i < player1.getLastResults().size(); i++) {
                LastResult lastResult = player1.getLastResults().get(i);
                layout.addComponent(new Label(lastResult.getOpponent().getName() + ": " + rezult(lastResult.getResult())));
            }
        }
        return layout;
    }

    private String rezult(int c){
        switch (c){
            case 0:
                return "ничья";
            case 1:
                return "победа";
            default:
                return "поражение";
        }
    }

    private VerticalLayout player(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        Player player1 = player.getPlayer();
        layout.addComponent(new Label(player1.getName()));
        layout.addComponent(new Label("Побед: " + player1.getWins()));
        layout.addComponent(new Label("Поражений: " + player1.getLosing()));
        layout.addComponent(new Label("Вничью: " + player1.getTie()));
        return layout;
    }


    public void requestFromFriend(String email, OutputStream outputStream){
        ui.access(()-> {
            Window window = new Window();
            window.addStyleName("window");
            window.setClosable(false);
            window.center();
            Layout layout = new VerticalLayout();
            layout.addComponent(new Label("Запрос от " + email));
            Button button1 = new Button("Принять");
            button1.addClickListener((Button.ClickListener) clickEvent -> {
                try {
                    outputStream.write(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 window.close();
            });
            Button button2 = new Button("Отклонить");
            button2.addClickListener((Button.ClickListener) clickEvent -> {
                try {
                    outputStream.write(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                window.close();
            });
            layout.addComponents(button1, button2);
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("500px");
            window.setHeight("500px");
            window.center();
            getUI().addWindow(window);
        });
    }

    public void startGame(Game game){
        ui.access(()-> {
            Window window = new Window();
            window.addStyleName("window");
            window.setClosable(false);
            window.center();
            Layout layout = new VerticalLayout();
            Button buttonStart = new Button("Старт");
            buttonStart.addClickListener(new startListener(window, game));
            layout.addComponent(buttonStart);
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("500px");
            window.setHeight("500px");
            window.center();
            getUI().addWindow(window);
        });
    }



    private class startListener implements Button.ClickListener {
        Window window;
        Game game;
        public startListener(Window window, Game game){
            this.window = window;
            this.game = game;
        }
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            new GameRequest().deleteMainSocket(game.getIdPlayer());
            new Thread(new GameSocket(game)).start();
            if (game.isStartGame()) {
                VaadinRequest.getCurrent().setAttribute("game", game);
                VaadinRequest.getCurrent().setAttribute("player", player);
                MainUI.getCurrent().getNavigator().navigateTo(GameView.NAME);
            }
            window.close();
        }
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }


    private class FriendListener implements Button.ClickListener{

        Window window = new Window();

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
                window.addStyleName("window");
                window.setClosable(true);
                window.center();
                Layout layout = new VerticalLayout();
                List<Long> friendList = new ArrayList<>(3);
                for (int i = 0; i < 3; i++){
                    HorizontalLayout horizontalLayout = new HorizontalLayout();
                    TextField textField = new TextField();
                    Button button = new Button("Запрос");
                    button.addClickListener((Button.ClickListener) clickEvent1 -> {
                        int index = player.getPlayer().getFriends().indexOf(new Friend(0, new Player(0, textField.getValue(), ""), 0, 0, 0, null));
                        if (index != -1){
                           // if (new GameRequest().requestFriend(player.getPlayer().getId(), textField.getValue())){
                            if (new GameRequest().requestFriend(player.getPlayer().getFriends().get(index).getPlayer2().getId(), player.getPlayer().getEmail())){
                                textField.setEnabled(false);
                                friendList.add(player.getPlayer().getFriends().get(index).getPlayer2().getId());
                               // friendList.add(player.getPlayer().getFriends().get(index).getId());
                            }
                        }
                    });
                    horizontalLayout.addComponents(textField, button);
                    layout.addComponent(horizontalLayout);
                }
                Button button = new Button("Начать");
                button.addClickListener((Button.ClickListener) clickEvent12 -> {
                    if (!friendList.isEmpty()){
                        Game game = new Game(player.getPlayer().getId(), false);
                        long idGame = new GameRequest().startFriend(friendList);
                        game.setIdGame(idGame);
                        game.setCount(friendList.size()+1);
                        new GameRequest().deleteMainSocket(game.getIdPlayer());
                        new Thread(new GameSocket(game)).start();
                        if (game.isStartGame()) {
                            VaadinRequest.getCurrent().setAttribute("game", game);
                            VaadinRequest.getCurrent().setAttribute("player", player);
                            MainUI.getCurrent().getNavigator().navigateTo(GameView.NAME);
                        }
                        window.close();
                    }
                });
                layout.addComponent(button);
                window.setContent(layout);
                window.setDraggable(false);
                window.setResizable(false);
                window.setWidth("500px");
                window.setHeight("500px");
                window.center();
                getUI().addWindow(window);
            }
    }


    private class PlayListener implements MouseEvents.ClickListener{

        private boolean transfer;

        public PlayListener(boolean transfer) {
            this.transfer = transfer;
        }

        Window window = new Window();
        @Override
        public void click(MouseEvents.ClickEvent clickEvent) {
            window.addStyleName("window");
            window.setClosable(true);
            window.center();
            Layout layout = new HorizontalLayout();
            Button button2 = new Button("2");
            Button button3 = new Button("3");
            Button button4 = new Button("4");
            button2.addClickListener(new choiceListener());
            button3.addClickListener(new choiceListener());
            button4.addClickListener(new choiceListener());
            layout.addComponents(button2, button3, button4);
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("200px");
            window.setHeight("100px");
            window.center();
            getUI().addWindow(window);
        }

        private class choiceListener implements Button.ClickListener {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                int count = Integer.parseInt(clickEvent.getButton().getCaption());
                Game game;
                if (player.isLoged()) {
                    game = new Game(player.getPlayer().getId(), true);
                } else {
                    game = new Game(-1, transfer);
                }
                Long idGame = new GameRequest().createGame(count, transfer);
                game.setIdGame(idGame);
                game.setCount(count);
                new GameRequest().deleteMainSocket(game.getIdPlayer());
                new Thread(new GameSocket(game, count)).start();
                if (game.isStartGame()) {
                    VaadinRequest.getCurrent().setAttribute("game", game);
                    VaadinRequest.getCurrent().setAttribute("player", player);
                    MainUI.getCurrent().getNavigator().navigateTo(GameView.NAME);
                }
                window.close();
            }
        }
    }



    private class SettingsListener implements MouseEvents.ClickListener {

        public Window window = new Window();

        @Override
        public void click(MouseEvents.ClickEvent clickEvent) {
            window.addStyleName("window");
            window.setClosable(false);
            window.center();
            Layout layout;
            if (loggedIn){
                layout = settings();
            } else {
                layout = login();
            }
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("500px");
            window.setHeight("500px");
            window.center();
            getUI().addWindow(window);
        }



        private Layout login(){
            TabSheet tabSheet = new TabSheet();
            tabSheet.setSizeFull();
            tabSheet.addTab(authorization(), "Авторизация");
            tabSheet.addTab(registration(), "Регистрация");
            VerticalLayout layout = new VerticalLayout(tabSheet);
            layout.setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);
            return layout;
        }



        private Layout authorization(){
            VerticalLayout layout = new VerticalLayout();
            FormLayout formLayout = new FormLayout();
            TextField email = new TextField("Электронная почта");
            PasswordField password = new PasswordField("Пароль");
            Label error = new Label();
            Button registration = new Button(" Войти");
            registration.addClickListener((Button.ClickListener) clickEvent -> {
               if (loginUser(email.getValue(), password.getValue())) {
                   updateData();
                   window.close();
               } else {
                   error.setValue("Неверная пара логин/пароль");
               }
            });
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(email, password, error);
            HorizontalLayout horizontalLayout = new HorizontalLayout(registration, cancel);
            layout.addComponents(formLayout, horizontalLayout);
            layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
            return layout;
        }

        private Layout registration() {
            VerticalLayout layout = new VerticalLayout();
            FormLayout formLayout = new FormLayout();
            TextField name = new TextField("Имя");
            TextField email = new TextField("Электронная почта");
            PasswordField password = new PasswordField("Пароль");
            PasswordField password2 = new PasswordField("Повторите пароль");
            Label error = new Label();
            Button registration = new Button("Зарегистрироваться");
            registration.addClickListener((Button.ClickListener) clickEvent -> {
                int c = createPlayer(name.getValue(), email.getValue(), password.getValue(), password2.getValue());
                if (error(error, c)){
                    updateData();
                    window.close();
                }
            });
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(name, email, password, password2, error);
            HorizontalLayout horizontalLayout = new HorizontalLayout(registration, cancel);
            layout.addComponents(formLayout, horizontalLayout);
            layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
            return layout;
        }



        private boolean loginUser(String email, String password){
            DataRequests dataRequests = new DataRequests();
            Resource<Long> id = dataRequests.authorization(email, password);
            if (id.getContent() != -1){
                Resource<Player> player1 = dataRequests.getPlayer(id);
                if (player1 != null){
                    player.setPlayer(player1);
                    return true;
                }
            }
            return false;
        }

        private int createPlayer(String name, String email, String password1, String password2){
            if (name.length() < 3){
                return 1;
            }
            if (!email.matches("\\w+@\\w+\\.\\w+")){
                return 2;
            }
            if (!password1.equals(password2)){
                return 3;
            }
            if (password1.length() < 6){
                return 4;
            }
            if (!(verification(name) && verification(email) && verification(password1))){
                return 5;
            }
            if (new DataRequests().createPlayer(name, email, password1, player)){
                return 0;
            } else {
                return -1;
            }

        }

        private Layout settings(){
            TabSheet tabSheet = new TabSheet();
            tabSheet.setSizeFull();
            tabSheet.addTab(setting(), "Настройки");
            tabSheet.addTab(comment(), "Комментарий");
            tabSheet.addTab(addFriend(), "Друзья");
            VerticalLayout layout = new VerticalLayout(tabSheet);
            layout.setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);
            return layout;

        }


        private Layout setting(){
            VerticalLayout layout = new VerticalLayout();
            FormLayout formLayout = new FormLayout();
            TextField name = new TextField("Имя");
            TextField email = new TextField("Электронная почта");
            Label error = new Label();
            PasswordField password = new PasswordField("Пароль");
            PasswordField password2 = new PasswordField("Повторите пароль");
            Button save = new Button("Сохранить");
            save.addClickListener((Button.ClickListener) clickEvent -> {
                int c = updatePlayer(name.getValue(), email.getValue(), password.getValue(), password2.getValue());
                if (error(error, c)){
                    updateData();
                    window.close();
                }
            });
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(name, email, password, password2, error);
            HorizontalLayout horizontalLayout = new HorizontalLayout(save, cancel);
            layout.addComponents(formLayout, horizontalLayout);
            layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
            Button exit = new Button("Выйти");                                  //СЛУШАТЕЛЬ!!!!!!!!!!!!!!!!!!!!!
            exit.addClickListener((Button.ClickListener) clickEvent -> {
                player.setPlayer(null);
                updateData();
                window.close();
            });
            layout.addComponent(exit);
            layout.setComponentAlignment(exit, Alignment.MIDDLE_CENTER);
            return layout;
        }

        private Layout comment(){
            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(new Label("Ваш комментарий:"));
            TextArea textArea = new TextArea();
            textArea.setRows(5);
            Button save = new Button("Сохранить");
            save.addClickListener((Button.ClickListener) clickEvent -> {
                if (!textArea.getValue().isEmpty()) {
                    if (new DataRequests().addComment(new Comment(player.getPlayer().getId(), textArea.getValue()), player)){
                        window.close();
                    }
                }
            });
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            HorizontalLayout horizontalLayout = new HorizontalLayout(save, cancel);
            layout.addComponent(textArea);
            layout.addComponent(horizontalLayout);
            return layout;
        }

        private Layout addFriend(){
            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(new Label("Введите email друга:"));
            TextField textField = new TextField();
            layout.addComponent(textField);
            Button save = new Button("Сохранить");
            save.addClickListener((Button.ClickListener) clickEvent -> {
                if (!textField.getValue().isEmpty()) {
                    if (new DataRequests().addFriend(textField.getValue(), player)){
                        updateData();
                        window.close();
                    }
                }
            });
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            HorizontalLayout horizontalLayout = new HorizontalLayout(save, cancel);
            layout.addComponent(horizontalLayout);
            return layout;
        }

        private int updatePlayer(String name, String email, String password1, String password2){
            if (!name.isEmpty() && name.length() < 3){
                return 1;
            }
            if (!email.isEmpty() && !email.matches("\\w+@\\w+\\.\\w+")){
                return 2;
            }
            if (!password1.equals(password2)){
                return 3;
            }
            if (!password1.isEmpty() && password1.length() < 6){
                return 4;
            }
            if (!(verification(name) && verification(email) && verification(password1))){
                return 5;
            }
            if (new DataRequests().updatePlayer(name, email, password1, player)){
                return 0;
            } else {
                return -1;
            }
        }

        private boolean error(Label error, int c){
            switch (c){
                case 0:
                    error.setValue("");
                   return true;
                case 1:
                    error.setValue("Имя должн быть длиннее 2-х символов");
                    return false;
                case 2:
                    error.setValue("Электронная почта не верна");
                    return false;
                case 3:
                    error.setValue("Пароли не совпадают");
                    return false;
                case 4:
                    error.setValue("Пароль должен быть длиннее 5-и символов");
                    break;
                case 5:
                    error.setValue("Символы \', \", \\ и пробел не допустимы");
                    return false;
            }
            return false;
        }

        private boolean verification(String s){
            if (s.contains("\'") || s.contains("\"") || s.contains("\\") || s.contains("\t") || s.contains(" ")){
                return false;
            }
            return true;
        }

        private class CancelListener implements Button.ClickListener {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                window.close();
            }
        }
    }




    private class ShopListener implements MouseEvents.ClickListener {

        @Override
        public void click(MouseEvents.ClickEvent clickEvent) {
            Window window = new Window();
            window.addStyleName("window");
            window.setClosable(false);
            window.center();
            TabSheet tabSheet = new TabSheet();
            tabSheet.setSizeFull();
            tabSheet.addTab(tables(), "Столы");
            tabSheet.addTab(faces(), "Колоды");
            tabSheet.addTab(backs(), "Рубашки");
            Button cancel = new Button("Выход");
            cancel.addStyleName("below");
            cancel.addClickListener(clickEvent1 -> window.close());
            VerticalLayout layout = new VerticalLayout(tabSheet, cancel);
            layout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("500px");
            window.setHeight("500px");
            window.center();
            getUI().addWindow(window);
        }

        private Layout tables() {
            HorizontalLayout layout = new HorizontalLayout();
            List<GamingTable> gamingTables = new DataRequests().getGamingTables(player);
            List<GamingTable> playerTable = player.getPlayer().getTables();                             //!!!!!!!!!
            for (int i = 0; i < gamingTables.size(); i++) {
                VerticalLayout verticalLayout = new VerticalLayout();
                GamingTable table = gamingTables.get(i);
                Image image = new Image(null, new FileResource(new File(path + table.getPatch())));
                image.setHeight("150px");
                image.setWidth("150px");
                verticalLayout.addComponents(new Label(table.getName()), image);
                Button doTable = new Button();
                if (playerTable.contains(table)) {
                    doTable.setCaption("Выбрать");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        player.getPlayer().setPlayerTable(table.getId());
                        new DataRequests().updatePlayer(player);
                        int index = player.getPlayer().getTables().indexOf(new GamingTable(player.getPlayer().getPlayerTable()));
                        if (index != -1){
                            GamingTable table1 = player.getPlayer().getTables().get(index);
                            background.setSource(new FileResource(new File(path + table1.getPatch())));
                        }
                    });
                    verticalLayout.addComponent(doTable);
                } else if (table.getId() != player.getPlayer().getPlayerTable()) {
                    verticalLayout.addComponent(new Label("Стоимость: " + table.getCost()));
                    doTable.setCaption("Купить");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        if (player.getPlayer().getBalance() > table.getCost()) {
                            player.getPlayer().getTables().add(table);
                            player.getPlayer().setBalance(player.getPlayer().getBalance() - table.getCost());
                            new DataRequests().updatePlayer(player);
                        }
                    });
                    verticalLayout.addComponent(doTable);
                }
                layout.addComponent(verticalLayout);
            }
            return layout;
        }

        private Layout faces() {
            HorizontalLayout layout = new HorizontalLayout();
            List<Face> faces = new DataRequests().getFaces(player);
            List<Face> playerFaces = player.getPlayer().getFaces();
            for (int i = 0; i < faces.size(); i++) {
                VerticalLayout verticalLayout = new VerticalLayout();
                Face face = faces.get(i);
                Image image = new Image(null, new FileResource(new File(path + face.getPatch() + "/img.png")));
                image.setHeight("150px");
                image.setWidth("150px");
                verticalLayout.addComponents(new Label(face.getName()), image);
                Button doTable = new Button();
                if (playerFaces.contains(face)) {
                    doTable.setCaption("Выбрать");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        player.getPlayer().setPlayerFace(face.getId());
                        new DataRequests().updatePlayer(player);
                    });
                    verticalLayout.addComponent(doTable);
                } else if (face.getId() != player.getPlayer().getPlayerFace()) {
                    verticalLayout.addComponent(new Label("Стоимость: " + face.getCost()));
                    doTable.setCaption("Купить");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        if (player.getPlayer().getBalance() > face.getCost()) {
                            player.getPlayer().getFaces().add(face);
                            player.getPlayer().setBalance(player.getPlayer().getBalance() - face.getCost());
                            new DataRequests().updatePlayer(player);
                        }
                    });
                    verticalLayout.addComponent(doTable);
                }
                layout.addComponent(verticalLayout);
            }
            return layout;
        }

        private Layout backs() {
            HorizontalLayout layout = new HorizontalLayout();
            List<Back> backs = new DataRequests().getBacks(player);
            List<Back> playerBacks = player.getPlayer().getBacks();
            for (int i = 0; i <backs.size(); i++) {
                VerticalLayout verticalLayout = new VerticalLayout();
                Back back = backs.get(i);
                Image image = new Image(null, new FileResource(new File(path + back.getPatch())));
                image.setHeight("150px");
                image.setWidth("150px");
                verticalLayout.addComponents(new Label(back.getName()), image);
                Button doTable = new Button();
                if (playerBacks.contains(back)) {
                    doTable.setCaption("Выбрать");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        player.getPlayer().setPlayerBack(back.getId());
                        new DataRequests().updatePlayer(player);
                    });
                    verticalLayout.addComponent(doTable);
                } else if (back.getId() != player.getPlayer().getPlayerBack()) {
                    verticalLayout.addComponent(new Label("Стоимость: " + back.getCost()));
                    doTable.setCaption("Купить");
                    doTable.addClickListener((Button.ClickListener) clickEvent -> {
                        if (player.getPlayer().getBalance() > back.getCost()) {
                            player.getPlayer().getBacks().add(back);
                            player.getPlayer().setBalance(player.getPlayer().getBalance() - back.getCost());
                            new DataRequests().updatePlayer(player);
                        }
                    });
                    verticalLayout.addComponent(doTable);
                }
                layout.addComponent(verticalLayout);
            }
            return layout;
        }
    }
}
