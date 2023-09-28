export interface Card {
    value: string;
    color: string;
}

export enum Value {
    TWO = "TWO", THREE = "THREE", FOUR = "FOUR", FIVE = "FIVE", SIX = "SIX", SEVEN = "SEVEN", EIGHT = "EIGHT", NINE = "NINE", TEN = "TEN", JACK = "JACK", QUEEN = "QUEEN", KING = "KING", ACE = "ACE"
}

export enum Color {
    HEART = "HEARTS", DIAMOND = "DIAMONDS", SPADE = "SPADES", CLUB = "CLUBS"
}