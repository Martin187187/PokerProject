package com.stemmer.pokergame;

import com.stemmer.pokergame.helper.BasicHandCalculator;
import com.stemmer.pokergame.helper.HandCalculator;
import com.stemmer.pokergame.model.Board;
import com.stemmer.pokergame.model.Card;
import com.stemmer.pokergame.model.Hand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.stemmer.pokergame.model.Card.Color.*;
import static com.stemmer.pokergame.model.Card.Color.SPADES;
import static com.stemmer.pokergame.model.Card.Value.*;
import static org.junit.jupiter.api.Assertions.*;

public class HandComparisonTest {
    static HandCalculator calculator;
    @BeforeAll
    static void initiate(){
        calculator = new BasicHandCalculator();
    }


    @Test
    void BasicTest(){

        Board board = new Board();
        board.addCard(new Card(THREE, CLUBS));
        board.addCard(new Card(QUEEN, DIAMONDS));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(TEN, SPADES));

        Card handCard1 = new Card(FIVE, SPADES);
        Card handCard2 = new Card(SEVEN, SPADES);
        Hand hand1 = new Hand(handCard1, handCard2, board);
        Card handCard3 = new Card(ACE, SPADES);
        Card handCard4 = new Card(EIGHT, DIAMONDS);
        Hand hand2 = new Hand(handCard3, handCard4, board);


        assertNotSame(calculator.getHandRanking(hand1).getRank(), calculator.getHandRanking(hand2).getRank());
        assertEquals(calculator.compareHands(hand1, hand2), -1);
    }

    @Test
    void BothHandFlushTest(){

        Board board = new Board();
        board.addCard(new Card(THREE, SPADES));
        board.addCard(new Card(QUEEN, SPADES));
        board.addCard(new Card(KING, HEARTS));
        board.addCard(new Card(EIGHT, SPADES));
        board.addCard(new Card(TEN, SPADES));

        Card handCard1 = new Card(FIVE, SPADES);
        Card handCard2 = new Card(SEVEN, SPADES);
        Hand hand1 = new Hand(handCard1, handCard2, board);
        Card handCard3 = new Card(ACE, SPADES);
        Card handCard4 = new Card(EIGHT, DIAMONDS);
        Hand hand2 = new Hand(handCard3, handCard4, board);


        assertSame(calculator.getHandRanking(hand1).getRank(), calculator.getHandRanking(hand2).getRank());
        assertEquals(calculator.compareHands(hand1, hand2), -1);
    }

}
