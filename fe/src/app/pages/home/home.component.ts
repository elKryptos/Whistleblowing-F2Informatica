import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(public util: UtilService, public router: Router) {}

  code!: string;
  alert: any = {};

  findReport(): void {
    if (this.code) {
      this.router.navigateByUrl('report/' + this.code);
    } else {
      this.alert = this.util.showAlert('Inserisci un codice', true);
    }
  }

  ngOnInit(): void {
    sessionStorage.clear();
  }
}
