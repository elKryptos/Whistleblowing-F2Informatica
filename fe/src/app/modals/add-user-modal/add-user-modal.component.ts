import { Component, EventEmitter, HostListener, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-add-user-modal',
  templateUrl: './add-user-modal.component.html',
  styleUrls: ['./add-user-modal.component.css'],
})
export class AddUserModalComponent {
  constructor(private util: UtilService, private api: ApiService) {}

  inputs: any[] = [
    { label: 'Nome', type: 'text', field: 'nome' },
    { label: 'Cognome', type: 'text', field: 'cognome' },
    { label: 'Email', type: 'email', field: 'email' },
    { label: 'Password', type: 'password', field: 'password' },
  ];

  options: string[] = ['Recipient', 'Admin'];

  alert: any = {};
  data: any = {};

  @Output() cancel = new EventEmitter<string>();

  signUp(): void {
    if (
      this.data.nome &&
      this.data.cognome &&
      this.data.email &&
      this.data.password &&
      this.data.ruolo
    ) {
      if (this.util.isEmailValid(this.data.email)) {
        if (this.util.isPasswordValid(this.data.password)) {
          this.api.signUp(this.data).subscribe({
            next: (res) => this.cancel.emit(''),
            error: (e) => console.log(e),
          });
        } else {
          // alert psw not valid
        }
      } else {
        // alert email not valid
      }
    } else {
      // alert fields
    }
  }

  // handler per i click fuori dal modal
  // private wasInside = true;

  // @HostListener('document:click')
  // clickout() {
  //   if (!this.wasInside) {
  //     this.cancel.emit('');
  //   }
  //   this.wasInside = false;
  // }
}
