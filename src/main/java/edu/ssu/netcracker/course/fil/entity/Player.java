package edu.ssu.netcracker.course.fil.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

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
    private Collection<Back> backs;

    @Getter
    @Setter
    private Collection<Face> faces;

    @Getter
    @Setter
    private Collection<GamingTable> tables;

    @Getter
    @Setter
    private Collection<LastResult> lastResults;

    @Getter
    @Setter
    private Collection<SavedGame2> savedGame2;

    @Getter
    @Setter
    private Collection<SavedGame3> savedGame3;

    @Getter
    @Setter
    private Collection<SavedGame4> savedGame4;

    @Getter
    @Setter
    private Collection<Friend> friends;

    public Player(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Player(String email, int bot, String name, String password, int wins, int losing, int tie, long balance, Collection<Back> backs, Collection<Face> faces, Collection<GamingTable> tables, Collection<LastResult> lastResults, Collection<SavedGame2> savedGame2, Collection<SavedGame3> savedGame3, Collection<SavedGame4> savedGame4, Collection<Friend> friends) {
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
