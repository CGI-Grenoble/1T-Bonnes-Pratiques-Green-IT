import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-org-accueil',
  templateUrl: './org-accueil.component.html',
  styleUrl: './org-accueil.component.scss'
})
export class OrgAccueilComponent implements OnInit{

  orgas!: any;

  constructor(private http: HttpClient) { }
  

  test(){
    const rep = this.http.get('http://localhost:8081/api/organisations').subscribe((donnees) => {
      this.orgas = donnees;
    });
  }

  ngOnInit(): void {
    const rep = this.http.get('http://localhost:8081/api/organisations').subscribe((donnees) => {
      this.orgas = donnees;
    });
  }

}
