package edu.ssu.netcracker.course.fil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by --- on 22.01.2019.
 */
@Service
public class SocketService {

    @Autowired
    GameService gameService;

    public boolean deleteSocket(long id){
        int index = MainSockets.getUsersList().indexOf(new Users(null, id));
        if (index != -1){
            MainSockets.deleteUser(index);
        }
        return true;
    }

    public boolean playFriend(String email, long idFriend){
        Socket socket = MainSockets.getSocket(idFriend);
        if (socket != null){
            try {
                socket.getOutputStream().write(0);
                byte[] bytes = email.getBytes();
                socket.getOutputStream().write(bytes.length);
                socket.getOutputStream().write(bytes);
                int num = socket.getInputStream().read();
                if (num == 0){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public long startGameFriends(List<Long> list){
        return gameService.startGameFriends(list);
    }
}
