import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {KeycloakService} from "keycloak-angular";
import { KeycloakCustomProfile } from '../keycloak-user';
import { MenuItem, MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrl: './join.component.scss'
})
export class JoinComponent {

  constructor(private router: Router, private readonly keycloak: KeycloakService, private http: HttpClient) {
  }

  userName! :string;
  userId !: string;
  orgasId !: any;
  orgas !: any;
  game !: any;
  link !: string;
  user !: any;

  async ngOnInit() {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile
    this.user = userData;
    this.userName = userData.firstName + " " + userData.lastName;
    this.userId = userData.id ?? '';

    const orgasId = this.http.get('http://localhost:8081/api/userOrganisations/'+this.userId).subscribe((donnees) => {
      this.orgas= donnees;
    })
  }

  async createGame (orga:any){
    console.log(orga)
    const rep = this.http.post('http://localhost:8081/api/games', orga).subscribe((donnees) => {
      this.game = donnees;
      console.log(this.game.id)
      this.router.navigate(['create/game/'+this.game.id]);
      this.link = this.router.url;
      console.log(this.link); 
      const yes = this.http.post('http://localhost:8081/api/games/'+this.game.id+'/create', this.userId ).subscribe((donnees) => {
      })
    });
  }




}
