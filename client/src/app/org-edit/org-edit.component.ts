import { Component, OnInit, booleanAttribute } from '@angular/core';
import { FormControl, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

export interface IWaintingMember {
  id: number;
  firstName: string;
  lastName: string;
  joinRequestId: string;
}

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
  memberWaitingData: IWaintingMember[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const repOrga = this.http
      .get(
        'http://localhost:8081/api/organisations/' +
          this.route.snapshot.firstChild?.url[1].path
      )
      .subscribe((donnees) => {
        this.orgaData = donnees;
        this.updateListDemandes();
      });
  }

  childData($event: any) {
    this.visibilite = $event;
  }

  decide(accept: boolean, requestID: string) {
    let choice: string;
    if (accept) {
      choice = 'accept';
    } else {
      choice = 'decline';
    }
    const repDecision = this.http
      .post(
        'http://localhost:8081/api/orgaJoinRequests/' + requestID + '/decide',
        choice
      )
      .subscribe((donnees) => {});
  }


  //fonction de test, attention : user_id à modifier
  demandeRejoindre() {
    const body = {
      organisation: this.orgaData,
      user_id: 'd01270c6-1568-446b-bc72-803897d8b455',
    };

    const repDecision = this.http
      .post('http://localhost:8081/api/orgaJoinRequests', body)
      .subscribe((donnees) => {console.log(donnees)});
  }

  updateListDemandes() {
    const repMemberWaiting = this.http
      .get(
        'http://localhost:8081/api/orgaJoinRequests/forOrga/' + this.orgaData.id
      )
      .subscribe((donnees: any) => {
        for (const demande of donnees) {
          const memberWaiting: IWaintingMember = {
            id: demande.userInfo.id,
            firstName: demande.userInfo.firstName,
            lastName: demande.userInfo.lastName,
            joinRequestId: demande.id,
          };
          this.memberWaitingData.push(memberWaiting);
        }
      });
  }

  async editOrga(
    orga_name: string,
    orga_is_public: string,
    orga_description: string
  ) {
    if (orga_name) {
      this.orgaData.name = orga_name;
    }

    if (orga_is_public == 'Publique') {
      this.orgaData.is_public = true;
    } else if (orga_is_public == 'Privée') {
      this.orgaData.is_public = false;
    }
    if (orga_description) {
      this.orgaData.description = orga_description;
    }

    const rep = this.http
      .put(
        'http://localhost:8081/api/organisations/' + this.orgaData.id,
        this.orgaData
      )
      .subscribe((donnees) => {});
  }
}
