import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {TurnService} from './turn.service';
import {NewTurn} from './new-turn';
import {GameState} from './game-state';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
  providers: [TurnService],
})
export class BoardComponent implements OnChanges {

  @Input() sideSize: number;
  board: Array<Array<boolean>>;

  constructor(private turnService: TurnService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.board = Array(this.sideSize);
    for (let i = 0; i < this.board.length; i++) {
      this.board[i] = Array(this.sideSize).fill(false);
    }
  }

  playTurn(rowIndex: number, columnIndex: number) {
    this.board[rowIndex][columnIndex] = true;
    const gameState = new GameState(this.board, 0);
    const data = new NewTurn(gameState, rowIndex, columnIndex);
    this.turnService.sendTurn(data).subscribe(result => console.log(result.points));
  }
}
