import {GameState} from './game-state';

export class AutoMove {
  constructor(
    public gameState: GameState,
    public mode: string,
  ) {
  }
}
