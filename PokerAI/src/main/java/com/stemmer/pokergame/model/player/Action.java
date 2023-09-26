package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.ActionType;

public record Action(ActionType type, int raisedTo, int amountHasToPay) {

    public ActionType getType() {
        return type;
    }

    public int getAmount() {
        return raisedTo;
    }

    public int getIncrease() {
        return amountHasToPay;
    }

}
