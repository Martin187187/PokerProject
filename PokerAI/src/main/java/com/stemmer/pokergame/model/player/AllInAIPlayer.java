package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.ActionType;
import com.stemmer.pokergame.model.Game;

public class AllInAIPlayer extends Player{

    public AllInAIPlayer(String name, int money) {
        super(name, money);
    }

    /**
     * @param game
     * @return
     */
    @Override
    public Action makeDecision(Game game) {

        int hasToPayInOrderToStayIn = game.getCurrentInvestment() - bettingInvestment;
        int re = totalMoney;
        setBettingInvestment(totalMoney );
        return new Action(ActionType.ALLIN, bettingInvestment, re);

    }
}
