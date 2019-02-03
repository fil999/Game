package edu.ssu.netcracker.course.fil.service;

import java.net.Socket;

/**
 * Created by --- on 22.01.2019.
 */
public class Users {

    private Socket socket;
    private long id;

    public Users(Socket socket, long id) {
        this.socket = socket;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public boolean equals(Object object){
        Users user = (Users) object;
        if (user.getId() == id){
            return true;
        }
        return false;
    }
}
