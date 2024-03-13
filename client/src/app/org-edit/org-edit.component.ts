import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-org-edit',
  templateUrl: './org-edit.component.html',
  styleUrl: './org-edit.component.scss',
})
export class OrgEditComponent implements OnInit {
  name!: string;
  description!: string;
  visibilite!: boolean;
  nature = new FormControl('');
  bouton_label = 'Modifier';
  orgaData!: any;
  memberWaitingData!: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    // console.log(this.route.snapshot.firstChild?.url[1].path);
    const repOrga = this.http
      .get(
        'http://localhost:8081/api/organisations/' +
          this.route.snapshot.firstChild?.url[1].path
      )
      .subscribe((donnees) => {
        this.orgaData = donnees;
        console.log(this.orgaData);
        const repMemberWaiting = this.http
          .get('http://localhost:8081/api/orgaJoinRequests/forOrga/' + this.orgaData.id)
          .subscribe((donnees) => {
            this.memberWaitingData = donnees;
            console.log(this.memberWaitingData);
            
          });
      });
  }

  childData($event: any) {
    this.visibilite = $event;
  }

  onClick(accept:boolean){
    const repDecision = this.http
    .post('http://localhost:8081/api/orgaJoinRequests/' + this.memberWaitingData[1].id + '/decide',"accept" )
    .subscribe((donnees) => {
      console.log(donnees);
    });
  }

  demandeRejoindre(){
    const body = {
      "id":0,
      "organisation":this.orgaData,
      "user_id":'fb9819f8-4297-4a4a-8aca-9f78f2d13e38'
    }

    const repDecision = this.http
    .post('http://localhost:8081/api/orgaJoinRequests',body )
    .subscribe((donnees) => {
      console.log(donnees);
    });
  }
}
