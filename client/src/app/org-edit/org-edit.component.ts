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

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    console.log(this.route.snapshot.firstChild?.params['id']);
    const rep = this.http
      .get(
        'http://localhost:8081/api/organisations/' +
          this.route.snapshot.firstChild?.params['id']
      )
      .subscribe((donnees) => {
        this.orgaData = donnees;
        console.log(this.orgaData);
      });
  }

  childData($event: any) {
    this.visibilite = $event;
  }
}
