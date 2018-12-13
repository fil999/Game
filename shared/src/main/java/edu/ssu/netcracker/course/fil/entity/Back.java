package edu.ssu.netcracker.course.fil.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "BACK")
public class Back{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BACK")
    private long id;

    @Getter
    @Setter
    @Column(name = "NAME_BACK", length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name = "COST_BACK")
    private int cost;

    @Getter
    @Setter
    @Column(name = "PATCH_BACK", length = 50)
    private String patch;


    public Back(String name, int cost, String patc) {
        this.name = name;
        this.cost = cost;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Back{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", patch='" + patch + '\'' +
                '}';
    }
}
