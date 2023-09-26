package com.stemmer.pokergame.model;

import java.util.Objects;

public class Card implements Comparable<Card>{

    public enum Value { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
    public enum Color { HEART, DIAMOND, SPADE, CLUB}

    private Value value;
    private Color color;

    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return value +"-"+ color;
    }

    @Override
    public int compareTo(Card o) {
        if(value == o.value)
            return 0;
        return value.ordinal() < o.value.ordinal() ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return value == card.value && color == card.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color);
    }
}
