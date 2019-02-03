package edu.ssu.netcracker.course.fil.game;

import edu.ssu.netcracker.course.fil.Card;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by --- on 12.01.2019.
 */
public class GameSocket implements Runnable{

    private Game game;
    private int count;
    private boolean friends;

    public GameSocket(Game game, int count){
        this.game = game;
        this.count = count;
        this.friends = false;
    }

    public GameSocket(Game game){
        this.game = game;
        this.friends = true;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8083);
            byte[] bytes = String.valueOf(game.getIdPlayer()).getBytes();
            socket.getOutputStream().write(bytes.length);
            socket.getOutputStream().write(bytes);
            if (game.isTransfer()){
                socket.getOutputStream().write(0);
            } else {
                socket.getOutputStream().write(1);
            }
            if (friends){
                socket.getOutputStream().write(1);
                bytes = String.valueOf(game.getIdGame()).getBytes();
                socket.getOutputStream().write(bytes.length);
                socket.getOutputStream().write(bytes);
            } else {
                socket.getOutputStream().write(0);
                socket.getOutputStream().write(count);
            }
            socket.getOutputStream().flush();
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int c = inputStream.read();
                switch (c) {
                    case 0:
                        int n = inputStream.read();
                        byte[] bytes1 = new byte[n];
                        inputStream.read(bytes1);
                        long id = Long.parseLong(new String(bytes1));
                        int myNum = inputStream.read();
                        int count = inputStream.read();
                        game.setCount(count);
                        game.setMyNum(myNum);
                        game.setIdPlayer(id);
                        game.setStartGame(true);
                        break;
                    case 1:
                        char suit = (char) inputStream.read();
                        int number = inputStream.read();
                        int num = inputStream.read();
                        Card card = new Card(number, suit);
                        int whatDo = inputStream.read();
                        game.cardOnTable(card, num, whatDo);
                        break;
                    case 2:
                        game.setCanToss(true);
                        break;
                    case 3:
                        game.setCanToss(false);
                        break;
                    case 4:
                        game.setCanCovered(true);
                        break;
                    case 5:
                        game.setCanCovered(false);
                        break;
                    case 6:
                        game.waiting();
                        break;
                    case 7:
                        game.isExit();
                        break;
                    case 8:
                        game.win();
                        socket.close();
                        return;
                    case 9:
                        game.lose();
                        socket.close();
                       return;
                    case 10:
                        int numOther = inputStream.read();
                        int countOther = inputStream.read();
                        game.updateOtherCards(numOther, countOther);
                        break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

