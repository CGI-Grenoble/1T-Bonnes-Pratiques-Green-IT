import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {KeycloakCustomProfile} from "../keycloak-user";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-org-find',
  templateUrl: './org-find.component.html',
  styleUrl: './org-find.component.scss'
})
export class OrgFindComponent {

  orgas!: any;

  constructor(private http: HttpClient, private readonly keycloak: KeycloakService) { }

  async ngOnInit() {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile;
    const rep = this.http.get('http://localhost:8081/api/organisations').subscribe((donnees: any) => {
      this.orgas = donnees.filter((orga: any) => orga.is_public)
      this.http.get('http://localhost:8081/api/userOrganisations/' + userData.id).subscribe((myOrgas: any) => {
        for(const myorga of myOrgas) {
          if(this.orgas.includes(myorga)) {
            const index = this.orgas.indexOf(myorga)
            this.orgas.splice(index, 1)
          }
        }
        for(const orga of this.orgas) {
        this.http.get('http://localhost:8081/api/organisationUsers/' + orga.id).subscribe((users: any) => {
          orga.nmembers = users.length
        })
      }
      })
    });
  }

  async demandeRejoindre(orga: any) {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile

    const body = {
      organisation: orga,
      user_id: userData.id,
    };

    const repDecision = this.http
      .post('http://localhost:8081/api/orgaJoinRequests', body)
      .subscribe((donnees) => {console.log(donnees)});
  }

}
