package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.Game;
import com.stemmer.pokergame.model.Hand;

public abstract class Player {


    protected final String name;
    protected Hand hand;
    protected int totalMoney;
    protected int bettingInvestment;
    protected int totalBettingInvestment;

    protected boolean folded;

    public Player(String name, int money) {
        this.name = name;
        this.totalMoney = money;
        this.bettingInvestment = 0;
        this.totalBettingInvestment = 0;
        this.folded = false;
    }

    public Hand getHand() {
        return hand;
    }

    public void reset(){
        hand.reset();
        resetBettingRound();
        this.folded = false;
    }

    public void foldHand(){
        this.folded = true;
    }
    public void resetBettingRound(){
        totalBettingInvestment += bettingInvestment;
        bettingInvestment = 0;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public abstract Action makeDecision(Game game);

    public int getBettingInvestment() {
        return bettingInvestment;
    }

    public void setBettingInvestment(int bettingInvestment) {
        this.bettingInvestment += bettingInvestment;
        this.totalMoney -= bettingInvestment;
    }

    public String getName() {
        return name;
    }

    public boolean isFolded() {
        return folded;
    }

    public int getTotalBettingInvestment() {
        return totalBettingInvestment;
    }

    public void setTotalBettingInvestment(int totalBettingInvestment) {
        this.totalBettingInvestment = totalBettingInvestment;
    }

    public String printPlayer(){
        return name + ":\t" + totalMoney;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                ", totalMoney=" + totalMoney +
                ", bettingInvestment=" + bettingInvestment +
                ", totalBettingInvestment=" + totalBettingInvestment +
                ", folded=" + folded +
                '}';
    }
}
