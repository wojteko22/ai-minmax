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
  two = Array(0, 1);
  sideSizeControl = new FormControl(7, Validators.compose(this.validators));

  allModes = ['human', 'min-max', 'alpha-beta'];
  selectedModes = [this.allModes[2], this.allModes[2]];

  allStateHeuristics = ['points-advantage', 'points-player-max', 'points-opponent-min'];
  selectedStateHeuristics = [this.allStateHeuristics[0], this.allStateHeuristics[0]];

  allNodeHeuristics = ['consecutive', 'max-points-advantage', 'min-points-advantage', 'combined'];
  selectedNodeHeuristics = [this.allNodeHeuristics[0], this.allNodeHeuristics[0]];

  depths = [3, 3];
  myGameState: GameState;

  computerTurn = false;

  constructor(private turnService: TurnService) {
    this.reset();
    this.sideSizeControl.valueChanges.subscribe(value => this.tryToUpdateSize(value));
  }

  private tryToUpdateSize(size: number) {
    if (this.sideSizeControl.valid) {
      this.myGameState = new GameState(size);
    }
  }

  reset() {
    this.myGameState = new GameState(this.sideSizeControl.value);
  }

  onBoardFieldClick(rowIndex: number, columnIndex: number) {
    this.computerTurn = true;
    this.myGameState.board[rowIndex][columnIndex] = true;
    const data = new NewMove(this.myGameState, rowIndex, columnIndex);
    this.turnService.makeAMove(data).subscribe(gameState => this.updateState(gameState));
  }

  private updateState(gameState) {
    if (!gameState) {
      this.computerTurn = false;
      return;
    }
    this.myGameState = gameState;
    this.tryToMakeAutoMove();
  }

  tryToMakeAutoMove() {
    this.computerTurn = true;
    const mode = this.selectedModes[this.playerIndex];
    if (mode != this.allModes[0]) {
      const stateHeuristics = this.selectedStateHeuristics[this.playerIndex];
      const nodeHeuristics = this.selectedNodeHeuristics[this.playerIndex];
      const depth = this.depths[this.playerIndex];
      const moveData = new AutoMove(this.myGameState, mode, stateHeuristics, nodeHeuristics, depth);
      this.turnService.makeAutoMove(moveData).subscribe(gameState => this.updateState(gameState));
    } else {
      this.computerTurn = false;
    }
  }

  get playerIndex() {
    return this.myGameState.playerIndex;
  }
}
