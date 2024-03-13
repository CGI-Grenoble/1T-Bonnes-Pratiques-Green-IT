import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {KeycloakService} from "keycloak-angular";
import { KeycloakCustomProfile } from '../keycloak-user';
import { MenuItem, MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrl: './join.component.scss'
})
export class JoinComponent {

  constructor(private readonly keycloak: KeycloakService, private http: HttpClient) {
  }

  userName! :string;
  userId !: string;
  orgas !: any;
  game !: any;
  link !: string;

  async ngOnInit() {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile
    this.userName = userData.firstName + " " + userData.lastName;
    this.userId = userData.id ?? '';

    const orgas = this.http.get('http://localhost:8081/api/userOrganisations/'+this.userId).subscribe((donnees) => {
      this.orgas = donnees;
      console.log(donnees); 
    })
  }

  async createGame (orga:any){
   
    const rep = this.http.post('http://localhost:8081/api/games', {organisation: orga}).subscribe((donnees) => {
      this.game = donnees;
    });
    this.link = "game/" + this.game.id;
  }




}
