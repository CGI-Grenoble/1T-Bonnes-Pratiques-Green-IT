import {Component, OnInit} from '@angular/core';
import {PrimeNGConfig} from 'primeng/api';
import {HttpClient} from "@angular/common/http";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root', templateUrl: './app.component.html', styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  //title = 'client';
  constructor(private primengConfig: PrimeNGConfig, private httpClient: HttpClient, private readonly keycloak: KeycloakService) {
  }

  ngOnInit() {
    this.primengConfig.ripple = true;
  }

  async logout() {
    await this.keycloak.logout(window.location.origin)
  }

  async call() {
    const token = await this.keycloak.getToken()
    this.httpClient.get<{ message: string }>('http://localhost:8081/hello', {}).subscribe(res => console.log(res))
  }
}
