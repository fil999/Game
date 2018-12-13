package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.Back;
import edu.ssu.netcracker.course.fil.service.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by --- on 27.11.2018.
 */
@Controller
@RequestMapping("/back")
public class BackController {

    @Autowired
    private BackService backService;

    @ResponseBody
    @RequestMapping(value = "/selectAllBack",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Back> selectAll() {
        return backService.selectAllBack();
    }
}
