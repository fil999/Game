package edu.ssu.netcracker.course.fil.service;

import edu.ssu.netcracker.course.fil.controller.GameController;
import edu.ssu.netcracker.course.fil.game.Card;
import edu.ssu.netcracker.course.fil.game.Game;
import edu.ssu.netcracker.course.fil.game.PlayerGame;
import edu.ssu.netcracker.course.fil.requests.RequestBot;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by --- on 18.12.2018.
 */
@Service
public class GameService {

    private static long idGame = 1;

    private static List<Game> games = new LinkedList<>();
//    private static Game game2;
//    private static Game game3;
//    private static Game game4;
//    private static Game game2transfer;
//    private static Game game3transfer;
//    private static Game game4transfer;

    private static List<WaitGame> waitGames = new LinkedList<>();


    private Game findGame(long id) {
        for (Game game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }

    public Long createGame(int count, boolean transfer) {
        synchronized (waitGames) {
            long idGame;
            int index = waitGames.indexOf(new WaitGame(null, count, transfer));
            if (index != -1) {
                WaitGame waitGame = waitGames.get(index);
                if (waitGame.isAdd()) {
                    idGame = waitGame.add();
                    return idGame;
                }
            }
            WaitGame waitGame = new WaitGame(new Game(this.idGame, count, transfer), count, transfer);
            this.idGame++;
            idGame = waitGame.add();
            waitGames.add(waitGame);
            new Thread(waitGame).start();
            return idGame;
        }
    }

    public void addPlayer(Socket socket, long idPlayer, long idGame, int who) {
        if (who == 1) {
            Game game = findGame(idGame);
            if (idPlayer == -1) {
                idPlayer = -game.getCountPlayers();
            }
            PlayerGame playerGame = new PlayerGame(idPlayer, socket, game.getPlayers().size());
            game.addPlayer(playerGame);
            if (game.getCount() == game.getCountPlayers()) {
                startGame(game);
            }
        } else {
            WaitGame waitGame = null;
            for (WaitGame waitGame1 : waitGames) {
                if (waitGame1.itGame(idGame)) {
                    waitGame = waitGame1;
                    break;
                }
            }
            if (waitGame != null) {
                if (idPlayer == -1) {
                    idPlayer = -waitGame.game.getCountPlayers();
                }
                PlayerGame playerGame = new PlayerGame(idPlayer, socket, waitGame.game.getPlayers().size());
                waitGame.addPlayer(playerGame);
            }
        }
    }


//   /* private Resource<Boolean> gameResource(long id){
//        Resource<Boolean> resource = new Resource<>(true);
//        resource.add(linkTo(methodOn(GameController.class).requestCards(id, 0)).withRel("requestCards"));
//        resource.add(linkTo(methodOn(GameController.class).requestTrump(id)).withRel("requestTrump"));
//       // resource.add(linkTo(methodOn(GameController.class).isCover(id, null, null)).withRel("isCover"));
//        return resource;
//    }
//
//    public Resource<Boolean> createGame(){
//        long id;
//        do {
//            id = Math.abs(new Random().nextLong());
//        } while (findGame(id) != null);
//        games.add(new Game(id));
//        return gameResource(id);
//    }*/
//
//    private long addGame(int count){
//        long id = -1;
//        switch (count) {
//            case 2:
//                if (game2 == null) {
//                    game2 = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game2.getId();
//                break;
//            case 3:
//                if (game3 == null) {
//                    game3 = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game3.getId();
//                break;
//            case 4:
//                if (game4 == null) {
//                    game4 = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game4.getId();
//                break;
//            default:
//                if (game2 == null) {
//                    game2 = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game2.getId();
//                break;
//        }
//        return id;
//    }
//
//    private long addGameTransfer(int count){
//        long id = -1;
//        switch (count) {
//            case 2:
//                if (game2transfer == null) {
//                    game2transfer = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game2transfer.getId();
//                break;
//            case 3:
//                if (game3transfer == null) {
//                    game3transfer = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game3transfer.getId();
//                break;
//            case 4:
//                if (game4transfer == null) {
//                    game4transfer = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game4transfer.getId();
//                break;
//            default:
//                if (game2transfer == null) {
//                    game2transfer = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = game2transfer.getId();
//                break;
//        }
//        return id;
//    }
//
//    /*private long addGame(int count, Game ... addGames){
//        long id = -1;
//        switch (count) {
//            case 2:
//                if (addGames[0] == null) {
//                    addGames[0] = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = addGames[0].getId();
//                break;
//            case 3:
//                if (addGames[1] == null) {
//                    addGames[1] = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = addGames[1].getId();
//                break;
//            case 4:
//                if (addGames[2] == null) {
//                    addGames[2] = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = addGames[2].getId();
//                break;
//            default:
//                if (addGames[0] == null) {
//                    addGames[0] = new Game(idGame, count, false);
//                    idGame++;
//                }
//                id = addGames[0].getId();
//                break;
//        }
//        return id;
//    }*/
//
//    /*public Game findGameByCount(int count){
//        switch (count){
//            case 2:
//                return game2;
//            case 3:
//                return game3;
//            case 4:
//                return game4;
//            default:
//                return game2;
//        }
//    }*/
//
//    public Game findGameByCount(int count, Game ... findGames){
//        switch (count){
//            case 2:
//                return findGames[0];
//            case 3:
//                return findGames[1];
//            case 4:
//                return findGames[2];
//            default:
//                return findGames[0];
//        }
//    }
//
//
//    private void deleteGame(int count, int transfer){
//        if (transfer == 0){
//            switch (count) {
//                case 2:
//                    game2transfer = null;
//                case 3:
//                    game3transfer = null;
//                case 4:
//                    game4transfer = null;
//                default:
//                    game2transfer = null;
//            }
//        } else {
//            switch (count) {
//                case 2:
//                    game2 = null;
//                case 3:
//                    game3 = null;
//                case 4:
//                    game4 = null;
//                default:
//                    game2 = null;
//            }
//        }
//    }
//
//    public long createGame(int count, boolean transfer){
//        if (transfer){
//            return addGameTransfer(count);
//        }
//        else {
//            return addGame(count);
//        }
//    }

    private void startGame(Game game){
        for (int i = 0; i < game.getCount(); i++){
            try {
                PlayerGame playerGame = game.getPlayers().get(i);
                playerGame.getOutputStream().write(0);
                byte[] bytes = String.valueOf(playerGame.getId()).getBytes();
                playerGame.getOutputStream().write(bytes.length);
                playerGame.getOutputStream().write(bytes);
                playerGame.getOutputStream().write(playerGame.getNum());
                playerGame.getOutputStream().write(game.getCount());
                //playerGame.getOutputStream().write((int)playerGame.getId());
                playerGame.getOutputStream().flush();
                //new ObjectOutputStream(playerGame.getOutputStream()).writeLong(playerGame.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        game.startGame();
    }

    public long startGameFriends(List<Long> list){
        Game game = new Game(idGame++, list.size()+1, true);
        games.add(game);
        for (long id : list){
            try {
                OutputStream stream = MainSockets.getSocket(id).getOutputStream();
                stream.write(1);
                byte[] bytes = String.valueOf(game.getId()).getBytes();
                stream.write(bytes.length);
                stream.write(bytes);
                //stream.write(String.valueOf(idGame).getBytes());
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return game.getId();
    }

//    private void addPlayer(Game game, Socket socket, long idPlayer){
//        if (idPlayer == -1){
//            idPlayer = -game.getCountPlayers();
//        }
//        PlayerGame playerGame = new PlayerGame(idPlayer, socket, game.getPlayers().size());
//        game.addPlayer(playerGame);
//    }
//
//    public void addPlayer(Socket socket, long idPlayer, long idGame){
//        Game game = findGame(idGame);
//        addPlayer(game, socket, idPlayer);
//        if(game.getCount() == game.getCountPlayers()) {
//            startGame(game);
//        }
//    }
//
//
//    public void addPlayer(Socket socket,  long idPlayer, int count, int transfer){
//        Game game;
//        if (transfer == 0){
//            game = findGameByCount(count, game2transfer, game3transfer, game4transfer);
//        } else {
//            game = findGameByCount(count, game2, game3, game4);
//        }
//        addPlayer(game, socket, idPlayer);
//        if(game.getCount() == game.getCountPlayers()){
//            games.add(game);
//            startGame(game);
//            deleteGame(count, transfer);
//           // game = null;
//        }
//        //findGameByCount(count).addPlayer(playerGame);
//    }

    public List<Card> requestCards(long idGame, int count, long idPlayer){
        if (count > 0) {
            Game game = findGame(idGame);
            if (game != null) {
                return game.requestCards(count, idPlayer);
            }
        }
        return null;
    }

    public Card requestTrump(long idGame){
        Game game = findGame(idGame);
        if (game != null) {
            return game.requestTrump();
        }
        return null;
    }

    public void exitGame(Game game){
        games.remove(game);
    }
   /* public Boolean isCover(long idGame, List<Card> cards){
        if (cards.size() > 1) {
            Game game = findGame(idGame);
            if (game != null) {
                Card under = cards.get(0);
                Card over = cards.get(1);
                return game.isCover(under, over);
            }
        }
        return null;
    }

    public Boolean isPut(long idGame, List<Card> cards){
        if (cards.size() > 2) {
            Game game = findGame(idGame);
            if (game != null) {
                Card wantsPut = cards.remove(cards.size()-1);
                return game.isPut(cards, wantsPut);
            }
        }
        return null;
    }*/

    public Boolean putCard(long idGame, long idPlayer, Card card){
        Game game = findGame(idGame);
        if (game != null) {
            return game.putCard(card, idPlayer);
        }
        return false;
    }

    public Boolean cover(long idGame, long idPlayer, Card card){
        Game game = findGame(idGame);
        if (game != null) {
            return game.cover(card, idPlayer);
        }
        return false;
    }

    public List<Card> takeCards(long idGame, long idPlayer){
        Game game = findGame(idGame);
        if (game != null) {
            List<Card> cards = game.takeCards(idPlayer);
            if (cards != null){
                return cards;
            }
        }
        return new ArrayList<>();
    }

    public Boolean setExit(long idGame){
        Game game = findGame(idGame);
        if (game != null) {
            game.setExit();
            return true;
        }
        return false;
    }

    public boolean transfer(long idGame, long idPlayer, Card card){
        Game game = findGame(idGame);
        if (game != null) {
            return game.transfer(card, idPlayer);
        }
        return false;
    }


    private class WaitGame implements Runnable{

        private Game game;
        private int count;
        private int nowCount;
        private boolean transfer;
        private boolean mayAdd;

        public WaitGame(Game game, int count, boolean transfer) {
            this.game = game;
            this.count = count;
            this.nowCount = 0;
            this.transfer = transfer;
            this.mayAdd = true;
        }

        public boolean isAdd(){
            return mayAdd;
        }

        public long add(){
            nowCount++;
            if (count == nowCount){
                mayAdd = false;
            }
            return game.getId();
        }

        public boolean itGame(long idGame){
            if (game.getId() == idGame){
                return true;
            }
            return false;
        }

        public void addPlayer(PlayerGame playerGame){
            game.addPlayer(playerGame);
            if (game.getPlayers().size() == count){
                games.add(game);
                waitGames.remove(this);
                startGame(game);
                Thread.currentThread().interrupt();
            }
        }


        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                int size = count - nowCount;
                for (int i = 0; i < size; i++) {
                    new RequestBot().newBot(game.getId(), transfer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public boolean equals(Object obj) {
            WaitGame other = (WaitGame) obj;
            if (count == other.count && transfer == other.transfer){
                return true;
            }
            return false;
        }
    }
}
