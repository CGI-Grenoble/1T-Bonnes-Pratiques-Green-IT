import { Component } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-org-edit',
  templateUrl: './org-edit.component.html',
  styleUrl: './org-edit.component.scss'
})
export class OrgEditComponent {
  name!: string;
  description!: string;
  visibilite!: string;
  nature =  new FormControl('');
  bouton_label = "Modifier"

  constructor(private http: HttpClient) {
  }

 

  childData($event :any){
   this.visibilite = $event }

  }

