package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class GamingTable {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int cost;

    @Getter
    @Setter
    private String patch;


    public GamingTable(String name, int cost, String patch) {
        this.name = name;
        this.cost = cost;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "GamingTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", patch='" + patch + '\'' +
                '}';
    }
}
