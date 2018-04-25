import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewTurn} from './new-turn';

@Injectable()
export class TurnService {

  constructor(private http: HttpClient) {
  }

  sendTurn(data: NewTurn) {
    return this.http.post('', data);
  }
}
