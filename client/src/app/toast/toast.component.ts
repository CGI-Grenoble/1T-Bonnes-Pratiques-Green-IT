import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss',
  providers: [MessageService],
})
export class ToastComponent {
  constructor(private messageService: MessageService) {}

  label! : string;
  visibilite! : string;

  show() {
    this.messageService.add({
      severity: 'info',
      summary: 'Success',
      detail: 'Message Content',
    });
  }
}
