import {GameState} from './game-state';

export class NewTurn {
  constructor(public gameState: GameState, public playerIndex: number, public rowIndex: number, public columnIndex: number) {
  }
}
