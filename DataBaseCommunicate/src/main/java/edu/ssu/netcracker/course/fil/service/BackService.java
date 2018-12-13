package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.entity.Back;
import edu.ssu.netcracker.course.fil.repository.BackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by --- on 29.11.2018.
 */
@Service
public class BackService {

    @Autowired
    private BackRepository backRepository;

    public List<Back> selectAllBack() {
        return backRepository.findAll();
    }
}
