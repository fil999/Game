package edu.ssu.netcracker.course.fil.data;

import edu.ssu.netcracker.course.fil.entity.Player;
import org.springframework.hateoas.Resource;


/**
 * Created by --- on 13.01.2019.
 */
public class DataPlayer {

    private Resource<Player> player;

    public DataPlayer(){
        player = null;
    }

    public void setPlayer(Resource<Player> player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player.getContent();
    }

    public String getHref(String rel){
        return player.getLink(rel).getHref();
    }

    public boolean isLoged(){
        if (player == null){
            return false;
        }
        return true;
    }
}
