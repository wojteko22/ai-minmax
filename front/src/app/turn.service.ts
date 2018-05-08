import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewMove} from './dto/new-move';
import {GameState} from './dto/game-state';
import {AutoMove} from './dto/auto-move';

@Injectable()
export class TurnService {

  url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  makeAMove(data: NewMove) {
    return this.http.post<GameState>(this.url, data);
  }

  makeAutoMove(moveData: AutoMove) {
    return this.http.post<GameState>(this.url + 'auto', moveData);
  }
}
