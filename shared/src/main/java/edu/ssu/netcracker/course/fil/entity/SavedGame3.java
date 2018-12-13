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
@Table(name = "SAVED_GAME_3_PLAYER")
public class SavedGame3 {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SAVED_3")
    private long id;

    @Getter
    @Setter
    @Column(name = "PLAYER_1_SAVED_3")
    private long player1;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_2_SAVED_3", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player2;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_3_SAVED_3", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player3;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_1_SAVED_3")
    private String cardPlayer1;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_2_SAVED_3")
    private String cardPlayer2;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_3_SAVED_3")
    private String cardPlayer3;

    @Getter
    @Setter
    @Column(name = "CARDS_RESIDUAL_SAVED_3")
    private String cardResidual;

    @Getter
    @Setter
    @Column(name = "TRUMP_CARD_SAVED_3")
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
