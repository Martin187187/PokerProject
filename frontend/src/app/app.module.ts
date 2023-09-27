import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GameviewComponent } from './gameview/gameview.component';
import { CardComponent } from './card/card.component';
import { CarddeckComponent } from './carddeck/carddeck.component';
import { PlayerviewComponent } from './playerview/playerview.component';
import { BoardComponent } from './board/board.component';

@NgModule({
  declarations: [
    AppComponent,
    GameviewComponent,
    CardComponent,
    CarddeckComponent,
    PlayerviewComponent,
    BoardComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
