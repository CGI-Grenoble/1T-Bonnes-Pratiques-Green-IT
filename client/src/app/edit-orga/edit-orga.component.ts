import { Component, NgModule, input } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ToastComponent } from '../toast/toast.component';
import { RadioButtonModule } from 'primeng/radiobutton';

import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-edit-orga',
  templateUrl: './edit-orga.component.html',
  styleUrl: './edit-orga.component.scss',
})
export class EditOrgaComponent {
  name!: string;
  description!: string;
  visibilite!: string;
  nature =  new FormControl('');
  bouton_label : string = "Créer"
 

  childData($event :any){
   this.visibilite = $event }

  }
