package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "FRIEND")
public class Friend {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FRIEND")
    private long id;

    @Getter
    @Setter
    @Column(name = "PLAYER1_FRIEND")
    private long player1;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER2_FRIEND", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player2;

    @Getter
    @Setter
    @Column(name = "WINS_PLAYER1_FRIEND")
    private int winsPlayer1;

    @Getter
    @Setter
    @Column(name = "WINS_PLAYER2_FREIND")
    private int winsPlayer2;

    @Getter
    @Setter
    @Column(name = "TIE_FRINED")
    private int tie;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "FRIEND_RESULTS_FRIEND")
    private Collection<ResultsFriends> resultsFriends;


    public Friend(long player1, Player player2, int winsPlayer1, int winsPlayer2, int tie, Collection<ResultsFriends> resultsFriends) {
        this.player1 = player1;
        this.player2 = player2;
        this.winsPlayer1 = winsPlayer1;
        this.winsPlayer2 = winsPlayer2;
        this.tie = tie;
        this.resultsFriends = resultsFriends;
    }


    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", winsPlayer1=" + winsPlayer1 +
                ", winsPlayer2=" + winsPlayer2 +
                ", tie=" + tie +
                ", resultsFriends=" + resultsFriends +
                '}';
    }
}
