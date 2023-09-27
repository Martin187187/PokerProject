import { Color, Value } from './card';
import { Deck } from './deck';
import { Game } from './game';

const deck = new Deck();
export const GAME_STATUS: Game = {
  board_cards: [
    deck.get_random_card(),
    deck.get_random_card(),
    deck.get_random_card(),
    deck.get_random_card(),
    deck.get_random_card(),
  ],
  players: [
    {
      id: 1,
      name: 'Player 1',
      hand_cards: [deck.get_random_card(), deck.get_random_card()],
      invested_money: 34677,
      status: 'folded',
      total_money: 19900,
    },
    {
      id: 1,
      name: 'Player 2',
      hand_cards: [deck.get_random_card(), deck.get_random_card()],
      invested_money: 100,
      status: 'called',
      total_money: 45678,
    },
    {
      id: 1,
      name: 'Player 3',
      hand_cards: [deck.get_random_card(), deck.get_random_card()],
      invested_money: 4563,
      status: 'raised',
      total_money: 23457,
    },
    {
      id: 1,
      name: 'Player 4',
      hand_cards: [deck.get_random_card(), deck.get_random_card()],
      invested_money: 3445,
      status: 'called',
      total_money: 34573,
    },
  ],
};
