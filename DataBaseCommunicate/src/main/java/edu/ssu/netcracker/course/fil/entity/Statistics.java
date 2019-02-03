package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "STATISTICS_GAME")
public class Statistics {

    @Getter
    @Setter
    @Id
    @Column(name = "DATE_STATISTICS", unique = true)
    @Temporal(TemporalType.DATE)
    private Date dateStatistics;

    @Getter
    @Setter
    @Column(name = "NUMER_PLAYER_STATISTIC")
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
