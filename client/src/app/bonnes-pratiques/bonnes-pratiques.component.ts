import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {KeycloakService} from "keycloak-angular";
import {KeycloakCustomProfile} from "../keycloak-user";

@Component({
  selector: 'app-bonnes-pratiques',
  templateUrl: './bonnes-pratiques.component.html',
  styleUrl: './bonnes-pratiques.component.scss'
})
export class BonnesPratiquesComponent {

  BP_to_do: any;
  BP_done: any;
  BP_undoable: any;
  BP_unseen_count: any;

  constructor(private http: HttpClient, private keycloak: KeycloakService) {
  }

  async ngOnInit() {
    let userData = await this.keycloak.loadUserProfile() as KeycloakCustomProfile

    this.http.get('http://localhost:8081/api/cards/cardCount').subscribe((cardsCount: any) => {
      this.http.get('http://localhost:8081/api/favorites/forUser/' + userData.id).subscribe((data: any) => {
        this.BP_to_do = data.filter((f: any) => f.category === "TODO")
        this.BP_done = data.filter((f: any) => f.category === "ALREADY_APPLIED")
        this.BP_undoable = data.filter((f: any) => f.category === "NOT_APPLICABLE")
        this.BP_unseen_count = cardsCount - data.length
      });
    })


  }
}
