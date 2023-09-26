package com.stemmer.pokergame.helper;

import com.stemmer.pokergame.model.Card;
import com.stemmer.pokergame.model.Hand;

import java.util.*;

import static com.stemmer.pokergame.model.Card.Value.ACE;

public class BasicHandCalculator extends HandCalculator{
    @Override
    public List<Card> hasPair(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();
        cardList.sort(Comparator.reverseOrder());
        List<Card> allCards = new LinkedList<>(cardList);
        allCards.sort(Comparator.reverseOrder());
        while(!cardList.isEmpty()){
            Card card1 = cardList.remove(0);

            for(Card card2: cardList){
                if(card1.getValue() == card2.getValue()) {
                    allCards.remove(card1);
                    allCards.remove(card2);
                    allCards.add(0, card1);
                    allCards.add(0, card2);

                    allCards.remove(5);
                    allCards.remove(5);
                    return allCards;
                }
            }
        }
        return new LinkedList<>();
    }

    @Override
    public List<Card> hasDoublePair(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();
        cardList.sort(Comparator.reverseOrder());
        List<Card> allCards = new LinkedList<>(cardList);
        allCards.sort(Comparator.reverseOrder());

        boolean firstPair = false;

        while(!cardList.isEmpty()){
            Card card1 = cardList.remove(0);

            for(Card card2: cardList){
                if(card1.getValue() == card2.getValue()) {

                    if(firstPair){
                        allCards.remove(card1);
                        allCards.remove(card2);
                        if(card1.getValue().ordinal() > allCards.get(0).getValue().ordinal()){
                            allCards.add(0, card1);
                            allCards.add(0, card2);
                        } else {
                            allCards.add(2, card1);
                            allCards.add(2, card2);
                        }

                        allCards.remove(5);
                        allCards.remove(5);
                        return allCards;
                    } else {
                        cardList.remove(card2);
                        allCards.remove(card1);
                        allCards.remove(card2);
                        allCards.add(0, card1);
                        allCards.add(0, card2);
                        firstPair = true;
                        break;
                    }
                }
            }
        }
        return new LinkedList<>();
    }

    @Override
    public List<Card> hasSet(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();
        cardList.sort(Comparator.reverseOrder());
        List<Card> allCards = new LinkedList<>(cardList);
        allCards.sort(Comparator.reverseOrder());
        while(!cardList.isEmpty()){
            Card card1 = cardList.remove(0);

            for(Card card2: cardList){
                for(Card card3: cardList) {
                    if (card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue() && !card2.equals(card3)) {
                        allCards.remove(card1);
                        allCards.remove(card2);
                        allCards.remove(card3);
                        allCards.add(0, card1);
                        allCards.add(0, card2);
                        allCards.add(0, card3);

                        allCards.remove(5);
                        allCards.remove(5);
                        return allCards;
                    }
                }
            }
        }
        return new LinkedList<>();
    }

    @Override
    public List<Card> hasQuad(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();
        cardList.sort(Comparator.reverseOrder());
        List<Card> allCards = new LinkedList<>(cardList);
        allCards.sort(Comparator.reverseOrder());
        while(!cardList.isEmpty()){
            Card card1 = cardList.remove(0);

            for(Card card2: cardList){
                for(Card card3: cardList) {
                    for (Card card4 : cardList) {
                        if (card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue() && card3.getValue() == card4.getValue()
                                && !card2.equals(card3) && !card3.equals(card4) && !card2.equals(card4)) {
                            allCards.remove(card1);
                            allCards.remove(card2);
                            allCards.remove(card3);
                            allCards.remove(card4);
                            allCards.add(0, card1);
                            allCards.add(0, card2);
                            allCards.add(0, card3);
                            allCards.add(0, card4);

                            allCards.remove(5);
                            allCards.remove(5);
                            return allCards;
                        }
                    }
                }
            }
        }
        return new LinkedList<>();
    }

    @Override
    public List<Card> hasFullHouse(Hand hand) {
        List<Card> set = hasSet(hand);
        List<Card> double_pair = hasDoublePair(hand);
        if(!set.isEmpty() && !double_pair.isEmpty()){
            List<Card> result = new LinkedList<>();
            result.add(set.get(0));
            result.add(set.get(1));
            result.add(set.get(2));
            if(set.get(0).getValue() == double_pair.get(0).getValue()){
                result.add(double_pair.get(2));
                result.add(double_pair.get(3));
            } else {
                result.add(double_pair.get(0));
                result.add(double_pair.get(1));
            }
            return result;
        }
        return new LinkedList<>();
    }

    @Override
    public List<Card> hasStraight(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();
        cardList.sort(Comparator.naturalOrder());
        List<Card> result = new LinkedList<>();

        result.add(cardList.get(0));
        for(int i = 1; i < cardList.size(); i++){

            Card lastCard = result.get(result.size()-1);
            Card currentCard = cardList.get(i);

            if(lastCard.getValue().ordinal()+1 == currentCard.getValue().ordinal() ||
                    (lastCard.getValue() == ACE && currentCard.getValue() == Card.Value.TWO)){
                result.add(currentCard);
            } else {
                if(result.size()>4)
                    break;
                result.clear();
                result.add(currentCard);

            }

        }
        if(result.size() > 4){
            result.sort(Comparator.reverseOrder());
            while(result.size() > 5)
                result.remove(5);
            return result;
        }

        return new LinkedList<>();
    }

    @Override
    public List<Card> hasFlush(Hand hand) {
        List<Card> cardList = hand.getTotalHandCards();

        int heart = 0;
        int diamond = 0;
        int club = 0;
        int spade = 0;
        for(Card card: cardList){
            switch (card.getColor()){
                case HEART -> heart++;
                case DIAMOND -> diamond++;
                case CLUB -> club++;
                case SPADE -> spade++;
            }
        }
        Card.Color color = null;
        if(heart > 4)
            color = Card.Color.HEART;
        else if (diamond > 4)
            color = Card.Color.DIAMOND;
        else if (club > 4)
            color = Card.Color.CLUB;
        else if (spade > 4)
            color = Card.Color.SPADE;

        if(color!=null){
            List<Card> result = new LinkedList<>();
            for(Card card: cardList){
                if(card.getColor() == color){
                    result.add(card);
                }
            }

            result.sort(Comparator.reverseOrder());
            while(result.size() > 5)
                result.remove(5);
            return result;
        }



        return new LinkedList<>();
    }


}
