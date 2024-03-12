import { Component, NgModule, OnInit, input } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-org-create',
  templateUrl: './org-create.component.html',
  styleUrl: './org-create.component.scss',
})
export class OrgCreateComponent implements OnInit {
  name!: string;
  description!: string;
  visibilite!: boolean;
  nature = new FormControl('');
  bouton_label: string = 'Créer';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
   
  }

  createOrga(
    orga_name: string,
    orga_is_public: boolean,
    orga_description: string
  ) {
    const body = {
      name: orga_name,
      description: orga_description,
      is_public: orga_is_public,
    };

    // const rep = this.http
    //   .post('http://localhost:8081/api/organisations', body)
    //   .subscribe((donnees) => {
    //     console.log(donnees);
    //   });
  }

  childData($event: any) {
    if ($event == 'Publique') {
      this.visibilite = true;
    } else if ($event == 'Privée') {
      this.visibilite = false;
    } else {
      console.log('error is pulbic');
    }
  }
}
