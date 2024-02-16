import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

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
    
  formGroup: FormGroup | undefined;
  
  default : Language = { name: 'Français', code: 'fr' };

    ngOnInit() {
        
      this.languages = [
          { name: 'Français', code: 'fr' },
          { name: 'English', code: 'en' }
      ];

      this.formGroup = new FormGroup({
          selectedLanguage: new FormControl<Language | null>(null)
      });  
    }
}
