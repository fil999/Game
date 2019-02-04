package edu.ssu.netcracker.course.fil.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by --- on 06.01.2019.
 */
public class GameSockets implements Runnable{

    private GameService gameService;

    @Override
    public void run() {
        gameService = new GameService();
        try {
            ServerSocket serverSocket = new ServerSocket(8083, 10);
            Socket connection;
            while (true) {
                connection = serverSocket.accept();
                int n = connection.getInputStream().read();
                byte[] bytes = new byte[n];
                connection.getInputStream().read(bytes);
                long id = Long.parseLong(new String(bytes));
                //int transfer = connection.getInputStream().read();
                int who = connection.getInputStream().read();
//                if (who == 0) {
//                    int count = connection.getInputStream().read();
//                    gameService.addPlayer(connection, id, count, transfer);
//                } else {
                n = connection.getInputStream().read();
                bytes = new byte[n];
                connection.getInputStream().read(bytes);
                long idGame = Long.parseLong(new String(bytes));
                gameService.addPlayer(connection, id, idGame, who);
                //  }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
