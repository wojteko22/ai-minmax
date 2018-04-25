import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {TurnService} from './turn.service';

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

  playTurn(row: Array<boolean>, index: number) {
    row[index] = true;
    this.turnService.sendTurn();
  }
}
