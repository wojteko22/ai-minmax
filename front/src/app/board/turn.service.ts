import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class TurnService {

  constructor(private http: HttpClient) {
  }

  sendTurn() {
    return this.http.post('', null);
  }
}
