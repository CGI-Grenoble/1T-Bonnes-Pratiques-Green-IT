import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-org',
  templateUrl: './org.component.html',
  styleUrl: './org.component.scss'
})
export class OrgComponent {

  constructor(public router: Router) { }
  onContinue() {
    this.router.navigateByUrl('org');
}

}
