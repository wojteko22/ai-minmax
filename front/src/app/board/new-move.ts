import {GameState} from './game-state';

export class NewMove {
  constructor(
    public gameState: GameState,
    public rowIndex: number,
    public columnIndex: number,
  ) {
  }
}
