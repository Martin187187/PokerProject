package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.ActionType;
import com.stemmer.pokergame.model.Game;

public class SimpleAIPlayer extends Player{

    public SimpleAIPlayer(String name, int money) {
        super(name, money);
    }

    /**
     * check if can check
     * call if has enough money
     * else fold
     *
     * @param game
     * @return
     */
    @Override
    public Action makeDecision(Game game) {

        int hasToPayInOrderToStayIn = game.getCurrentInvestment() - bettingInvestment;
        if(hasToPayInOrderToStayIn == 0){
            return new Action(ActionType.CHECK, 0, 0);
        } else if(totalMoney >= hasToPayInOrderToStayIn){
            setBettingInvestment(hasToPayInOrderToStayIn);
            return new Action(ActionType.CALL, 0, hasToPayInOrderToStayIn);
        }

        foldHand();
        return new Action(ActionType.FOLD, 0, 0);
    }
}
