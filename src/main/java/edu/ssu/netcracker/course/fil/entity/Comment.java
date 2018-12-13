package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */

//hateoas
@NoArgsConstructor
public class Comment {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long player;

    @Getter
    @Setter
    private String text;


    public Comment(long player, String text) {
        this.player = player;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", player=" + player +
                ", text='" + text + '\'' +
                '}';
    }
}
