import { Card, Color, Value } from "./card";

export class Deck {

    private card_deck: Array<Card>;

    public constructor(){
        this.card_deck = this.generate_deck();
    }

    public reset(): void{
        this.card_deck = this.generate_deck();
    }

    public get_random_card(): Card{
        const index = Math.floor(Math.random() * this.card_deck.length);
        const card = this.card_deck[index];
        console.log(card)
        if (index > -1) {
            this.card_deck.splice(index, 1);
         }
        return card;
    }
    public generate_deck(): Card[]{
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