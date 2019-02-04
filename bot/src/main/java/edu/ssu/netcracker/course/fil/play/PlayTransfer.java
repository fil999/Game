package edu.ssu.netcracker.course.fil.play;

import edu.ssu.netcracker.course.fil.forgame.Card;

/**
 * Created by --- on 03.02.2019.
 */
public class PlayTransfer extends Play {

    public PlayTransfer(long idGame) {
        super(idGame);
    }

    private Card transfer(Card card){
        for (Card card1 : cards){
            if ((card1.getNumber() == card.getNumber()) && (card1.getSuit() != trump.getSuit())){
                return card1;
            }
        }
        return null;
    }


    @Override
    public void setCanCovered() {
        //sleep();
        Card card = transfer(cardsOnTable.get(0));
        if (card != null) {
            cards.remove(card);
            requests.transfer(card, idPlayer);
        } else {
            int size = countTransfer + 1;
            if (size > 1) {
                for (int i = 0; i < size; i++) {
                    card = cardsOnTable.get(i);
                    covered(card);
                }
            } else {
                card = cardsOnTable.get(cardsOnTable.size()-1);
                covered(card);
            }
            countTransfer = 0;
        }
    }
}
