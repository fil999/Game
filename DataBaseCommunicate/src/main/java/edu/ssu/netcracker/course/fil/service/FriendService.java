package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.entity.Friend;
import edu.ssu.netcracker.course.fil.entity.Player;
import edu.ssu.netcracker.course.fil.repository.FriendRepository;
import edu.ssu.netcracker.course.fil.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by --- on 29.11.2018.
 */
@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Friend insertFriend(String emailFriend, long id){
        Player playerFriend = playerRepository.findPlayerByEmail(emailFriend);
        Friend friend = null;
        if (playerFriend != null) {
            friend = new Friend(id, playerFriend, 0, 0, 0, new ArrayList<>());
            friend = friendRepository.save(friend);
            Player gamePlayer = friend.getPlayer2();
            friend.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            //friend.getResultsFriends();
        }
        return friend;
    }
}
