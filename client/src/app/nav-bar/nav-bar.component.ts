import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { HttpClient } from '@angular/common/http';


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

  languages: Language[] | undefined;

  items: MenuItem[] | undefined;

  modes : any[] | undefined;
    
  formGroup: FormGroup | undefined;

  idGame !: number;
  
  default : Language = { name: 'Français', code: 'fr' };

  constructor(private http: HttpClient) { }

  async linkGame(){
    const date = new Date();
    const rep = this.http.post('http://localhost:8081/api/games', date).subscribe((donnees) => {
      console.log(donnees);
    });
  
  }

    ngOnInit() {
      this.linkGame();
      this.languages = [
          { name: 'Français', code: 'fr' },
          { name: 'English', code: 'en' }
      ];

      this.modes = [
        { name: 'Rejoindre', code: 'mode1' },
        { name: 'Créer', code: 'mode2' }
      ]

      this.items = [
        {
          label: 'Rejoindre',
          routerLink: '/join'
      },
      {
          label: 'Créer',
          routerLink: '/join'
      }
      ]

      this.formGroup = new FormGroup({
          selectedLanguage: new FormControl<Language | null>(null)
      });  
    }
}
