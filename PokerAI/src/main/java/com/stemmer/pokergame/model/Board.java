package com.stemmer.pokergame.model;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private List<Card> boardCards;

    public Board(){
        this.boardCards = new LinkedList<>();
    }

    public void addFlop(Card card1, Card card2, Card card3){
        this.boardCards.add(card1);
        this.boardCards.add(card2);
        this.boardCards.add(card3);
    }

    public void reset(){
        boardCards.clear();
    }
    public void addTurn(Card card){
        this.boardCards.add(card);
    }

    public void addRiver(Card card){
        this.boardCards.add(card);
    }

    public void addCard(Card card){
        this.boardCards.add(card);
    }
    private boolean isFull(){
        return boardCards.size() == 5;
    }

    public List<Card> getBoardCards() {
        return boardCards;
    }
}
