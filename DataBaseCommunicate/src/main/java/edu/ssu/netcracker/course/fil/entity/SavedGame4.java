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
@Table(name = "SAVED_GAME_4_PLAYER")
public class SavedGame4 {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SAVED_4")
    private long id;

    @Getter
    @Setter
    @Column(name = "PLAYER_1_SAVED_4")
    private long player1;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_2_SAVED_4", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player2;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_3_SAVED_4", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player3;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_4_SAVED_4", referencedColumnName = "ID_PLAYER", updatable = false)
    private Player player4;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_1_SAVED_4")
    private String cardPlayer1;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_2_SAVED_4")
    private String cardPlayer2;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_3_SAVED_4")
    private String cardPlayer3;

    @Getter
    @Setter
    @Column(name = "CARDS_PLAYER_4_SAVED_4")
    private String cardPlayer4;

    @Getter
    @Setter
    @Column(name = "CARDS_RESIDUAL_SAVED_4")
    private String cardResidual;

    @Getter
    @Setter
    @Column(name = "TRUMP_CARD_SAVED_4")
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
