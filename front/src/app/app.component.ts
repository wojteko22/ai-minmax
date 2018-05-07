import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {NewTurn} from './board/new-turn';
import {GameState} from './board/game-state';
import {TurnService} from './board/turn.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [TurnService],
})
export class AppComponent {
  private validators = [Validators.required, Validators.min(2)];

  sideSizeControl = new FormControl(4, Validators.compose(this.validators));
  points = [0, 0];
  allModes = ["human", "consecutive"];
  selectedModes = [this.allModes[0], this.allModes[0]];
  playerIndex = 0;
  board: Array<Array<boolean>>;

  constructor(private turnService: TurnService) {
    this.sideSizeControl.valueChanges.subscribe(value => this.tryToUpdateSize(value));
    this.createBoard(this.sideSizeControl.value);
  }

  private tryToUpdateSize(size: number) {
    if (this.sideSizeControl.valid) {
      this.createBoard(size);
    }
  }

  private createBoard(size: number) {
    this.board = Array(size);
    for (let i = 0; i < this.board.length; i++) {
      this.board[i] = Array(size).fill(false);
    }
  }

  onBoardFieldClick(rowIndex: number, columnIndex: number) {
    this.board[rowIndex][columnIndex] = true;
    const gameState = new GameState(this.board, this.points, this.playerIndex);
    const nextPlayerIndex = (this.playerIndex + 1) % 2;
    const nextPlayerMode = this.selectedModes[nextPlayerIndex];
    const data = new NewTurn(gameState, rowIndex, columnIndex, nextPlayerMode);
    this.turnService.sendTurn(data).subscribe(gameState => this.updateState(gameState));
  }

  private updateState(gameState) {
    this.board = gameState.board;
    this.points = gameState.points;
    this.playerIndex = gameState.playerIndex;
  }
}
