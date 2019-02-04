package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.play.Play;
import edu.ssu.netcracker.course.fil.play.PlayTransfer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by --- on 03.02.2019.
 */
@Service
public class BotService {

    private static List<Play> plays = new LinkedList<>();

    public boolean newBot(long idGame, boolean transfer){
        Play play;
        if (transfer){
            play = new PlayTransfer(idGame);
        } else {
            play = new Play(idGame);
        }
        plays.add(play);
        return true;
    }

    public void deleteBot(Play play){
        int index = plays.indexOf(play);
        if (index != -1){
            plays.remove(index);
        }
    }
}
