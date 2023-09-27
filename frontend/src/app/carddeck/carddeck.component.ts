import { Component } from '@angular/core';
import { Card, Color, Value } from '../card';

@Component({
  selector: 'app-carddeck',
  templateUrl: './carddeck.component.html',
  styleUrls: ['./carddeck.component.css']
})
export class CarddeckComponent {
  
  deck: Card[] = this.generate_Deck();

  generate_Deck(): Card[]{
    const result: Card[] = [];

    const enumValues: Value[] = Object.values(Value);
    const enumColors: Color[] = Object.values(Color);

    // Iterate over the enum values
    for (const enumValue of enumValues) {
      for ( const enumColor of enumColors){
        const card: Card = {value: enumValue, color: enumColor}
        result.push(card);
      }
    }
    return result;
  }
}
