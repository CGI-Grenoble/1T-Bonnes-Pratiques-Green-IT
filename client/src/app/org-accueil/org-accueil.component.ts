import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {KeycloakService} from "keycloak-angular";
import {KeycloakCustomProfile} from "../keycloak-user";

@Component({
  selector: 'app-org-accueil',
  templateUrl: './org-accueil.component.html',
  styleUrl: './org-accueil.component.scss'
})
export class OrgAccueilComponent implements OnInit{

  @Output() newOrgaEvent = new EventEmitter<string>();


  orgas!: any;
  orga !: any;

  constructor(private http: HttpClient, private readonly keycloak: KeycloakService) { }

  onSendOrga() {
    console.log('book in ChildComponent:', this.orga);
    this.newOrgaEvent.emit(this.orga);
  }

  async ngOnInit() {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile
    const rep = this.http.get('http://localhost:8081/api/userOrganisations/' + userData.id).subscribe((donnees) => {
      this.orgas = donnees;
    });
  }

}
