import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-dashboard-header',
  templateUrl: './dashboard-header.component.html',
  styleUrls: ['./dashboard-header.component.css'],
})
export class DashboardHeaderComponent {
  name!: string;

  ngOnInit(): void {
    let token = sessionStorage.getItem('token');

    if (token) {
      let claims = JSON.parse(window.atob(token.split('.')[1]));
      this.name = claims.nome;
    }
  }
}
