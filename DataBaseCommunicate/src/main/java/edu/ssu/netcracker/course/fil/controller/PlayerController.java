package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.Player;
import edu.ssu.netcracker.course.fil.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by --- on 26.11.2018.
 */
@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @ResponseBody
    @RequestMapping(value = "/selectPlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Player selectPlayer(@RequestBody Player player){
        return playerService.selectByEmailAndPassword(player);
    }

    @ResponseBody
    @RequestMapping(value = "/updatePlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updatePlayer(@RequestBody Player player){
        return playerService.updatePlayer(player);
    }



}
