package edu.ssu.netcracker.course.fil.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
@Entity
@Table(name = "PLAYER")
public class Player{

    @Getter
    @Setter
    @Id
    @Column(name = "ID_PLAYER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "EMAIL_PLAYER", length = 50, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(name = "BOT")
    private int bot;

    @Getter
    @Setter
    @Column(name = "NAME_PLAYER", length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name = "PASSWORD_PLAYER")
    private String password;

    @Getter
    @Setter
    @Column(name = "WINS_PLAYER")
    private int wins;

    @Getter
    @Setter
    @Column(name = "LOSING_PLAYER")
    private int losing;

    @Getter
    @Setter
    @Column(name = "TIE_PLAYER")
    private int tie;

    @Getter
    @Setter
    @Column(name = "BALANCE")
    private long balance;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "BOUGHT_BACK", joinColumns = @JoinColumn(name = "PLAYER_BOUGHT_BACK"), inverseJoinColumns = @JoinColumn(name = "BACK_BOUGHT_BACK"))
    private List<Back> backs;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "BOUGHT_FACE", joinColumns = @JoinColumn(name = "PLAYER_BOUGHT_FACE"), inverseJoinColumns = @JoinColumn(name = "FACE_BOUGHT_FACE"))
    private List<Face> faces;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "BOUGHT_GAMING_TABLE", joinColumns = @JoinColumn(name = "PLAYER_BOUGHT_TABLE"), inverseJoinColumns = @JoinColumn(name = "TABLE_BOUGHT_TABLE"))
    private List<GamingTable> tables;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_RESULT")
    private List<LastResult> lastResults;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_1_SAVED_2")
    private List<SavedGame2> savedGame2;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_1_SAVED_3")
    private List<SavedGame3> savedGame3;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_1_SAVED_4")
    private List<SavedGame4> savedGame4;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER1_FRIEND")
    private List<Friend> friends;

    @Getter
    @Setter
    @Column(name = "GAMING_TABLE")
    private long playerTable;

    @Getter
    @Setter
    @Column(name = "BACK")
    private long playerBack;

    @Getter
    @Setter
    @Column(name = "FACE")
    private long playerFace;


    public Player(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Player(long id, String email, int bot, String name, String password, int wins, int losing, int tie, long balance, List<Back> backs, List<Face> faces, List<GamingTable> tables, List<LastResult> lastResults, List<SavedGame2> savedGame2, List<SavedGame3> savedGame3, List<SavedGame4> savedGame4, List<Friend> friends, long playerTable, long playerBack, long playerFace) {
        this.id = id;
        this.email = email;
        this.bot = bot;
        this.name = name;
        this.password = password;
        this.wins = wins;
        this.losing = losing;
        this.tie = tie;
        this.balance = balance;
        this.backs = backs;
        this.faces = faces;
        this.tables = tables;
        this.lastResults = lastResults;
        this.savedGame2 = savedGame2;
        this.savedGame3 = savedGame3;
        this.savedGame4 = savedGame4;
        this.friends = friends;
        this.playerTable = playerTable;
        this.playerBack = playerBack;
        this.playerFace = playerFace;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
               /* ", email_player='" + email_player + '\'' +
                ", bot=" + bot +
                ", name_player='" + name_player + '\'' +
                ", password_player='" + password_player + '\'' +
                ", wins_player=" + wins_player +
                ", losing_player=" + losing_player +
                ", tie_player=" + tie_player +
                ", balance=" + balance +
                ", backs=" + backs +
                ", faces=" + faces +
                ", tables=" + tables +
                ", lastResults=" + lastResults + */
              //  ", savedGame2=" + savedGame2 +
              //  ", savedGame3=" + savedGame3 +
              //  ", savedGame4=" + savedGame4 +
                //", friends=" + friends +
                '}';
    }
}
