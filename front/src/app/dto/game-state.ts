export class GameState {

  public board: Array<Array<boolean>>;
  public points: Array<number> = [0, 0];
  public playerIndex: number = 0;

  constructor(boardSize: number) {
    this.createBoard(boardSize);
  }

  createBoard(size: number) {
    this.board = Array(size);
    for (let i = 0; i < this.board.length; i++) {
      this.board[i] = Array(size).fill(false);
    }
  }
}
