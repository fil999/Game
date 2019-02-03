package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class Back{

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


    public Back(String name, int cost, String patch) {
        this.name = name;
        this.cost = cost;
        this.patch = patch;
    }

    public Back(long id) {
        this.id = id;
        patch = "backDefault.png";
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

    @Override
    public boolean equals(Object object){
        Back other = (Back) object;
        if (id == other.getId()){
            return true;
        } return false;
    }
}
