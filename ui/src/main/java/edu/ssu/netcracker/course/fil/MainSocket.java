package edu.ssu.netcracker.course.fil;

import edu.ssu.netcracker.course.fil.game.Game;
import edu.ssu.netcracker.course.fil.view.MainView;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by --- on 22.01.2019.
 */
public class MainSocket implements Runnable{

    private long id;
    private MainView mainView;

    public MainSocket(MainView mainView, long id){
        this.mainView = mainView;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8084);
            byte[] bytes = String.valueOf(id).getBytes();
            socket.getOutputStream().write(bytes.length);
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().flush();
            InputStream inputStream = socket.getInputStream();
            int n;
            while (true) {
                int c = inputStream.read();
                switch (c) {
                    case 0:
                        n = inputStream.read();
                        bytes = new byte[n];
                        inputStream.read(bytes);
                        String email = new String(bytes);
                        mainView.requestFromFriend(email, socket.getOutputStream());
                        break;
                    case 1:
                        n = inputStream.read();
                        bytes = new byte[n];
                        inputStream.read(bytes);
                        long id = Long.parseLong(new String(bytes));
                        Game game = new Game(id, false);
                        game.setIdGame(id);
                        mainView.startGame(game);
                        break;
                    case 2:
                        socket.close();
                        return;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}