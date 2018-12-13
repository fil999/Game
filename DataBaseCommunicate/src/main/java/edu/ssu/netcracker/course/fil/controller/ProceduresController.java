package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.*;
import edu.ssu.netcracker.course.fil.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by --- on 27.11.2018.
 */
@Controller
@RequestMapping("/procedures")
public class ProceduresController {

    @Autowired
    private ProcedureService procedureService;

    @RequestMapping(value = "/game",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void game(@RequestBody LastResult lastResult){
        procedureService.game(lastResult);
    }

    @RequestMapping(value = "/friendsGame",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void friendsGame(@RequestBody ResultsFriends resultsFriends){
        procedureService.friendsGame(resultsFriends);
    }

    @RequestMapping(value = "/save2",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save2(@RequestBody SavedGame2 savedGame2){
        procedureService.save2(savedGame2);
    }

    @RequestMapping(value = "/save3",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save3(@RequestBody SavedGame3 savedGame3){
        procedureService.save3(savedGame3);
    }

    @RequestMapping(value = "/save4",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save4(@RequestBody SavedGame4 savedGame4){
        procedureService.save4(savedGame4);
    }
}
