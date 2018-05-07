import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {BoardClick} from './board/board-click';
import {NewTurn} from './board/new-turn';
import {GameState} from './board/game-state';
import {TurnService} from './board/turn.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [TurnService],
})
export class AppComponent {
  private validators = [Validators.required, Validators.min(2)];

  sideSizeControl = new FormControl(4, Validators.compose(this.validators));
  sideSize = this.sideSizeControl.value;
  points = 0;

  constructor(private turnService: TurnService) {
    this.sideSizeControl.valueChanges.subscribe(value => this.tryToUpdateSize(value));
  }

  private tryToUpdateSize(value: number) {
    if (this.sideSizeControl.valid) {
      this.sideSize = value;
    }
  }

  onUpdateGameState(boardClick: BoardClick) {
    const {board, rowIndex, columnIndex} = boardClick;
    const gameState = new GameState(board, this.points);
    const data = new NewTurn(gameState, rowIndex, columnIndex);
    this.turnService.sendTurn(data).subscribe(gameState => this.points = gameState.points);
  }
}
