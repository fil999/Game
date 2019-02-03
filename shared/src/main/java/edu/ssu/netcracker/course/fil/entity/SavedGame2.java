package edu.ssu.netcracker.course.fil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class SavedGame2{

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
    private String cardPlayer1;

    @Getter
    @Setter
    private String cardPlayer2;

    @Getter
    @Setter
    private String cardResidual;

    @Getter
    @Setter
    private String trump;


    public SavedGame2(long player1, Player player2, String cardPlayer1, String cardPlayer2, String cardResidual, String trump) {
        this.player1 = player1;
        this.player2 = player2;
        this.cardPlayer1 = cardPlayer1;
        this.cardPlayer2 = cardPlayer2;
        this.cardResidual = cardResidual;
        this.trump = trump;
    }


}
