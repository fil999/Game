package edu.ssu.netcracker.course.fil.service;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by --- on 22.01.2019.
 */
public class MainSockets implements Runnable {

    private static List<Users> usersList = new ArrayList<>();

    public static List<Users> getUsersList() {
        return usersList;
    }

    public static void deleteUser(int index){
        try {
            usersList.get(index).getSocket().getOutputStream().write(2);
            usersList.get(index).getSocket().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        usersList.remove(index);
    }

    public static Socket getSocket(long id){
        int index = usersList.indexOf(new Users(null, id));
        if (index != -1) {
            return usersList.get(index).getSocket();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8084, 10);
            Socket connection;
            while (true) {
                connection = serverSocket.accept();
                int n = connection.getInputStream().read();
                byte[] bytes = new byte[n];
                connection.getInputStream().read(bytes);
                long id = Long.parseLong(new String(bytes));
                if (id > 1) {
                    usersList.add(new Users(connection, id));
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
