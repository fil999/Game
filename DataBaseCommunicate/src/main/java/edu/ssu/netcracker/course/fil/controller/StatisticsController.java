package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by --- on 27.11.2018.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ResponseBody
    @RequestMapping(value = "/insertStatistics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insertStatistics(@RequestBody int number){
        return statisticsService.insertStatistics(number);
    }
}
