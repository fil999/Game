package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.entity.GamingTable;
import edu.ssu.netcracker.course.fil.repository.GamingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by --- on 29.11.2018.
 */
@Service
public class GamingTableService {

    @Autowired
    private GamingTableRepository gamingTableRepository;


    public List<GamingTable> selectAllGamingTable(){
        return gamingTableRepository.findAll();
    }
}
