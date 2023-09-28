package com.stemmer.pokergame.model;

import java.util.LinkedList;
import java.util.List;

public class Hand implements Comparable<Hand>{

    private List<Card> handCards;
    private Board board;

    public Hand(Card card1, Card card2, Board board) {

        this.handCards = new LinkedList<>();
        this.handCards.add(card1);
        this.handCards.add(card2);
        this.board = board;
    }

    public void reset(){
        handCards.clear();

    }

    public List<Card> getTotalHandCards(){
        List<Card> totalCards = new LinkedList<>(handCards);
        totalCards.addAll(board.getBoardCards());
        return totalCards;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    @Override
    public int compareTo(Hand o) {
        return 0;
    }

    @Override
    public String toString() {
        return getTotalHandCards().toString();
    }
}
