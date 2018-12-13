package edu.ssu.netcracker.course.fil.controller;

import edu.ssu.netcracker.course.fil.entity.Face;
import edu.ssu.netcracker.course.fil.service.FaceService;
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
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private FaceService faceService;

    @ResponseBody
    @RequestMapping(value = "/selectAllFace",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Face> selectAll() {
        return faceService.selectAllFace();
    }
}
