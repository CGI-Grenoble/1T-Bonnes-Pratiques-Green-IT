import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class OrgService {
    constructor(private http: HttpClient) {}

    // postCreateOrga(body:any) {
    //     console.log("postCreateOrga");
    //     const rep = this.http
    //       .post('http://localhost:8081/api/organisations', body)
    //       .subscribe((donnees) => {
    //         console.log(donnees);
    //       });
    //   }
}