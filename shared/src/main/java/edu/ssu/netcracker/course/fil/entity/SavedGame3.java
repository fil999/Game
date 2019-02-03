package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class SavedGame3 {

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
    private Player player3;

    @Getter
    @Setter
    private String cardPlayer1;

    @Getter
    @Setter
    private String cardPlayer2;

    @Getter
    @Setter
    private String cardPlayer3;

    @Getter
    @Setter
    private String cardResidual;

    @Getter
    @Setter
    private String trump;


    public SavedGame3(long player1, Player player2, Player player3, String cardPlayer1, String cardPlayer2, String cardPlayer3, String cardResidual, String trump) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.cardPlayer1 = cardPlayer1;
        this.cardPlayer2 = cardPlayer2;
        this.cardPlayer3 = cardPlayer3;
        this.cardResidual = cardResidual;
        this.trump = trump;
    }

    @Override
    public String toString() {
        return "SavedGame3{" +
                "ID_saved=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", player3=" + player3 +
                ", cardPlayer1='" + cardPlayer1 + '\'' +
                ", cardPlayer2='" + cardPlayer2 + '\'' +
                ", cardPlayer3='" + cardPlayer3 + '\'' +
                ", cardResidual='" + cardResidual + '\'' +
                ", trump='" + trump + '\'' +
                '}';
    }
}
