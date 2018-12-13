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
@Table(name = "FACE")
public class Face {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACE")
    private long id;

    @Getter
    @Setter
    @Column(name = "NAME_FACE", length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name = "COST_FACE")
    private int cost;

    @Getter
    @Setter
    @Column(name = "PATCH_FACE", length = 50)
    private String patch;


    public Face(String name, int cost, String patch) {
        this.name = name;
        this.cost = cost;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", patch='" + patch + '\'' +
                '}';
    }
}
