package com.stemmer.pokergame.model;

import com.stemmer.pokergame.helper.BasicHandCalculator;
import com.stemmer.pokergame.helper.HandCalculator;
import com.stemmer.pokergame.model.player.Action;
import com.stemmer.pokergame.model.player.Player;

import java.util.*;

import static com.stemmer.pokergame.model.Status.*;

public abstract class Game {

    protected Board board;
    protected List<Card> cardList;
    protected List<Player> originalPlayerList;
    protected Queue<Player> playerList;
    protected Queue<Player> playerQueue;
    protected Queue<Player> waitingQueue;
    public int smallBlind;
    public int currentInvestment;
    public int pot;
    public int lastLegalRaiseAmount;
    private Status status;

    protected HandCalculator calculator;
    protected Random rand = new Random();
    protected List<List<Action>> result = new LinkedList<>();

    public Game(int smallBlind, Board board){
        this.board = board;
        this.smallBlind = smallBlind;
        this.originalPlayerList = new LinkedList<>();
        this.cardList = new LinkedList<>();
        this.playerQueue = new LinkedList<>();
        this.playerList = new LinkedList<>();
        this.status = DEFAULT;

        this.calculator = new BasicHandCalculator();
    }

    public void addPlayer(Player player){
        originalPlayerList.add(player);
        playerList.add(player);
    }

    public abstract List<List<Action>> playRound();


    public void createDeck(){

        this.cardList.clear();
        for(Card.Value value: Card.Value.values()){
            for(Card.Color color: Card.Color.values()){
                Card card = new Card(value, color);
                this.cardList.add(card);
            }
        }

    }
    public void dealCards(){
        Random rand = new Random();
        for(Player player: playerList){
            Card card1 = cardList.remove(rand.nextInt(cardList.size()));
            Card card2 = cardList.remove(rand.nextInt(cardList.size()));

            Hand hand = new Hand(card1, card2, board);
            player.setHand(hand);
        }
    }

    protected void initiate(){
        status = DEFAULT;

    }
    protected void playFlop(){

        Card card1 = cardList.remove(rand.nextInt(cardList.size()));
        Card card2 = cardList.remove(rand.nextInt(cardList.size()));
        Card card3 = cardList.remove(rand.nextInt(cardList.size()));

        board.addFlop(card1, card2, card3);

        playerQueue = new LinkedList<>(waitingQueue);
        for(Player player: playerQueue){
            player.resetBettingRound();
        }

        status = FLOP;
        currentInvestment = 0;
        lastLegalRaiseAmount = 0;
    }

    protected void playTurn(){

        Card cardTurn = cardList.remove(rand.nextInt(cardList.size()));

        board.addCard(cardTurn);

        playerQueue = new LinkedList<>(waitingQueue);
        for(Player player: playerQueue){
            player.resetBettingRound();
        }

        status = TURN;
        currentInvestment = 0;
        lastLegalRaiseAmount = 0;
    }

    protected void playRiver(){

        Card cardTurn = cardList.remove(rand.nextInt(cardList.size()));

        board.addCard(cardTurn);

        playerQueue = new LinkedList<>(waitingQueue);
        for(Player player: playerQueue){
            player.resetBettingRound();
        }

        status = RIVER;
        currentInvestment = 0;
        lastLegalRaiseAmount = 0;
    }

    public void reset(){
        board.reset();
        playerList.forEach(Player::reset);
    }

    public void terminateLosers(){
        playerList.removeIf(x->x.getTotalMoney()<=0);
    }

    public void shiftPlayers(){
        playerList.add(playerList.poll());
    }
    public void determineWinners(){
        List<Player> currentEvaluation = new LinkedList<>(playerList);

        while(currentEvaluation.stream().anyMatch(x -> x.getTotalBettingInvestment() > 0 && !x.isFolded())){

            List<Player> winningPlayerList = new LinkedList<>(currentEvaluation);
            //print
            for (Player player: currentEvaluation){
                System.out.println(player.printPlayer() + " has: ");
                var result = calculator.getHandRanking(player.getHand());
                if(player.isFolded()){
                    System.out.println("FOLDED");
                } else {
                    System.out.println(result.getRank());
                    System.out.println(result.getHand());
                }
            }
            //get winners
            for(int i = 0; i < currentEvaluation.size(); i++){
                Player player1 = currentEvaluation.get(i);

                if(player1.isFolded()){
                    winningPlayerList.remove(player1);
                    continue;
                }

                for(int j = i + 1; j <  currentEvaluation.size(); j++){
                    Player player2 = currentEvaluation.get(j);
                    if(player2.isFolded()){
                        winningPlayerList.remove(player2);
                        continue;
                    }
                    int comp = calculator.compareHands(player1.getHand(), player2.getHand());
                    if(comp == 1)
                        winningPlayerList.remove(player2);
                    else if(comp == -1)
                        winningPlayerList.remove(player1);
                }
            }

            winningPlayerList.sort(Comparator.comparingInt(Player::getTotalBettingInvestment));

            //divide winnings


            for(int i = 0; i < winningPlayerList.size(); i++){
                List<Integer> playerInvestments = currentEvaluation.stream().map(Player::getTotalBettingInvestment).toList();
                Player winner = winningPlayerList.get(i);
                int index = currentEvaluation.indexOf(winner);
                int winnerInvestment = playerInvestments.get(index);
                double collectRatio = (double)winnerInvestment/((winningPlayerList.size()-i)*winnerInvestment);
                //System.out.println("sdjk: " + collectRatio);
                for(int j = 0; j < currentEvaluation.size(); j++){
                    Player player = currentEvaluation.get(j);
                    int playerInvestment = Math.min(winnerInvestment, playerInvestments.get(j));
                    int collectMoney = (int) (collectRatio*playerInvestment);
                    //System.out.println("collected money from "+ player.getName() + " for " + winner.getName() + ": " + collectMoney);
                    player.setTotalBettingInvestment(player.getTotalBettingInvestment()-collectMoney);
                    winner.setTotalMoney(winner.getTotalMoney()+collectMoney);
                }
            }

            //remove winners from evaluation list

            for(var player: winningPlayerList){
                currentEvaluation.remove(player);
            }
        }

        for(var player: playerList){
            player.setTotalMoney(player.getTotalMoney()+player.getTotalBettingInvestment());
        }


    }

    public Board getBoard() {
        return board;
    }

    // maybe refactor game state in own class
    public void printGameState(){
        playerList.forEach(x -> System.out.println(x.printPlayer()));
    }
    public List<Card> getCardList() {
        return cardList;
    }

    public Queue<Player> getPlayerList() {
        return playerList;
    }

    public List<Player> getOriginalPlayerList() {
        return originalPlayerList;
    }

    public int getCurrentInvestment() {
        return currentInvestment;
    }

    public int getPot() {
        return pot;
    }

    public Status getStatus() {
        return status;
    }
}
