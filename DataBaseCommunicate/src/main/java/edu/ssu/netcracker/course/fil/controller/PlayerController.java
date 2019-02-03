package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.Player;
import edu.ssu.netcracker.course.fil.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by --- on 26.11.2018.
 */
@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

   // @ResponseBody
   // @RequestMapping(value = "/selectPlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   // public Player selectPlayer(@RequestBody Player player){
    //    return playerService.selectByEmailAndPassword(player);
    //}

    @ResponseBody
    @RequestMapping(value = "/updatePlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource<Player>> updatePlayer(@RequestBody Player player){
        return playerService.updatePlayer(player);
    }

    @ResponseBody
    @RequestMapping(value = "/createPlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource<Player>> createPlayer(@RequestBody Player player){
        return playerService.createPlayer(player);
    }

    @ResponseBody
    @RequestMapping(value = "/authorization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource<Long>> authorization(@RequestBody List<String> data){
        List<Resource<Long>> longResource = playerService.autoization(data.get(0), data.get(1));
        return longResource;
    }

    @ResponseBody
    @RequestMapping(value = "/findPlayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource<Player>> findPlayer(@RequestBody Long id){
        return playerService.findById(id);
    }



}
