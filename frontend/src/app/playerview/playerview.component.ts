import { Component, Input } from '@angular/core';
import { Player } from '../player';

@Component({
  selector: 'app-playerview',
  templateUrl: './playerview.component.html',
  styleUrls: ['./playerview.component.css']
})
export class PlayerviewComponent {

  @Input() player!: Player;
}
