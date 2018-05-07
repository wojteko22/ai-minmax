import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {BoardClick} from './board-click';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnChanges {

  @Input() sideSize: number;
  @Output() fieldClick: EventEmitter<BoardClick> = new EventEmitter();
  board: Array<Array<boolean>>;

  ngOnChanges(changes: SimpleChanges): void {
    this.board = Array(this.sideSize);
    for (let i = 0; i < this.board.length; i++) {
      this.board[i] = Array(this.sideSize).fill(false);
    }
  }

  onFieldClick(rowIndex: number, columnIndex: number) {
    this.board[rowIndex][columnIndex] = true;
    const boardClick = new BoardClick(this.board, rowIndex, columnIndex);
    this.fieldClick.emit(boardClick);
  }
}
