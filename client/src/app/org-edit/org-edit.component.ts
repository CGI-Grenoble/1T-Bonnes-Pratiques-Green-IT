import { Component } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-org-edit',
  templateUrl: './org-edit.component.html',
  styleUrl: './org-edit.component.scss'
})
export class OrgEditComponent {
  name!: string;
  description!: string;
  visibilite!: boolean;
  nature =  new FormControl('');
  bouton_label = "Modifier"
 

  childData($event :any){
   this.visibilite = $event }

  }

