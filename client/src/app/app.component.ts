import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import {OAuthService} from "angular-oauth2-oidc";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  //title = 'client';
  constructor(private primengConfig: PrimeNGConfig, private oauthService: OAuthService, private httpClient: HttpClient) {}

    ngOnInit() {
        this.primengConfig.ripple = true;
    }

    logout() {
      this.oauthService.logOut()
    }

    call() {
      this.httpClient.get<{message: string}>('http://localhost:8081/hello', {
        headers: {
          'Authorization': `Bearer ${this.oauthService.getAccessToken()}`
        }
      }).subscribe(res => console.log(res))
    }
}
