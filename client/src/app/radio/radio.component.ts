import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgModel } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-radio',
  templateUrl: './radio.component.html',
  styleUrl: './radio.component.scss',
})
export class RadioComponent implements OnInit {
  @Output() public visibilite = new EventEmitter<string>();
  visible!: string;
  formulaire!: FormGroup;
  
  onChange(){
    this.visibilite.emit(this.visible);
   
  }
  
  ngOnInit(): void {
    
     this.formulaire = new FormGroup({
        visibility: new FormControl(),
      });
  }


}
