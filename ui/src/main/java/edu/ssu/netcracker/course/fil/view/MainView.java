package edu.ssu.netcracker.course.fil.view;

import com.vaadin.event.MouseEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import edu.ssu.netcracker.course.fil.entity.Friend;
import edu.ssu.netcracker.course.fil.ui.MainUI;

import java.io.File;

/**
 * Created by --- on 08.12.2018.
 */
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "";
    private static final String path = "ui\\src\\main\\resources\\images\\";
    public boolean loggedIn = false;

    public MainView() {
        setSizeFull();
        addStyleName("white");


        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.addStyleName("back");
        absoluteLayout.setWidth("1024px");
        absoluteLayout.setHeight("768px");

        absoluteLayout.addComponent(new Image(null, new FileResource(new File(path + "tabledefault.jpg"))));


        Image settings2 = new Image(null, new FileResource(new File(path + "settings2.png")));
        settings2.addStyleName("clickable");
        settings2.setWidth("50");
        settings2.addClickListener(new SettingsListener());



        Image play = new Image(null, new FileResource(new File(path + "play.png")));
        play.addStyleName("clickable");
        play.setHeight("50px");
        play.addClickListener(clickEvent -> {
            MainUI.getCurrent().getNavigator().navigateTo(GameView.NAME);
        });

        Image play1 = new Image(null, new FileResource(new File(path + "play.png")));
        play1.setHeight("50px");
        absoluteLayout.addComponent(play, "top:300px; left:300px");
        absoluteLayout.addComponent(play1, "top:300px; left:600px");





        Button test = new Button("test");
        test.addStyleName("clickable");
        test.addClickListener(clickEvent -> loggedIn = !loggedIn);
        absoluteLayout.addComponent(test, "top:190px; right:20px");

        absoluteLayout.addComponent(settings2, "top:20px; right:20px");


        itLogged(absoluteLayout);

        addComponent(absoluteLayout);
        setComponentAlignment(absoluteLayout, Alignment.TOP_CENTER);
    }


    private void itLogged(AbsoluteLayout layout){
        Image shop = new Image(null, new FileResource(new File(path + "shop4.png")));
        shop.addStyleName("clickable");
        shop.setWidth("50");
        shop.addClickListener(new ShopListener());
        layout.addComponent(shop, "top:90px; right:20px");

        layout.addComponent(friend(new Friend()), "bottom:20px; right:20px");
        layout.addComponent(playerResult(), "bottom:20px; left:20px");
        layout.addComponent(player(), "top:20px; left:20px");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Label("1000"));
        horizontalLayout.addComponent(new Image(null, new FileResource(new File(path + "coin.png"))));
        layout.addComponent(horizontalLayout, "top:20px; left:400px");

    }



    private VerticalLayout friend(Friend friend){
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName("colorVertical");
        layout.setWidth("150px");
        layout.setHeight("300px");
        layout.addComponent(new Label("Name"));
        layout.addComponent(new Label("email"));
        layout.addComponent(new Label("Побед: " + 3));
        layout.addComponent(new Label("Поражений: " + 4));
        layout.addComponent(new Label("Вничью: " + 4));
        layout.addComponent(new Label("Среди последних 10 игр:"));
        layout.addComponent(new Label("Побед: 6"));
        layout.addComponent(new Label("Поражений: 3"));
        layout.addComponent(new Label("Вничью: 1"));

        Button play = new Button("Сыграть");
        play.addStyleName("buttonplay");
        play.addClickListener(clickEvent -> {

        });
        layout.addComponent(play);
        return layout;
    }



    private VerticalLayout playerResult(){
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("150px");
        layout.setHeight("300px");
        layout.addStyleName("colorVertical");
        for (int i = 0; i < 10; i++){
            layout.addComponent(new Label("Противник: результат"));
        }

        return layout;
    }

    private VerticalLayout player(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.addComponent(new Label("Имя"));
        layout.addComponent(new Label("Побед: 200"));
        layout.addComponent(new Label("Поражений: 200"));
        layout.addComponent(new Label("Вничью: 200"));
        return layout;
    }




    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
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
                layout = login();
            } else {
                layout = settings();
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

        private Layout settings(){
            VerticalLayout layout = new VerticalLayout();
            Label setting = new Label("Настройки");
            layout.addComponent(setting);
            layout.setComponentAlignment(setting, Alignment.MIDDLE_CENTER);
            FormLayout formLayout = new FormLayout();
            TextField name = new TextField("Имя", "TestName");
            TextField email = new TextField("Электронная почта", "TestEmail");
            PasswordField password = new PasswordField("Пароль");
            PasswordField password2 = new PasswordField("Повторите пароль");
            Button registration = new Button("Сохранить");
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(name, email, password, password2);
            HorizontalLayout horizontalLayout = new HorizontalLayout(registration, cancel);
            layout.addComponents(formLayout, horizontalLayout);
            layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
            return layout;
        }

        private Layout authorization(){
            VerticalLayout layout = new VerticalLayout();
            FormLayout formLayout = new FormLayout();
            TextField email = new TextField("Электронная почта");
            PasswordField password = new PasswordField("Пароль");
            Button registration = new Button(" Войти");
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(email, password);
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
            Button registration = new Button("Зарегистрироваться");
            Button cancel = new Button("Отмена");
            cancel.addClickListener(new CancelListener());
            formLayout.addComponents(name, email, password, password2);
            HorizontalLayout horizontalLayout = new HorizontalLayout(registration, cancel);
            layout.addComponents(formLayout, horizontalLayout);
            layout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
            return layout;
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
            tabSheet.addTab(tables(), "Колоды");
            tabSheet.addTab(tables(), "Рубашки");
            Button cancel = new Button("Выход");
            cancel.addClickListener(clickEvent1 -> window.close());
            VerticalLayout layout = new VerticalLayout(tabSheet, cancel);
            layout.setComponentAlignment(cancel, Alignment.MIDDLE_RIGHT);
            window.setContent(layout);
            window.setDraggable(false);
            window.setResizable(false);
            window.setWidth("500px");
            window.setHeight("500px");
            window.center();
            getUI().addWindow(window);
        }

        private Panel tables(){
            Panel panel = new Panel();
            panel.addStyleName("transparent");
            HorizontalSplitPanel split = new HorizontalSplitPanel();
            panel.setContent(split);
            HorizontalLayout layout = new HorizontalLayout();
            for (int i = 1; i <= 5; i++) {
                VerticalLayout verticalLayout = new VerticalLayout();
                Image image = new Image(null, new FileResource(new File(path + "faceDefault\\10" + i + ".jpg")));
                image.setHeight("150px");
                image.setWidth("150px");
                verticalLayout.addComponents(new Label(String.valueOf(i)), image);
                verticalLayout.addComponent(new Label("Стоимость: 200"));
                Button buy = new Button("Купить");
                verticalLayout.addComponent(buy);
                layout.addComponent(verticalLayout);
            }
            panel.setContent(layout);
            return panel;
        }

        private Layout faces(){
            return new VerticalLayout();
        }

        private Layout backs(){
            return new VerticalLayout();
        }

    }
}
