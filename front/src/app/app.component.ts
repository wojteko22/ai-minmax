import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {NewMove} from './dto/new-move';
import {GameState} from './dto/game-state';
import {TurnService} from './turn.service';
import {AutoMove} from './dto/auto-move';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [TurnService],
})
export class AppComponent {
  private validators = [Validators.required, Validators.min(2)];

  sideSizeControl = new FormControl(4, Validators.compose(this.validators));
  allModes = ["human", "consecutive", "points", "alpha-beta"];
  selectedModes = [this.allModes[3], this.allModes[3]];
  myGameState = new GameState(this.sideSizeControl.value);

  constructor(private turnService: TurnService) {
    this.sideSizeControl.valueChanges.subscribe(value => this.tryToUpdateSize(value));
  }

  private tryToUpdateSize(size: number) {
    if (this.sideSizeControl.valid) {
      this.myGameState = new GameState(size);
    }
  }

  onBoardFieldClick(rowIndex: number, columnIndex: number) {
    this.myGameState.board[rowIndex][columnIndex] = true;
    const data = new NewMove(this.myGameState, rowIndex, columnIndex);
    this.turnService.makeAMove(data).subscribe(gameState => this.updateState(gameState));
  }

  private updateState(gameState) {
    if (!gameState) {
      return;
    }
    this.myGameState = gameState;
    this.makeAutoMove();
  }

  makeAutoMove() {
    const mode = this.selectedModes[this.myGameState.playerIndex];
    if (mode != this.allModes[0]) {
      const moveData = new AutoMove(this.myGameState, mode);
      this.turnService.makeAutoMove(moveData).subscribe(gameState => this.updateState(gameState));
    }
  }
}
