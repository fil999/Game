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
@Table(name = "LAST_RESULTS_PLAYER")
public class LastResult{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESULT")
    private long id;

    @Getter
    @Setter
    @Column(name = "PLAYER_RESULT")
    private long player;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "OPPONENT_RESULT", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player opponent;

    @Getter
    @Setter
    @Column(name = "RESULT_RESULT")
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
