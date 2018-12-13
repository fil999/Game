package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.entity.Statistics;
import edu.ssu.netcracker.course.fil.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by --- on 29.11.2018.
 */
@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public int insertStatistics(int number){
        if (number >= 0){
            statisticsRepository.save(new Statistics(new Date(), number));
            return 0;
        } else {
            return -1;
        }
    }

}
