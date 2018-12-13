package edu.ssu.netcracker.course.fil.ui;



import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import edu.ssu.netcracker.course.fil.view.GameView;
import edu.ssu.netcracker.course.fil.view.MainView;

/**
 * Created by --- on 08.12.2018.
 */
@SpringUI
@Theme("mytheme")
@Title("Главная страница")
public class MainUI extends UI {

    Navigator navigator;
    public static MainView mainView;
    public static GameView gameView;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainView = new MainView();
        gameView = new GameView();

        navigator = new Navigator(this, this);

        navigator.addView(MainView.NAME, MainView.class);
        navigator.addView(GameView.NAME, GameView.class);

    }
}
