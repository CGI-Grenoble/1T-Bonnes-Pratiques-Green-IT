import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface IMember {
  name: string;
  id: number;
}

@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrl: './members.component.scss',
})
export class MembersComponent {

  constructor (private http: HttpClient){  }

  getMembersByOrgaId( id :number) {
    
  }
  public members: IMember[] = [
    { name: 'Aziz', id: 0 },
    { name: 'Eloi', id: 1 },
    { name: 'Alix', id: 2 },
  ];

  suppr() : void {
    console.log("deleted");
  }

  ngOnInit() {
    //appeler requete pour recuperer les membres d'une orga
    //  this.productService.getProducts().then((data) => (this.products = data.slice(0, 5)));
  }
}
