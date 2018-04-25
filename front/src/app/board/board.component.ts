import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnChanges {

  @Input() sideSize: number;
  side: Array<number>;

  ngOnChanges(changes: SimpleChanges): void {
    this.side = Array(this.sideSize);
  }
}
