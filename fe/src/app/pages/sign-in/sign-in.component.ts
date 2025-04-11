import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
})
export class SignInComponent {
  constructor(
    public util: UtilService,
    private auth: AuthService,
    private s: ApiService,
    private router: Router
  ) {}

  inputs: any[] = [
    { label: 'Email', type: 'email', field: 'email' },
    { label: 'Password', type: 'password', field: 'password' },
  ];

  alert: any = {};
  creds: any = {};

  signIn(): void {
    if (this.creds.email && this.creds.password) {
      this.auth.signIn(this.creds).subscribe({
        next: (res) => {
          let token = JSON.parse(res).msg;
          sessionStorage.setItem('token', token);

          let claims = JSON.parse(window.atob(token.split('.')[1]));
          if (claims.ruolo == 'Admin')
            this.router.navigateByUrl('dashboard/admin');
          if (claims.ruolo == 'Recipient')
            this.router.navigateByUrl('dashboard/recipient');
        },
        error: (e) =>
          (this.alert = this.util.showAlert(JSON.parse(e.error).msg, true)),
      });
    } else {
      this.alert = this.util.showAlert('Compila tutti i campi', true);
    }
  }
}
