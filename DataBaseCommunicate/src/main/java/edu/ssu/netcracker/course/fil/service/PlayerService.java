package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.controller.*;
import edu.ssu.netcracker.course.fil.entity.*;
import edu.ssu.netcracker.course.fil.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by --- on 29.11.2018.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;


    private List<Resource<Player>> playerResource(Player player){
        Resource<Player> resource = new Resource<>(player);
        resource.add(linkTo(methodOn(PlayerController.class).updatePlayer(player)).withRel("update"));
        resource.add(linkTo(methodOn(BackController.class).selectAll()).withRel("backs"));
        resource.add(linkTo(methodOn(FaceController.class).selectAll()).withRel("faces"));
        resource.add(linkTo(methodOn(GamingTableController.class).selectAllGamingTable()).withRel("gamingTables"));
        resource.add(linkTo(methodOn(CommentController.class).insertComment(new Comment())).withRel("addComment"));
        resource.add(linkTo(methodOn(FriendController.class).insertFriend(player.getId(), new String())).withRel("addFriend"));
        List<Resource<Player>> list = new ArrayList<>();
        list.add(resource);
        return list;
    }

    private String pass(String password){
        String s = String.valueOf(password.hashCode());
        int k = 64 - s.length();
        for (int i = 0; i < k; i++){
            s+= " ";
        }
        return s;
    }

	//TODO: зарефачить и раскидать логику по методам
    public Player selectByEmailAndPassword(Player player){
        if ((player.getEmail() != null) && (player.getPassword() != null)) {
           // player.setPassword(String.valueOf(player.getPassword().hashCode()));
            player.setPassword(pass(player.getPassword()));
            player = playerRepository.findPlayerByEmailAndPassword(player.getEmail(), player.getPassword());
            for(LastResult lastResult : player.getLastResults()){
                Player opponent = lastResult.getOpponent();
                lastResult.setOpponent(new Player(opponent.getId(), opponent.getEmail(), opponent.getName()));
            }
            for (SavedGame2 game: player.getSavedGame2()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (SavedGame3 game: player.getSavedGame3()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer3();
                game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (SavedGame4 game: player.getSavedGame4()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer3();
                game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer4();
                game.setPlayer4(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (Friend friend: player.getFriends()){
                Player gamePlayer = friend.getPlayer2();
                friend.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                friend.getResultsFriends();
            }
            return player;
        }
        else {
            return null;
        }
    }

    private void resetExcess(Player player){
        for(LastResult lastResult : player.getLastResults()){
            Player opponent = lastResult.getOpponent();
            lastResult.setOpponent(new Player(opponent.getId(), opponent.getEmail(), opponent.getName()));
        }
        for (SavedGame2 game: player.getSavedGame2()){
            Player gamePlayer = game.getPlayer2();
            game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
        }
        for (SavedGame3 game: player.getSavedGame3()){
            Player gamePlayer = game.getPlayer2();
            game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            gamePlayer = game.getPlayer3();
            game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
        }
        for (SavedGame4 game: player.getSavedGame4()){
            Player gamePlayer = game.getPlayer2();
            game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            gamePlayer = game.getPlayer3();
            game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            gamePlayer = game.getPlayer4();
            game.setPlayer4(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
        }
        for (Friend friend: player.getFriends()){
            Player gamePlayer = friend.getPlayer2();
            friend.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            friend.getResultsFriends();
        }
    }

    public List<Resource<Player>> findById(long id){
        if (id>0) {
            Player player = playerRepository.findPlayerById(id);
            resetExcess(player);
           /* for(LastResult lastResult : player.getLastResults()){
                Player opponent = lastResult.getOpponent();
                lastResult.setOpponent(new Player(opponent.getId(), opponent.getEmail(), opponent.getName()));
            }
            for (SavedGame2 game: player.getSavedGame2()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (SavedGame3 game: player.getSavedGame3()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer3();
                game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (SavedGame4 game: player.getSavedGame4()){
                Player gamePlayer = game.getPlayer2();
                game.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer3();
                game.setPlayer3(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                gamePlayer = game.getPlayer4();
                game.setPlayer4(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
            }
            for (Friend friend: player.getFriends()){
                Player gamePlayer = friend.getPlayer2();
                friend.setPlayer2(new Player(gamePlayer.getId(), gamePlayer.getEmail(), gamePlayer.getName()));
                friend.getResultsFriends();
            }*/
            return playerResource(player);
        }
        else {
            return null;
        }
    }

    public List<Resource<Long>> autoization(String email, String password){
        password = pass(password);
        /*long count = playerRepository.countPlayerByEmailAndPassword(email, password);
        if (count == 0){
            return false;
        } else {
            return true;
        }*/
        Long id = playerRepository.selectIdPlayer(email, password);
        if (id == null){
           id = new Long(-1);
        }
        Resource<Long> resource = new Resource<>(id);
        resource.add(linkTo(methodOn(PlayerController.class).findPlayer(id)).withRel("findPlayer"));
        List<Resource<Long>> list = new ArrayList<>();
        list.add(resource);
        return list;
    }


    public List<Resource<Player>> updatePlayer(Player player) {
        if ((player.getEmail() != null) && (player.getPassword() != null) && (player.getName() != null)) {
            Player player1 = playerRepository.findPlayerById(player.getId());
            player.setLastResults(player1.getLastResults());
            player.setWins(player1.getWins());
            player.setLosing(player1.getLosing());
            player.setTie(player1.getTie());
            player.setBalance(player1.getBalance());
            player.setFriends(player1.getFriends());
            if (!player1.getPassword().equals(player.getPassword())){
                player.setPassword(String.valueOf(player.getPassword().hashCode()));
            }
            player = playerRepository.save(player);
            resetExcess(player);
            return playerResource(player);
        }
        return null;
    }


   /* public List<Resource<Player>> updatePlayer(Player player) {
        if ((player.getEmail() != null) && (player.getPassword() != null) && (player.getName() != null)) {
            Player player1 = playerRepository.findPlayerById(player.getId());
            player1.setEmail(player.getEmail());
            player1.setName(player.getName());
            if (!player1.getPassword().equals(player.getPassword())){
                player1.setPassword(String.valueOf(player.getPassword().hashCode()));
            }
            player = playerRepository.save(player1);
            resetExcess(player);
            return playerResource(player);
        }
        return null;
    }
*/
    public List<Resource<Player>> createPlayer(Player player) {
        if ((player.getEmail() != null) && (player.getPassword() != null) && (player.getName() != null)) {
            player.setPassword(String.valueOf(player.getPassword().hashCode()));
            player = playerRepository.save(player);
            return findById(player.getId());
        }
        return null;
    }

}
