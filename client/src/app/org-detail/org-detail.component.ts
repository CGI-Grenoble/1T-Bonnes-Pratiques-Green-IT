import { Component, Input, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RouterModule, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-org-detail',
  templateUrl: './org-detail.component.html',
  styleUrl: './org-detail.component.scss'
})


export class OrgDetailComponent {

  @Input() org!: any;
  @Input() show!: boolean;

  orga !: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }

  async ngOnInit() {
      const rep = this.http.get('http://localhost:8081/api/organisations/'+ (this.route.snapshot.firstChild?.params['id'])).subscribe((donnees) => {
        this.orga = donnees;
        console.log(this.orga);
        console.log('http://localhost:8081/api/organisations/'+ (this.route.snapshot.firstChild?.params['id']))
      });
    /* const body = {
      name: "test",
      description: "clc",
      is_public: true,
    };
    const fsdfs = this.http
      .post('http://localhost:8081/api/organisations', body)
      .subscribe((donnees) => {
        console.log(donnees);
      }); */
  }
  

  
  /* constructor(private router: Router) { }

  onContinue() {
    this.router.navigateByUrl('org');
  } */

}
