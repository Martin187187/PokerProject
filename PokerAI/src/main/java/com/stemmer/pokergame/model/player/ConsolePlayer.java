package com.stemmer.pokergame.model.player;

import com.stemmer.pokergame.model.ActionType;
import com.stemmer.pokergame.model.Game;

import java.util.Scanner;

public class ConsolePlayer extends Player{

    public ConsolePlayer(String name, int money) {
        super(name, money);
    }

    @Override
    public Action makeDecision(Game game){
        int hasToPayInOrderToStayIn = game.getCurrentInvestment() - bettingInvestment;
        System.out.println(name + "'s turn");
        System.out.println("your hand: " + hand);
        System.out.println("already invested money: " + bettingInvestment);
        System.out.println("has to pay in order to stay in: " + hasToPayInOrderToStayIn);
        System.out.println("current game investment: " + game.getCurrentInvestment());

        while(true){
            String callOrCheck = hasToPayInOrderToStayIn > 0 ? "call" : "check";
            String betOrRaise = "raise";
            System.out.print("type: fold/"+callOrCheck+"/"+betOrRaise+" [raisedTo>="+Math.max(game.smallBlind*2+ bettingInvestment +hasToPayInOrderToStayIn,hasToPayInOrderToStayIn+game.lastLegalRaiseAmount)+"]/allin: ");
            Scanner scanner = new Scanner(System.in);
            String inputLine = scanner.nextLine();
            String[] inputArray = inputLine.split(" ");
            try{
                if(inputArray.length == 0){
                    System.out.println("not a valid inout");
                } else if(inputArray[0].equals("fold")) {
                    foldHand();
                    return new Action(ActionType.FOLD, 0, 0);
                } else if(inputArray[0].equals("check") && hasToPayInOrderToStayIn > 0 )
                    System.out.println("check is not possible");
                else if(inputArray[0].equals("call") && hasToPayInOrderToStayIn == 0 )
                    System.out.println("call is not possible");
                else if(inputArray[0].equals("check"))
                    return new Action(ActionType.CHECK, 0, 0);
                else if (hasToPayInOrderToStayIn > totalMoney) {
                    System.out.println("dont have enough money");
                } else if (inputArray[0].equals("allin")) {
                    int re = totalMoney;
                    setBettingInvestment(totalMoney );
                    return new Action(ActionType.ALLIN, bettingInvestment, re);
                } else if(inputArray[0].equals("call")) {
                    setBettingInvestment(hasToPayInOrderToStayIn);
                    return new Action(ActionType.CALL, 0, hasToPayInOrderToStayIn);
                } else if(!inputArray[0].equals("raise")) {
                    System.out.println("operator " +  inputArray[0] + " is not supported!");
                } else if(inputArray.length != 2){
                    System.out.println("you have to put a number");
                } else {
                    int raiseAmount = Integer.parseInt(inputArray[1])- bettingInvestment;
                    if(raiseAmount + bettingInvestment < game.smallBlind * 2+ game.currentInvestment){
                        System.out.println("raise hase to be at least 2 time small blind: " + (game.smallBlind*2+ bettingInvestment +hasToPayInOrderToStayIn));
                    } else if(raiseAmount + bettingInvestment < hasToPayInOrderToStayIn + game.lastLegalRaiseAmount){
                        System.out.println("raise has to be at least the size of the last raise: " + (hasToPayInOrderToStayIn+game.lastLegalRaiseAmount));
                    } else if ((raiseAmount - hasToPayInOrderToStayIn) % game.smallBlind != 0) {
                        System.out.println("raise has to be a multiple of small blind: " + game.smallBlind);
                    } else if (raiseAmount > totalMoney) {
                        System.out.println("not enough money");
                    } else {
                        int re = bettingInvestment;
                        setBettingInvestment(raiseAmount);
                        return new Action(ActionType.RAISE, raiseAmount + re, raiseAmount);
                    }
                }

            } catch (NumberFormatException e){
                System.out.println("input is not a valid number: " + e);
            }

        }
    }
}
