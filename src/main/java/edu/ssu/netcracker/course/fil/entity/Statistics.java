package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class Statistics {

    @Getter
    @Setter
    private Date dateStatistics;

    @Getter
    @Setter
    private int numberPlayerStatistics;


    public Statistics(Date dateStatistics, int numberPlayerStatistics) {
        this.dateStatistics = dateStatistics;
        this.numberPlayerStatistics = numberPlayerStatistics;
    }

    @Override
    public String toString() {
        return "Statistics_Game_Entity{" +
                "dateStatistics=" + dateStatistics +
                ", numberPlayerStatistics=" + numberPlayerStatistics +
                '}';
    }
}
