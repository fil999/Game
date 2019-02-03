package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Created by --- on 25.11.2018.
 */
public class Friend {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long player1;

    @Getter
    @Setter
    private Player player2;

    @Getter
    @Setter
    private int winsPlayer1;

    @Getter
    @Setter
    private int winsPlayer2;

    @Getter
    @Setter
    private int tie;

    @Getter
    @Setter
    private Collection<ResultsFriends> resultsFriends;

    public Friend() {
    }

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

    @Override
    public boolean equals(Object other){
        Friend friend = (Friend) other;
        if (player2.getEmail().equals(friend.player2.getEmail())){
            return true;
        }
        return false;
    }
}
