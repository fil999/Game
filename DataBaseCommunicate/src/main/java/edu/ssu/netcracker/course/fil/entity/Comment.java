package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by --- on 25.11.2018.
 */

//hateoas
@NoArgsConstructor
@Entity
@Table(name = "COMMENT_PLAYER")
public class Comment {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMMENT")
    private long id;

    @Getter
    @Setter
    @Column(name = "PLAYER_COMMENT")
    private long player;

    @Getter
    @Setter
    @Column(name = "TEXT_COMMENT", length = 1000)
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
