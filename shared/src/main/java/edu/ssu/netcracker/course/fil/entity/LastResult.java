package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class LastResult{

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long player;

    @Getter
    @Setter
    private Player opponent;

    @Getter
    @Setter
    private int result;

    public LastResult(long player, Player opponent, int result) {
        this.player = player;
        this.opponent = opponent;
        this.result = result;
    }

    @Override
    public String toString() {
        return "LastResult{" +
                "id=" + id +
                ", player=" + player +
                ", opponent=" + opponent +
                ", result=" + result +
                '}';
    }
}
