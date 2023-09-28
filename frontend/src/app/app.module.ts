import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GameviewComponent } from './gameview/gameview.component';
import { CardComponent } from './card/card.component';
import { PlayerviewComponent } from './playerview/playerview.component';
import { BoardComponent } from './board/board.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    GameviewComponent,
    CardComponent,
    PlayerviewComponent,
    BoardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
