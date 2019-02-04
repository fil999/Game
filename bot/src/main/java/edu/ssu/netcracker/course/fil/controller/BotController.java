package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by --- on 03.02.2019.
 */
@Controller
@RequestMapping("/bot")
public class BotController {

    @Autowired
    BotService botService;

    @RequestMapping(value = "/newBot/{transfer}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean newBot(@PathVariable(value = "transfer") boolean transfer, @RequestBody long idGame){
        return botService.newBot(idGame, transfer);
    }
}
