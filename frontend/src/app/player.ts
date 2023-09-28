import { Card } from "./card";

export interface Player {
    id: number;
    name: string;

    hand_cards: Card[];

    status: string; // folded, has to call 50, raised to 500, ...
    invested_money: number;
    total_money: number ;

}