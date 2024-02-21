import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-radio',
  templateUrl: './radio.component.html',
  styleUrl: './radio.component.scss',
})
export class RadioComponent {

  formulaire: FormGroup;

  constructor() {
    this.formulaire = new FormGroup({
      visibility: new FormControl()
    });
  }
}
