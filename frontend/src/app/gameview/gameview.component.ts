import { Component, Input } from '@angular/core';
import { Player } from '../player';
import { Card, Color, Value } from '../card';
import { Game } from '../game';
import { GAME_STATUS } from '../mock-game-status';
import { GameStatusService } from '../game-status.service';

@Component({
  selector: 'app-gameview',
  templateUrl: './gameview.component.html',
  styleUrls: ['./gameview.component.css']
})
export class GameviewComponent {
  game?: Game;

  constructor(private gameStatusService: GameStatusService){}

  
  ngOnInit(): void {
    this.gameStatusService.get_game_status()
    .subscribe(gameStatus => this. game = gameStatus);
  }
}
