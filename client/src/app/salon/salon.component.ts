import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-salon',
  templateUrl: './salon.component.html',
  styleUrl: './salon.component.scss',
})
export class SalonComponent {

  lien: string = "";
  gameId !: number;
  membersInfo !: any;
  members = <any>[];
  loading: boolean = true;

  constructor(private router: Router, private http: HttpClient){}

  async ngOnInit() {
    setTimeout(() => {
      // Fetch data here...
      this.loading = false;
  }, 1000);
    this.lien = window.location.href;
    const elements = this.lien.split('/')
    const rep = this.http.get('http://localhost:8081/api/games/'+elements[elements.length-1]+'/users').subscribe((donnees) => {
      this.membersInfo= donnees;
      console.log(donnees)
      let i = 0;
      for (let member of this.membersInfo){
        this.members.push({'name': member.firstName+" "+member.lastName, 'id':i})
        i++;
      }
    });
  }
}
