import { Card } from "./card";
import { Player } from "./player";

export interface Game {

    players: Player[];
    boardCards: Card[];
}