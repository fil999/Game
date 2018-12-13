package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class ResultsFriends {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long friends;

    @Getter
    @Setter
    private int result;


    public ResultsFriends(long friends, int result) {
        this.friends = friends;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultsFriends{" +
                "id=" + id +
                ", friends=" + friends +
                ", result=" + result +
                '}';
    }
}
