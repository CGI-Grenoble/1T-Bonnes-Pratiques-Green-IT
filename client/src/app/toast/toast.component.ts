import { Component, Input } from '@angular/core';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss',
  providers: [MessageService],
})
export class ToastComponent {
  @Input ('nameData') public name! : string;
  @Input ('descriptionData') public description! : string;
  @Input ('privatisationData') public visibilite! : string;
  @Input ('labelBouton') public label! : string;

  constructor(private messageService: MessageService) {}

  submit() {
   console.log("nom : ", this.name);
   console.log("description : ", this.description);
   console.log("prive : ", this.visibilite);



    this.messageService.add({
      severity: 'info',
      summary: 'Success',
      detail: 'Message Content',
    });
  }
}
