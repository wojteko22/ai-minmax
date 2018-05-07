import {GameState} from './game-state';

export class NewTurn {
  constructor(
    public gameState: GameState,
    public rowIndex: number,
    public columnIndex: number,
    public nextPlayerMode: string,
  ) {
  }
}
