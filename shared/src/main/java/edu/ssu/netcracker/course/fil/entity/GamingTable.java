package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "GAMING_TABLE")
public class GamingTable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TABLE")
    private long id;

    @Getter
    @Setter
    @Column(name = "NAME_TABLE", length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name = "COST_TABLE")
    private int cost;

    @Getter
    @Setter
    @Column(name = "PATCH_TABLE", length = 50)
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
