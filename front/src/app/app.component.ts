import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private validators = [Validators.required, Validators.min(2)];

  sideSize = new FormControl(4, Validators.compose(this.validators));
  side = Array(this.sideSize.value);

  constructor() {
    this.sideSize.valueChanges.subscribe(value => this.tryToUpdateSize(value));
  }

  private tryToUpdateSize(value: number) {
    if (this.sideSize.valid) {
      this.side = Array(value);
    }
  }
}
