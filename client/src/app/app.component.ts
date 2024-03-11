import {Component, OnInit} from '@angular/core';
import {PrimeNGConfig} from 'primeng/api';
import {HttpClient} from "@angular/common/http";
import {KeycloakService} from "keycloak-angular";
import {KeycloakCustomProfile} from './keycloak-user';

@Component({
  selector: 'app-root', templateUrl: './app.component.html', styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  //title = 'client';
  constructor(private primengConfig: PrimeNGConfig, private httpClient: HttpClient, private readonly keycloak: KeycloakService) {
  }

  async ngOnInit() {
    this.primengConfig.ripple = true;
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile

    // this.httpClient.post<{ message: string }>('http://localhost:8081/api/userOrganisations/remove/' + userData.id, "michelin").subscribe(res => console.log(res))
    // this.httpClient.get<{ message: string }>('http://localhost:8081/api/userOrganisations/' + userData.id, {}).subscribe(res => console.log(res))
    // this.httpClient.get<{ message: string }>('http://localhost:8081/api/organisationUsers/michelin', {}).subscribe(res => console.log(res))
  }
}
