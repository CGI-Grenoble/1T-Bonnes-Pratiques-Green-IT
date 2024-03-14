import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {KeycloakService} from "keycloak-angular";
import {KeycloakCustomProfile} from "../keycloak-user";

@Component({
  selector: 'app-org-create', templateUrl: './org-create.component.html', styleUrl: './org-create.component.scss',
})
export class OrgCreateComponent implements OnInit {
  name!: string;
  description!: string;
  visibilite!: boolean;
  nature = new FormControl('');
  bouton_label: string = 'Créer';

  constructor(private http: HttpClient, private readonly keycloak: KeycloakService) {
  }

  ngOnInit(): void {
  }

  async createOrga(orga_name: string, orga_is_public: boolean, orga_description: string) {
    console.log("CreateOrga");
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile
    const body = {
      name: orga_name, description: orga_description, is_public: orga_is_public,
    };
    const rep = this.http.post('http://localhost:8081/api/organisations', body)
      .subscribe((donnees) => {
        const orgaCreated: any = donnees;
        this.http.post('http://localhost:8081/api/userOrganisations/add/' + userData.id, orgaCreated.id).subscribe((res) => console.log(res))
      });

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
