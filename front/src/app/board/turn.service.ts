import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewTurn} from './new-turn';
import {GameState} from './game-state';

@Injectable()
export class TurnService {

  url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  sendTurn(data: NewTurn) {
    return this.http.post<GameState>(this.url, data);
  }
}
