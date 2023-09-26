package com.stemmer.pokergame.helper;

import com.stemmer.pokergame.model.Card;
import com.stemmer.pokergame.model.Hand;

import java.util.Comparator;
import java.util.List;

public abstract class HandCalculator {

    public class Rank {
        private HandRanking rank;
        private List<Card> hand;

        public Rank(HandRanking rank, List<Card> hand) {
            this.rank = rank;
            this.hand = hand;
        }

        public HandRanking getRank() {
            return rank;
        }

        public List<Card> getHand() {
            return hand;
        }
    }

    public enum HandRanking {
        STRAIGHT_FLUSH, QUAD, FULL_HOUSE, FLUSH, STRAIGHT, SET, DOUBLE_PAIR, PAIR, HIGH_CARD
    }

    public abstract List<Card> hasPair(Hand hand);
    public abstract List<Card> hasDoublePair(Hand hand);
    public abstract List<Card> hasSet(Hand hand);
    public abstract List<Card> hasQuad(Hand hand);
    public abstract List<Card> hasFullHouse(Hand hand);
    public abstract List<Card> hasStraight(Hand hand);
    public abstract List<Card> hasFlush(Hand hand);

    public Rank getHandRanking(Hand hand){

        List<Card> pair = hasPair(hand);
        List<Card> doublePair = hasDoublePair(hand);
        List<Card> set = hasSet(hand);
        List<Card> quad = hasQuad(hand);
        List<Card> fullHouse = hasFullHouse(hand);
        List<Card> straight = hasStraight(hand);
        List<Card> flush = hasFlush(hand);
        if(!straight.isEmpty() && !flush.isEmpty() && straight.get(0).equals(flush.get(0)))
            return new Rank(HandRanking.STRAIGHT_FLUSH, straight);
        else if(!quad.isEmpty())
            return new Rank(HandRanking.QUAD, quad);
        else if(!fullHouse.isEmpty())
            return new Rank(HandRanking.FULL_HOUSE, fullHouse);
        else if(!flush.isEmpty())
            return new Rank(HandRanking.FLUSH, flush);
        else if (!straight.isEmpty())
            return new Rank(HandRanking.STRAIGHT, straight);
        else if(!set.isEmpty())
            return new Rank(HandRanking.SET, set);
        else if(!doublePair.isEmpty())
            return new Rank(HandRanking.DOUBLE_PAIR, doublePair);
        else if(!pair.isEmpty())
            return new Rank(HandRanking.PAIR, pair);
        else {
            List<Card> highCardHand = hand.getTotalHandCards();
            highCardHand.sort(Comparator.reverseOrder());

            System.out.println(highCardHand);
            highCardHand.remove(5);
            highCardHand.remove(5);

            return new Rank(HandRanking.HIGH_CARD, highCardHand);
        }
    }

    public int compareHands(Hand hand1, Hand hand2){

        Rank r1 = getHandRanking(hand1);
        Rank r2 = getHandRanking(hand2);

        if(r1.getRank().ordinal() == r2.getRank().ordinal()) {
            for(int i = 0; i < 5; i++){
                Card card1 = r1.getHand().get(i);
                Card card2 = r2.getHand().get(i);

                if(card1.getValue().ordinal() == card2.getValue().ordinal())
                    continue;

                return card1.getValue().ordinal() > card2.getValue().ordinal() ? 1 : -1;
            }
            return 0;
        }

        return r1.getRank().ordinal() < r2.getRank().ordinal() ? 1 : -1;
    }

}
