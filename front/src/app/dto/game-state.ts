export class GameState {
  constructor(
    public board: Array<Array<boolean>>,
    public points: Array<number>,
    public playerIndex: number,
  ) {
  }
}
