package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class Face {

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


    public Face(String name, int cost, String patch) {
        this.name = name;
        this.cost = cost;
        this.patch = patch;
    }

    public Face(long id) {
        this.id = id;
        patch = "faceDefault";
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

    @Override
    public boolean equals(Object object){
        Face other = (Face) object;
        if (id == other.getId()){
            return true;
        } return false;
    }
}
