package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by --- on 22.01.2019.
 */
@Controller
@RequestMapping("/socket")
public class SocketController {

    @Autowired
    private SocketService socketService;

    @RequestMapping(value = "/deleteSocket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean deleteSocket(@RequestBody long id){
         return socketService.deleteSocket(id);
    }

    @RequestMapping(value = "/playFriend/{idFriend}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean playFriend(@PathVariable(value = "idFriend") long idFriend, @RequestBody String email){
        return socketService.playFriend(email, idFriend);
    }

    @RequestMapping(value = "/startGameFriend", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long startGameFriend(@RequestBody List<Long> list){
        return socketService.startGameFriends(list);
    }


}
