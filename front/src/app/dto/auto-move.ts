import {GameState} from './game-state';

export class AutoMove {
  constructor(
    public gameState: GameState,
    public mode: string,
    public stateHeuristicsName: string,
    public depth: number,
  ) {
  }
}
