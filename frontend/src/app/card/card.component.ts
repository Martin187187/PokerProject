import { Component, Input } from '@angular/core';
import { Card } from '../card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent {
  @Input() card!: Card;

  getPath(card: Card): string {
    return "../../assets/cards/".concat(card.value).concat("_of_").concat(card.color).concat(".svg");
  }
}
