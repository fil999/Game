package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class SavedGame4 {

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
    private Player player4;

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
    private String cardPlayer4;

    @Getter
    @Setter
    private String cardResidual;

    @Getter
    @Setter
    private String trump;


    public SavedGame4(long player1, Player player2, Player player3, Player player4, String cardPlayer1, String cardPlayer2, String cardPlayer3, String cardPlayer4, String cardResidual, String trump) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.cardPlayer1 = cardPlayer1;
        this.cardPlayer2 = cardPlayer2;
        this.cardPlayer3 = cardPlayer3;
        this.cardPlayer4 = cardPlayer4;
        this.cardResidual = cardResidual;
        this.trump = trump;
    }

    @Override
    public String toString() {
        return "SavedGame4{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", player3=" + player3 +
                ", player4=" + player4 +
                ", cardPlayer1='" + cardPlayer1 + '\'' +
                ", cardPlayer2='" + cardPlayer2 + '\'' +
                ", cardPlayer3='" + cardPlayer3 + '\'' +
                ", cardPlayer4='" + cardPlayer4 + '\'' +
                ", cardResidual='" + cardResidual + '\'' +
                ", trump='" + trump + '\'' +
                '}';
    }
}
