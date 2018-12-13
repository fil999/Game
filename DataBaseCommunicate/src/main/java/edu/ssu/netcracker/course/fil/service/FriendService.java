package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.entity.Friend;
import edu.ssu.netcracker.course.fil.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by --- on 29.11.2018.
 */
@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public int insertFriend(Friend friend){
        friend = friendRepository.save(friend);
        if (friend != null){
            return 0;
        }
        return -1;
    }
}
