import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class OrgaServiceService {
  constructor(private http: HttpClient) {}

  postCreateOrga(body: any) {
    console.log('postCreateOrga');
    const rep = this.http
      .post('http://localhost:8081/api/organisations', body)
      .subscribe((donnees) => {
        console.log(donnees);
      });
  }
}
