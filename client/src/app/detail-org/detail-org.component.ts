import { Component, NgModule } from '@angular/core';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-detail-org',
  templateUrl: './detail-org.component.html',
  styleUrl: './detail-org.component.scss'
})


export class DetailOrgComponent {
  
  constructor(private router: Router) { }

  onContinue() {
    this.router.navigateByUrl('org');
  }

}
