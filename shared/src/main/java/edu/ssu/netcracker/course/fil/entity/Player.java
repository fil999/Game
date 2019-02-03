package edu.ssu.netcracker.course.fil.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by --- on 25.11.2018.
 */
@NoArgsConstructor
public class Player{

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private int bot;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private int wins;

    @Getter
    @Setter
    private int losing;

    @Getter
    @Setter
    private int tie;

    @Getter
    @Setter
    private long balance;

    @Getter
    @Setter
    private List<Back> backs;

    @Getter
    @Setter
    private List<Face> faces;

    @Getter
    @Setter
    private List<GamingTable> tables;

    @Getter
    @Setter
    private List<LastResult> lastResults;

    @Getter
    @Setter
    private List<SavedGame2> savedGame2;

    @Getter
    @Setter
    private List<SavedGame3> savedGame3;

    @Getter
    @Setter
    private List<SavedGame4> savedGame4;

    @Getter
    @Setter
    private List<Friend> friends;

    @Getter
    @Setter
    private long playerTable;

    @Getter
    @Setter
    private long playerBack;

    @Getter
    @Setter
    private long playerFace;

    public Player(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Player(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        playerTable = 1;
        playerBack = 1;
        playerFace = 1;
        tables = new ArrayList<>();
        tables.add(new GamingTable(1));
        faces = new ArrayList<>();
        faces.add(new Face(1));
        backs = new ArrayList<>();
        backs.add(new Back(1));
        friends = new ArrayList<>();
        lastResults = new ArrayList<>();
        savedGame2 = new ArrayList<>();
        savedGame3 = new ArrayList<>();
        savedGame4 = new ArrayList<>();
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
