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
        board.addCard(new Card(EIGHT, CLUB));
        board.addCard(new Card(QUEEN, CLUB));
        board.addCard(new Card(KING, CLUB));
        board.addCard(new Card(JACK, CLUB));
        board.addCard(new Card(TEN, CLUB));

        Card handCard1 = new Card(ACE, CLUB);
        Card handCard2 = new Card(FOUR, CLUB);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.STRAIGHT_FLUSH);

        List<Card> resultHand = Arrays.asList(new Card(ACE, CLUB), new Card(KING, CLUB), new Card(QUEEN, CLUB), new Card(JACK, CLUB), new Card(TEN, CLUB));
        assertTrue(resultHand.containsAll(ranking.getHand()) && ranking.getHand().containsAll(resultHand));

    }

    @Test
    void SimpleQuadTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, CLUB));
        board.addCard(new Card(TEN, SPADE));

        Card handCard1 = new Card(EIGHT, HEART);
        Card handCard2 = new Card(EIGHT, DIAMOND);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.QUAD);

        assertSame(ranking.getHand().get(0).getValue(), EIGHT);
        assertSame(ranking.getHand().get(1).getValue(), EIGHT);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertSame(ranking.getHand().get(3).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(4), new Card(KING, HEART));
    }

    @Test
    void SimpleFullHouseTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(KING, DIAMOND));
        board.addCard(new Card(QUEEN, HEART));
        board.addCard(new Card(EIGHT, CLUB));
        board.addCard(new Card(QUEEN, SPADE));

        Card handCard1 = new Card(EIGHT, HEART);
        Card handCard2 = new Card(KING, SPADE);
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
        board.addCard(new Card(THREE, SPADE));
        board.addCard(new Card(QUEEN, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, SPADE));
        board.addCard(new Card(TEN, SPADE));

        Card handCard1 = new Card(FIVE, SPADE);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.FLUSH);

        assertEquals(ranking.getHand().get(0), new Card(QUEEN, SPADE));
        assertEquals(ranking.getHand().get(1), new Card(TEN, SPADE));
        assertEquals(ranking.getHand().get(2), new Card(EIGHT, SPADE));
        assertEquals(ranking.getHand().get(3), new Card(SEVEN, SPADE));
        assertEquals(ranking.getHand().get(4), new Card(FIVE, SPADE));
    }

    @Test
    void SimpleStraightTest(){

        Board board = new Board();
        board.addCard(new Card(SIX, DIAMOND));
        board.addCard(new Card(NINE, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, CLUB));
        board.addCard(new Card(TEN, HEART));

        Card handCard1 = new Card(FIVE, DIAMOND);
        Card handCard2 = new Card(SEVEN, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.STRAIGHT);

        assertEquals(ranking.getHand().get(0), new Card(TEN, HEART));
        assertEquals(ranking.getHand().get(1), new Card(NINE, SPADE));
        assertEquals(ranking.getHand().get(2), new Card(EIGHT, CLUB));
        assertEquals(ranking.getHand().get(3), new Card(SEVEN, SPADE));
        assertEquals(ranking.getHand().get(4), new Card(SIX, DIAMOND));
    }

    @Test
    void SimpleSetTest(){

        Board board = new Board();
        board.addCard(new Card(QUEEN, DIAMOND));
        board.addCard(new Card(NINE, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(EIGHT, CLUB));
        board.addCard(new Card(TEN, HEART));

        Card handCard1 = new Card(EIGHT, DIAMOND);
        Card handCard2 = new Card(EIGHT, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.SET);

        assertSame(ranking.getHand().get(0).getValue(), EIGHT);
        assertSame(ranking.getHand().get(1).getValue(), EIGHT);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(3), new Card(KING, HEART));
        assertEquals(ranking.getHand().get(4), new Card(QUEEN, DIAMOND));
    }

    @Test
    void SimpleDoublePairTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMOND));
        board.addCard(new Card(NINE, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(QUEEN, CLUB));
        board.addCard(new Card(TEN, HEART));

        Card handCard1 = new Card(QUEEN, DIAMOND);
        Card handCard2 = new Card(EIGHT, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.DOUBLE_PAIR);

        assertSame(ranking.getHand().get(0).getValue(), QUEEN);
        assertSame(ranking.getHand().get(1).getValue(), QUEEN);
        assertSame(ranking.getHand().get(2).getValue(), EIGHT);
        assertSame(ranking.getHand().get(3).getValue(), EIGHT);
        assertEquals(ranking.getHand().get(4), new Card(KING, HEART));
    }

    @Test
    void SimplePairTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMOND));
        board.addCard(new Card(NINE, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(QUEEN, CLUB));
        board.addCard(new Card(TEN, HEART));

        Card handCard1 = new Card(QUEEN, DIAMOND);
        Card handCard2 = new Card(TWO, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.PAIR);

        assertSame(ranking.getHand().get(0).getValue(), QUEEN);
        assertSame(ranking.getHand().get(1).getValue(), QUEEN);
        assertEquals(ranking.getHand().get(2), new Card(KING, HEART));
        assertEquals(ranking.getHand().get(3), new Card(TEN, HEART));
        assertEquals(ranking.getHand().get(4), new Card(NINE, SPADE));
    }

    @Test
    void SimpleHighCardTest(){

        Board board = new Board();
        board.addCard(new Card(EIGHT, DIAMOND));
        board.addCard(new Card(NINE, SPADE));
        board.addCard(new Card(KING, HEART));
        board.addCard(new Card(QUEEN, CLUB));
        board.addCard(new Card(TEN, HEART));

        Card handCard1 = new Card(ACE, DIAMOND);
        Card handCard2 = new Card(TWO, SPADE);
        Hand hand = new Hand(handCard1, handCard2, board);

        var ranking = calculator.getHandRanking(hand);

        assertSame(ranking.getRank(), HandCalculator.HandRanking.HIGH_CARD);

        assertEquals(ranking.getHand().get(0), new Card(ACE, DIAMOND));
        assertEquals(ranking.getHand().get(1), new Card(KING, HEART));
        assertEquals(ranking.getHand().get(2), new Card(QUEEN, CLUB));
        assertEquals(ranking.getHand().get(3), new Card(TEN, HEART));
        assertEquals(ranking.getHand().get(4), new Card(NINE, SPADE));
    }
}