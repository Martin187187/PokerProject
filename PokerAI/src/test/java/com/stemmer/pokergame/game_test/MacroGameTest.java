package com.stemmer.pokergame.game_test;

import com.stemmer.pokergame.model.*;
import com.stemmer.pokergame.model.player.*;
import org.junit.jupiter.api.Test;

import static com.stemmer.pokergame.model.Card.Color.*;
import static com.stemmer.pokergame.model.Card.Value.*;
import static com.stemmer.pokergame.model.ActionType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MacroGameTest {


    @Test
    void SimpleGameTest(){

        Game game = new NaiveGame(50, new Board());
        game.addPlayer(new SimpleAIPlayer("Player 1", 50));
        game.addPlayer(new SimpleAIPlayer("Player 2", 20000));
        game.addPlayer(new SimpleAIPlayer("Player 3", 20000));

        var actions = game.playRound();
        assertEquals(actions.size(),  4);
        var preFlop = actions.get(0);
        assertEquals(preFlop.size(), 2);
        assertEquals(preFlop.get(0), new Action(CHECK, 0, 0));
        assertEquals(preFlop.get(1), new Action(CALL, 0, 100));


        var pastFlop = actions.get(1);
        assertEquals(pastFlop.size(), 2);
        assertEquals(pastFlop.get(0), new Action(CHECK, 0, 0));
        assertEquals(pastFlop.get(1), new Action(CHECK, 0, 0));

        var pastTurn = actions.get(2);
        assertEquals(pastTurn.size(), 2);
        assertEquals(pastTurn.get(0), new Action(CHECK, 0, 0));
        assertEquals(pastTurn.get(1), new Action(CHECK, 0, 0));

        var pastRiver = actions.get(3);
        assertEquals(pastRiver.size(), 2);
        assertEquals(pastRiver.get(0), new Action(CHECK, 0, 0));
        assertEquals(pastRiver.get(1), new Action(CHECK, 0, 0));


        assertEquals(250, game.getPot());
    }

    @Test
    void SimpleWinningsTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new SimpleAIPlayer("Player 1", 20000);
        Player two = new SimpleAIPlayer("Player 1", 20000);

        game.addPlayer(one);
        game.addPlayer(two);
        game.playRound();

        Board board = new Board();
        board.addCard(new Card(THREE, SPADE));
        board.addCard(new Card(QUEEN, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand1 = new Hand(handCard1, handCard2, board);
        Card handCard3 = new Card(ACE, SPADE);
        Card handCard4 = new Card(EIGHT, DIAMOND);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        one.setHand(hand1);
        two.setHand(hand2);
        game.determineWinners();

        assertEquals(200, game.getPot());
        assertEquals(19900, one.getTotalMoney());
        assertEquals(20100, two.getTotalMoney());
    }

    @Test
    void SimpleSplitPotTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new SimpleAIPlayer("Player 1", 20000);
        Player two = new SimpleAIPlayer("Player 2", 20000);

        game.addPlayer(one);
        game.addPlayer(two);
        var actions = game.playRound();
        System.out.println(actions);

        Board board = new Board();
        board.addCard(new Card(THREE, CLUB));
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand1 = new Hand(handCard1, handCard2, board);
        Card handCard3 = new Card(FIVE, DIAMOND);
        Card handCard4 = new Card(SEVEN, DIAMOND);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        one.setHand(hand1);
        two.setHand(hand2);
        game.determineWinners();

        assertEquals(200, game.getPot());
        assertEquals(20000, one.getTotalMoney());
        assertEquals(20000, two.getTotalMoney());
    }

    @Test
    void SimpleAllInTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new AllInAIPlayer("Player 1", 20000);
        Player two = new AllInAIPlayer("Player 2", 20000);
        Player three = new AllInAIPlayer("Player 3", 20000);

        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);
        var actions = game.playRound();
        for(var ac: actions)
            System.out.println(ac);


        Board board = new Board();
        board.addCard(new Card(THREE, CLUB));
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        //Side pot winer
        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand1 = new Hand(handCard1, handCard2, board);

        //Winner
        Card handCard3 = new Card(FIVE, DIAMOND);
        Card handCard4 = new Card(ACE, DIAMOND);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        //loser
        Card handCard5 = new Card(FIVE, DIAMOND);
        Card handCard6 = new Card(TWO, DIAMOND);
        Hand hand3 = new Hand(handCard5, handCard6, board);

        one.setHand(hand1);
        two.setHand(hand2);
        three.setHand(hand3);
        game.determineWinners();

        assertEquals(60000, game.getPot());
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        assertEquals(0, one.getTotalMoney());
        assertEquals(60000, two.getTotalMoney());
        assertEquals(0, three.getTotalMoney());
    }
    @Test
    void SimpleSidePotTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new AllInAIPlayer("Player 1", 20000);
        Player two = new AllInAIPlayer("Player 2", 10000);
        Player three = new AllInAIPlayer("Player 3", 20000);

        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);
        var actions = game.playRound();
        for(var ac: actions)
            System.out.println(ac);


        Board board = new Board();
        board.addCard(new Card(THREE, CLUB));
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        //Side pot winer
        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand1 = new Hand(handCard1, handCard2, board);

        //Winner
        Card handCard3 = new Card(FIVE, DIAMOND);
        Card handCard4 = new Card(ACE, DIAMOND);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        //loser
        Card handCard5 = new Card(FIVE, DIAMOND);
        Card handCard6 = new Card(TWO, DIAMOND);
        Hand hand3 = new Hand(handCard5, handCard6, board);

        one.setHand(hand1);
        two.setHand(hand2);
        three.setHand(hand3);
        game.determineWinners();

        assertEquals(50000, game.getPot());
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);

        // side pot winner
        assertEquals(20000, one.getTotalMoney());

        // winner
        assertEquals(30000, two.getTotalMoney());
        assertEquals(0, three.getTotalMoney());
    }
    @Test
    void TwoUnequalWinnerTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new AllInAIPlayer("Player 1", 30000);
        Player two = new AllInAIPlayer("Player 2", 10000);
        Player three = new AllInAIPlayer("Player 3", 20000);

        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);
        var actions = game.playRound();
        for(var ac: actions)
            System.out.println(ac);


        Board board = new Board();
        board.addCard(new Card(THREE, CLUB));
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        //Side pot winer
        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(ACE, SPADE);
        Hand hand1 = new Hand(handCard1, handCard2, board);

        //Winner
        Card handCard3 = new Card(FIVE, DIAMOND);
        Card handCard4 = new Card(ACE, DIAMOND);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        //loser
        Card handCard5 = new Card(FIVE, DIAMOND);
        Card handCard6 = new Card(TWO, DIAMOND);
        Hand hand3 = new Hand(handCard5, handCard6, board);

        one.setHand(hand1);
        two.setHand(hand2);
        three.setHand(hand3);
        game.determineWinners();

        assertEquals(60000, game.getPot());
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);

        assertEquals(45000, one.getTotalMoney());
        assertEquals(15000, two.getTotalMoney());
        assertEquals(0, three.getTotalMoney());
    }


}
