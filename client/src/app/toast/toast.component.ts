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
  @Input ('privatisationData') public visibilite! : boolean;
  @Input ('labelBouton') public label! : string;
  @Input ('createOrga')  submitFunction : Function | undefined;
  // @Input() submitFunction: (orga_name:string, orga_is_public:boolean, orga_description:string) => void;

  constructor(private messageService: MessageService) {}

  submit() {
   console.log("nom : ", this.name);
   console.log("description : ", this.description);
   console.log("prive : ", this.visibilite);

   if (this.submitFunction){
    this.submitFunction(this.name,this.visibilite,this.description)
   }
   


    this.messageService.add({
      severity: 'info',
      summary: 'Success',
      detail: 'Message Content',
    });
  }

  
}
