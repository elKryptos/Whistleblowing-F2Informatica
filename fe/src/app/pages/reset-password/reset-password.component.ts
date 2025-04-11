import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent {
  constructor(private util: UtilService, private route: ActivatedRoute) {}

  alert: any = {};
  email!: string;
  id!: string;
  newCreds: any = {};

  inputs: any[] = [
    { label: 'Codice di verifica', type: 'text', field: 'code' },
    { label: 'Nuova password', type: 'password', field: 'password' },
    { label: 'Conferma nuova password', type: 'password', field: 'cPassword' },
  ];

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  }

  resetPassword(): void {
    if (this.email) {
      if (this.util.isEmailValid(this.email)) {
        console.log('email', this.email);
        // inviare la mail e gettare il codice per la verifica
      } else {
        this.alert = this.util.showAlert('Email non valida', true);
      }
    } else {
      this.alert = this.util.showAlert('Inserisci un indirizzo email', true);
    }
  }

  setNewPassword(): void {
    if (
      this.newCreds.code &&
      this.newCreds.password &&
      this.newCreds.cPassword
    ) {
      if (this.util.isPasswordValid(this.newCreds.password)) {
        if (this.newCreds.password == this.newCreds.cPassword) {
          // check il code se è quello mandato per mail
          console.log('newCreds', this.newCreds);
        } else {
          this.alert = this.util.showAlert(
            'Le password devono combaciare',
            true
          );
        }
      } else {
        this.alert = this.util.showAlert('Password non valida', true);
      }
    } else {
      this.alert = this.util.showAlert('Compila tutti i campi', true);
    }
  }
}
