import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Game } from './game';
import { GAME_STATUS } from './mock-game-status';

@Injectable({
  providedIn: 'root'
})
export class GameStatusService {

  constructor() { }

  get_game_status(): Observable<Game> {
    const game_status = of(GAME_STATUS);
    return game_status;
  }
}
