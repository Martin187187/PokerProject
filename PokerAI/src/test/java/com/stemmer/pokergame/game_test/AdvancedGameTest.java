package com.stemmer.pokergame.game_test;

import com.stemmer.pokergame.model.*;
import com.stemmer.pokergame.model.player.Player;
import com.stemmer.pokergame.model.player.RepeatAIPlayer;
import org.junit.jupiter.api.Test;

import static com.stemmer.pokergame.model.ActionType.*;
import static com.stemmer.pokergame.model.Card.Color.*;
import static com.stemmer.pokergame.model.Card.Color.DIAMONDS;
import static com.stemmer.pokergame.model.Card.Value.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvancedGameTest {

    @Test
    void RepeatedPlayerTest(){

        Game game = new NaiveGame(50, new Board());

        Player one = new RepeatAIPlayer("Player 1", 10000, RAISE, 1, CALL);
        Player two = new RepeatAIPlayer("Player 2", 30000, RAISE, 0, CALL);
        Player three = new RepeatAIPlayer("Player 3", 10000, RAISE, 2, CALL);

        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);

        var actions = game.playRound();
        assertEquals(actions.size(),  4);
        var preFlop = actions.get(0);
        assertEquals(preFlop.size(), 5);
        assertEquals(preFlop.get(0).getType(), RAISE);
        assertEquals(preFlop.get(1).getType(), CALL);
        assertEquals(preFlop.get(2).getType(), RAISE);
        assertEquals(preFlop.get(3).getType(), CALL);
        assertEquals(preFlop.get(4).getType(), CALL);


        var pastFlop = actions.get(1);
        assertEquals(pastFlop.size(), 6);
        assertEquals(pastFlop.get(0).getType(), RAISE);
        assertEquals(pastFlop.get(1).getType(), RAISE);
        assertEquals(pastFlop.get(2).getType(), CALL);
        assertEquals(pastFlop.get(3).getType(), RAISE);
        assertEquals(pastFlop.get(4).getType(), CALL);
        assertEquals(pastFlop.get(5).getType(), CALL);

        var pastTurn = actions.get(2);
        assertEquals(pastTurn.size(), 6);
        assertEquals(pastTurn.get(0).getType(), RAISE);
        assertEquals(pastTurn.get(1).getType(), RAISE);
        assertEquals(pastTurn.get(2).getType(), CALL);
        assertEquals(pastTurn.get(3).getType(), RAISE);
        assertEquals(pastTurn.get(4).getType(), CALL);
        assertEquals(pastTurn.get(5).getType(), CALL);

        var pastRiver = actions.get(3);
        assertEquals(pastRiver.size(), 6);
        assertEquals(pastRiver.get(0).getType(), RAISE);
        assertEquals(pastRiver.get(1).getType(), RAISE);
        assertEquals(pastRiver.get(2).getType(), CALL);
        assertEquals(pastRiver.get(3).getType(), RAISE);
        assertEquals(pastRiver.get(4).getType(), CALL);
        assertEquals(pastRiver.get(5).getType(), CALL);


        Board board = new Board();
        board.addCard(new Card(THREE, CLUBS));
        board.addCard(new Card(QUEEN, DIAMONDS));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(TEN, SPADES));

        //Side pot winer
        Card handCard1 = new Card(FIVE, SPADES);
        Card handCard2 = new Card(ACE, SPADES);
        Hand hand1 = new Hand(handCard1, handCard2, board);

        //Winner
        Card handCard3 = new Card(FIVE, DIAMONDS);
        Card handCard4 = new Card(ACE, DIAMONDS);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        //loser
        Card handCard5 = new Card(FIVE, DIAMONDS);
        Card handCard6 = new Card(TWO, DIAMONDS);
        Hand hand3 = new Hand(handCard5, handCard6, board);

        one.setHand(hand1);
        two.setHand(hand2);
        three.setHand(hand3);
        game.determineWinners();

    }

    @Test
    void RepeatedPlayerTest2(){

        Game game = new NaiveGame(50, new Board());

        Player one = new RepeatAIPlayer("Player 1", 100, RAISE, 2, CALL);
        Player two = new RepeatAIPlayer("Player 2", 59300, RAISE, 1, CALL);
        Player three = new RepeatAIPlayer("Player 3", 600, RAISE, 1, CALL);

        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);

        var actions = game.playRound();
        assertEquals(2,  actions.size());
        var preFlop = actions.get(0);
        assertEquals(preFlop.size(), 3);
        assertEquals(preFlop.get(0).getType(), ALLIN);
        assertEquals(preFlop.get(1).getType(), RAISE);
        assertEquals(preFlop.get(2).getType(), CALL);


        var pastFlop = actions.get(1);
        assertEquals(pastFlop.size(), 3);
        assertEquals(pastFlop.get(0).getType(), RAISE);
        assertEquals(pastFlop.get(1).getType(), ALLIN);
        assertEquals(pastFlop.get(2).getType(), CALL);




        Board board = new Board();
        board.addCard(new Card(QUEEN, SPADES));
        board.addCard(new Card(QUEEN, DIAMONDS));
        board.addCard(new Card(TEN, HEARTS));
        board.addCard(new Card(ACE, SPADES));
        board.addCard(new Card(FOUR, CLUBS));

        Card handCard1 = new Card(TEN, SPADES);
        Card handCard2 = new Card(TWO, SPADES);
        Hand hand1 = new Hand(handCard1, handCard2, board);

        Card handCard3 = new Card(SIX, CLUBS);
        Card handCard4 = new Card(TWO, CLUBS);
        Hand hand2 = new Hand(handCard3, handCard4, board);

        Card handCard5 = new Card(TEN, DIAMONDS);
        Card handCard6 = new Card(TWO, DIAMONDS);
        Hand hand3 = new Hand(handCard5, handCard6, board);

        one.setHand(hand1);
        two.setHand(hand2);
        three.setHand(hand3);
        game.determineWinners();
        assertEquals(1300, game.getPot());

        assertEquals(150, one.getTotalMoney());
        assertEquals(58700, two.getTotalMoney());
        assertEquals(1150, three.getTotalMoney());

    }
}
