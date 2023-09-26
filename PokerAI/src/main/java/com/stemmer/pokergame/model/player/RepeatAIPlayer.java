package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.ActionType;
import com.stemmer.pokergame.model.Game;
import com.stemmer.pokergame.model.Status;

import static com.stemmer.pokergame.model.ActionType.*;
import static com.stemmer.pokergame.model.Status.*;

public class RepeatAIPlayer extends Player{

    private final ActionType repeatedAction;
    private final int maxRepetition;
    private final ActionType lastAction;

    private int turnCount;
    private Status lastStatus;

    public RepeatAIPlayer(String name, int money, ActionType repeatedAction, int maxRepetition, ActionType lastAction) {
        super(name, money);
        this.repeatedAction = repeatedAction;
        this.maxRepetition = maxRepetition;
        this.lastAction = lastAction;

        this.turnCount = 0;
        this.lastStatus = DEFAULT;
    }

    @Override
    public Action makeDecision(Game game) {

        turnCount = lastStatus == game.getStatus() ? turnCount + 1 : 1;
        lastStatus = game.getStatus();
        int hasToPayInOrderToStayIn = game.getCurrentInvestment() - bettingInvestment;

        // do the last action
        if(turnCount > maxRepetition){

            if(lastAction == ALLIN) {
                int re = totalMoney;
                setBettingInvestment(totalMoney);
                return new Action(ALLIN, bettingInvestment, re);
            } else if(hasToPayInOrderToStayIn == 0){
                return new Action(ActionType.CHECK, 0, 0);
            } else if(totalMoney >= hasToPayInOrderToStayIn){
                setBettingInvestment(hasToPayInOrderToStayIn);
                return new Action(ActionType.CALL, 0, hasToPayInOrderToStayIn);
            }
            foldHand();
            return new Action(ActionType.FOLD, 0, 0);
        }

        // repeated actions

        if(hasToPayInOrderToStayIn > totalMoney){

            if(lastAction == ALLIN) {
                int re = totalMoney;
                setBettingInvestment(totalMoney);
                return new Action(ALLIN, bettingInvestment, re);
            }
            foldHand();
            return new Action(ActionType.FOLD, 0, 0);
        } else if(repeatedAction == CALL || game.lastLegalRaiseAmount*2 +hasToPayInOrderToStayIn> totalMoney){
            setBettingInvestment(hasToPayInOrderToStayIn);
            return new Action(ActionType.CALL, 0, hasToPayInOrderToStayIn);
        } else if(repeatedAction == RAISE){
            int n = Math.max(game.lastLegalRaiseAmount*2+hasToPayInOrderToStayIn, game.smallBlind * 2);
            int re = bettingInvestment;
            setBettingInvestment(n);
            return new Action(ActionType.RAISE, n + re, n);
        }



        return null;
    }

}
