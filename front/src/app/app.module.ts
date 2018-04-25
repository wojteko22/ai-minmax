import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule, MatInputModule} from '@angular/material';

import {ReactiveFormsModule} from '@angular/forms';
import {BoardComponent} from './board/board.component';


@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
  ],
  imports: [
    BrowserModule,

    ReactiveFormsModule,

    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
