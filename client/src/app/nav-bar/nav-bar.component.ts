import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {KeycloakService} from "keycloak-angular";

interface Language {
  name: string;
  code: string;
} 

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})

export class NavBarComponent implements OnInit{

  constructor(private readonly keycloak: KeycloakService) {
  }

  languages: Language[] | undefined;
    
  formGroup: FormGroup | undefined;
  
  default : Language = { name: 'Français', code: 'fr' };

  userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile;
  name : string = this.userData.firstName + " " + this.userData.lastName

    ngOnInit() {
        
      this.languages = [
          { name: 'Français', code: 'fr' },
          { name: 'English', code: 'en' }
      ];

      this.formGroup = new FormGroup({
          selectedLanguage: new FormControl<Language | null>(null)
      });  
    }

    async logout() {
      await this.keycloak.logout(window.location.origin)
    }
  
}
