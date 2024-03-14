import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-org-accueil',
  templateUrl: './org-accueil.component.html',
  styleUrl: './org-accueil.component.scss'
})
export class OrgAccueilComponent implements OnInit{

  @Output() newOrgaEvent = new EventEmitter<string>();

  
  orgas!: any;
  orga !: any;
  
  constructor(private http: HttpClient) { }
  
  onSendOrga() {
    console.log('book in ChildComponent:', this.orga);
    this.newOrgaEvent.emit(this.orga);
  }

  ngOnInit(): void {
    const rep = this.http.get('http://localhost:8081/api/organisations').subscribe((donnees) => {
      this.orgas = donnees;
    });
  }

}
