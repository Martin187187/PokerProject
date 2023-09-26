package com.stemmer.pokergame.model;

import com.stemmer.pokergame.model.player.Action;
import com.stemmer.pokergame.model.player.Player;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.stemmer.pokergame.model.ActionType.*;

public class NaiveGame extends Game{


    public NaiveGame(int smallBlind, Board board) {
        super(smallBlind, board);
    }

    @Override
    public List<List<Action>> playRound(){

        result.clear();
        // get player order
        playerQueue = new LinkedList<>(playerList);

        //create Deck
        createDeck();

        //maybe check if starting player wants to straddle

        //put blinds
        Iterator<Player> iterator = playerQueue.iterator();

        Player smallBlindPlayer = iterator.next();
        smallBlindPlayer.setBettingInvestment(Math.min(smallBlindPlayer.getTotalMoney(),smallBlind));

        Player bigBlindPlayer = iterator.next();
        bigBlindPlayer.setBettingInvestment(Math.min(bigBlindPlayer.getTotalMoney(), smallBlind*2));

        pot = smallBlindPlayer.getBettingInvestment() + bigBlindPlayer.getBettingInvestment();
        currentInvestment = Math.max(smallBlindPlayer.getBettingInvestment(),bigBlindPlayer.getBettingInvestment());

        //deal cards
        dealCards();

        //start betting round with first player in queue

        lastLegalRaiseAmount = currentInvestment;
        initiate();
        bettingRound();

        // play flop
        playFlop();
        bettingRound();

        // play Turn
        playTurn();
        bettingRound();

        // play River
        playRiver();
        bettingRound();

        playerList.forEach(Player::resetBettingRound);

        // change player order for next round
        shiftPlayers();

        return result;
    }

    public void bettingRound(){
        List<Action> actions = new LinkedList<>();
        waitingQueue = new LinkedList<>();

        int n = playerQueue.stream().filter(x->x.getTotalMoney()>0).toList().size();
        if(n <= 1){
            return;
        }
        while(!playerQueue.stream().filter(x->x.getTotalMoney()>0).toList().isEmpty()){
            Player player = playerQueue.poll();

            if(player.getTotalMoney()>0) {
                Action decision = player.makeDecision(this);
                if(player.getTotalMoney() <= 0){
                    decision = new Action(ALLIN, decision.getAmount(), decision.getIncrease());
                }
                actions.add(decision);
                pot+=decision.getIncrease();
                switch (decision.getType()) {

                    // fold
                    case FOLD -> {
                    }

                    // check
                    case CHECK, CALL -> {
                        waitingQueue.add(player);
                    }

                    // call, bet or raise
                    case RAISE, ALLIN -> {
                        //is amountHasToPay
                        if(decision.getAmount() > currentInvestment) {

                            currentInvestment = decision.getAmount();
                            lastLegalRaiseAmount = decision.getAmount();

                            while (!waitingQueue.isEmpty()) {
                                playerQueue.add(waitingQueue.poll());
                            }
                        }


                        waitingQueue.add(player);
                    }


                }
            } else if(player.getTotalBettingInvestment() > 0 || player.getBettingInvestment() > 0){

                waitingQueue.add(player);
            }

        }
        result.add(actions);
    }
}
