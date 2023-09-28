package com.stemmer.pokergame;

import com.stemmer.pokergame.helper.BasicHandCalculator;
import com.stemmer.pokergame.helper.HandCalculator;
import com.stemmer.pokergame.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static com.stemmer.pokergame.model.Card.Value.*;
import static com.stemmer.pokergame.model.Card.Color.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleEvaluationTest {

    static HandCalculator calculator;
    @BeforeAll
    static void initiate(){
        calculator = new BasicHandCalculator();
    }
    @Test
    void SimpleStraightFlushTest(){
        Board board = new Board();
        board.addCard(new Card(EIGHT, CLUBS));
        board.addCard(new Card(QUEEN, CLUBS));
        board.addCard(new Card(KING, CLUBS));
        board.addCard(new Card(JACK, CLUBS));
        board.addCard(new Card(TEN, CLUBS));

        Card handCard1 = new Card(ACE, CLUBS);
        Card handCard2 = new Card(FOUR, CLUBS);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.STRAIGHT_FLUSH);

        List<Card> resultHand = Arrays.asList(new Card(ACE, CLUBS), new Card(KING, CLUBS), new Card(QUEEN, CLUBS), new Card(JACK, CLUBS), new Card(TEN, CLUBS));
        assertTrue(resultHand.containsAll(ranking.getHand()) && ranking.getHand().containsAll(resultHand));

    }

    @Test
    void SimpleQuadTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(QUEEN, DIAMONDS));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, CLUBS));
        board.addCard(new Card(TEN, SPADES));

        Card handCard1 = new Card(EIGHT, HEARTS);
        Card handCard2 = new Card(EIGHT, DIAMONDS);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.QUAD);

        assertSame(ranking.getHand().get(0).getValue(), EIGHT);
        assertSame(ranking.getHand().get(1).getValue(), EIGHT);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertSame(ranking.getHand().get(3).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(4), new Card(KING, HEARTS));
    }

    @Test
    void SimpleFullHouseTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(KING, DIAMONDS));
        board.addCard(new Card(QUEEN, HEARTS));
        board.addCard(new Card(EIGHT, CLUBS));
        board.addCard(new Card(QUEEN, SPADES));

        Card handCard1 = new Card(EIGHT, HEARTS);
        Card handCard2 = new Card(KING, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.FULL_HOUSE);

        assertSame(ranking.getHand().get(0).getValue(), EIGHT);
        assertSame(ranking.getHand().get(1).getValue(), EIGHT);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertSame(ranking.getHand().get(3).getValue(), KING);
        assertSame(ranking.getHand().get(4).getValue(), KING);
    }

    @Test
    void SimpleFlushTest(){

        Board board = new Board();
        board.addCard(new Card(THREE, SPADES));
        board.addCard(new Card(QUEEN, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(TEN, SPADES));

        Card handCard1 = new Card(FIVE, SPADES);
        Card handCard2 = new Card(SEVEN, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.FLUSH);

        assertEquals(ranking.getHand().get(0), new Card(QUEEN, SPADES));
        assertEquals(ranking.getHand().get(1), new Card(TEN, SPADES));
        assertEquals(ranking.getHand().get(2), new Card(EIGHT, SPADES));
        assertEquals(ranking.getHand().get(3), new Card(SEVEN, SPADES));
        assertEquals(ranking.getHand().get(4), new Card(FIVE, SPADES));
    }

    @Test
    void SimpleStraightTest(){

        Board board = new Board();
        board.addCard(new Card(SIX, DIAMONDS));
        board.addCard(new Card(NINE, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, CLUBS));
        board.addCard(new Card(TEN, HEARTS));

        Card handCard1 = new Card(FIVE, DIAMONDS);
        Card handCard2 = new Card(SEVEN, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.STRAIGHT);

        assertEquals(ranking.getHand().get(0), new Card(TEN, HEARTS));
        assertEquals(ranking.getHand().get(1), new Card(NINE, SPADES));
        assertEquals(ranking.getHand().get(2), new Card(EIGHT, CLUBS));
        assertEquals(ranking.getHand().get(3), new Card(SEVEN, SPADES));
        assertEquals(ranking.getHand().get(4), new Card(SIX, DIAMONDS));
    }

    @Test
    void SimpleSetTest(){

        Board board = new Board();
        board.addCard(new Card(QUEEN, DIAMONDS));
        board.addCard(new Card(NINE, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, CLUBS));
        board.addCard(new Card(TEN, HEARTS));

        Card handCard1 = new Card(EIGHT, DIAMONDS);
        Card handCard2 = new Card(EIGHT, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.SET);

        assertSame(ranking.getHand().get(0).getValue(), EIGHT);
        assertSame(ranking.getHand().get(1).getValue(), EIGHT);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(3), new Card(KING, HEARTS));
        assertEquals(ranking.getHand().get(4), new Card(QUEEN, DIAMONDS));
    }

    @Test
    void SimpleDoublePairTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMONDS));
        board.addCard(new Card(NINE, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(QUEEN, CLUBS));
        board.addCard(new Card(TEN, HEARTS));

        Card handCard1 = new Card(QUEEN, DIAMONDS);
        Card handCard2 = new Card(EIGHT, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.DOUBLE_PAIR);

        assertSame(ranking.getHand().get(0).getValue(), QUEEN);
        assertSame(ranking.getHand().get(1).getValue(), QUEEN);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertSame(ranking.getHand().get(3).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(4), new Card(KING, HEARTS));
    }

    @Test
    void SimplePairTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMONDS));
        board.addCard(new Card(NINE, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(QUEEN, CLUBS));
        board.addCard(new Card(TEN, HEARTS));

        Card handCard1 = new Card(QUEEN, DIAMONDS);
        Card handCard2 = new Card(TWO, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.PAIR);

        assertSame(ranking.getHand().get(0).getValue(), QUEEN);
        assertSame(ranking.getHand().get(1).getValue(), QUEEN);
        assertEquals(ranking.getHand().get(2), new Card(KING, HEARTS));
        assertEquals(ranking.getHand().get(3), new Card(TEN, HEARTS));
        assertEquals(ranking.getHand().get(4), new Card(NINE, SPADES));
    }

    @Test
    void SimpleHighCardTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMONDS));
        board.addCard(new Card(NINE, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(QUEEN, CLUBS));
        board.addCard(new Card(TEN, HEARTS));

        Card handCard1 = new Card(ACE, DIAMONDS);
        Card handCard2 = new Card(TWO, SPADES);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.HIGH_CARD);

        assertEquals(ranking.getHand().get(0), new Card(ACE, DIAMONDS));
        assertEquals(ranking.getHand().get(1), new Card(KING, HEARTS));
        assertEquals(ranking.getHand().get(2), new Card(QUEEN, CLUBS));
        assertEquals(ranking.getHand().get(3), new Card(TEN, HEARTS));
        assertEquals(ranking.getHand().get(4), new Card(NINE, SPADES));
    }
}