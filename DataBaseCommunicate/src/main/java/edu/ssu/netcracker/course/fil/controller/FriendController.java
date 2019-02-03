package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.Friend;
import edu.ssu.netcracker.course.fil.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by --- on 27.11.2018.
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @ResponseBody
    @RequestMapping(value = "/insertFriend/{idPlayer}",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Friend insertFriend(@PathVariable(value = "idPlayer") long idPlayer, @RequestBody String emailFriend) {
        return friendService.insertFriend(emailFriend, idPlayer);
    }
}
