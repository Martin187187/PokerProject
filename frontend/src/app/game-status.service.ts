import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Game } from './game';
import { GAME_STATUS } from './mock-game-status';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameStatusService {

  private heroesUrl = 'http://localhost:8080/game';  // URL to web api

  constructor(private http: HttpClient) { }

  get_game_status(): Observable<Game> {
    const game_status = of(GAME_STATUS);
    return game_status;
  }

  get_game_http(): Observable<Game> {
    const result = this.http.get<Game>(this.heroesUrl)
    return result
  }
}
