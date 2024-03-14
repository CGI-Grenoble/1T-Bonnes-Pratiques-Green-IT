import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IWaintingMember } from '../org-edit/org-edit.component';

@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrl: './members.component.scss',
})
export class MembersComponent {
  @Input('memberWaitingData') public memberWaitingData: IWaintingMember[] = [];
  @Input('decide') decide: Function | undefined;

  constructor(private http: HttpClient) {}

  loading: boolean = true;

  sendResponse(accept: boolean, requestID: string){
    if (this.decide) {
      this.decide(accept, requestID);
    }
  }

  ngOnInit() {
    console.log(this.memberWaitingData);
    setTimeout(() => {
      // Fetch data here...
      this.loading = false;
    }, 1000);
  }
}
