import { Component, Input } from '@angular/core';
import { Player } from '../player';
import { Card, Color, Value } from '../card';

@Component({
  selector: 'app-gameview',
  templateUrl: './gameview.component.html',
  styleUrls: ['./gameview.component.css']
})
export class GameviewComponent {

  card1: Card = { value: Value.ACE, color: Color.DIAMOND }
  card2: Card = { value: Value.TEN, color: Color.CLUB }
  player: Player = { 
    id: 1, 
    name: "Player 1", 
    hand_cards: [this.card1, this.card2] ,
    invested_money: 100,
    status: "called",
    total_money: 19900
  }
  players: Player[] = [this.player,this.player,this.player,this.player]

  board_card_list = [
    { value: Value.ACE, color: Color.HEART },
    { value: Value.KING, color: Color.SPADE },
    { value: Value.NINE, color: Color.CLUB },
    { value: Value.EIGHT, color: Color.SPADE },
    { value: Value.THREE, color: Color.SPADE }
  ]
}
