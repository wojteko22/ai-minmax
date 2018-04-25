import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private validators = [Validators.required, Validators.min(2)];

  sideSizeControl = new FormControl(4, Validators.compose(this.validators));
  sideSize = this.sideSizeControl.value;

  constructor() {
    this.sideSizeControl.valueChanges.subscribe(value => this.tryToUpdateSize(value));
  }

  private tryToUpdateSize(value: number) {
    if (this.sideSizeControl.valid) {
      this.sideSize = value;
    }
  }
}
