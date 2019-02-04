package edu.ssu.netcracker.course.fil.socket;

import edu.ssu.netcracker.course.fil.forgame.Card;
import edu.ssu.netcracker.course.fil.play.Play;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by --- on 03.02.2019.
 */
public class BotGameSocket implements Runnable {

    private boolean transfer;
    private long idGame;
    private Play play;

    public BotGameSocket(boolean transfer, long idGame, Play play) {
        this.transfer = transfer;
        this.idGame = idGame;
        this.play = play;
    }


    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8083);
            byte[] bytes = String.valueOf(-1).getBytes();
            socket.getOutputStream().write(bytes.length);
            socket.getOutputStream().write(bytes);
//            if (transfer) {
//                socket.getOutputStream().write(0);
//            } else {
//                socket.getOutputStream().write(1);
//            }
            socket.getOutputStream().write(0);
            bytes = String.valueOf(idGame).getBytes();
            socket.getOutputStream().write(bytes.length);
            socket.getOutputStream().write(bytes);
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
                        play.setIdPlayer(id);
                        play.startGame();
                       // game.setCount(count);
                       // game.setMyNum(myNum);
                        //game.setIdPlayer(id);
                       // game.setStartGame(true);
                        break;
                    case 1:
                        char suit = (char) inputStream.read();
                        int number = inputStream.read();
                        int num = inputStream.read();
                        Card card = new Card(number, suit);
                        int whatDo = inputStream.read();
                        play.cardOnTable(card, whatDo);
                       // game.cardOnTable(card, num, whatDo);
                        break;
                    case 2:
                       // game.setCanToss(true);
                        play.setCanToss();
                        break;
                    case 3:
                       // game.setCanToss(false);
                        break;
                    case 4:
                        //game.setCanCovered(true);
                        play.setCanCovered();
                        break;
                    case 5:
                       // game.setCanCovered(false);
                        break;
                    case 6:
                        play.waiting();
                        //game.waiting();
                        break;
                    case 7:
                        //game.isExit();
                        play.isExit();
                        break;
                    case 8:
                        //game.win();
                        socket.close();
                        play.exitGame();
                        return;
                    case 9:
                        //game.lose();
                        socket.close();
                        play.exitGame();
                        return;
                    case 10:
                        int numOther = inputStream.read();
                        int countOther = inputStream.read();
                        //game.updateOtherCards(numOther, countOther);
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
