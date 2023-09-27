export interface Card {
    value: Value;
    color: Color;
}

export enum Value {
    TWO = "2", THREE = "3", FOUR = "4", FIVE = "5", SIX = "6", SEVEN = "7", EIGHT = "8", NINE = "9", TEN = "10", JACK = "jack", QUEEN = "queen", KING = "king", ACE = "ace"
}

export enum Color {
    HEART = "hearts", DIAMOND = "diamonds", SPADE = "spades", CLUB = "clubs"
}