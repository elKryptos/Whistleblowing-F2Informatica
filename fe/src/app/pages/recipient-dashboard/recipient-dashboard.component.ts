import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-recipient-dashboard',
  templateUrl: './recipient-dashboard.component.html',
  styleUrls: ['./recipient-dashboard.component.css'],
})
export class RecipientDashboardComponent {
  constructor(private api: ApiService) {}

  items: any[] = [
    { label: 'Statistiche', icon: 'lucideLineChart' },
    { label: 'Segnalazioni', icon: 'lucideFileSpreadsheet' },
  ];

  alert: any = {};
  tabSelected: string = 'Statistiche';
  modalVisible: string = '';
  reportsTabs: string[] = ['Tutte', 'Aperte', 'Completate'];
  reportTabSelected: string = 'Tutte';
  reports: any[] = [];
  reportSelected: any = {};
  user: any = {};

  ngOnInit(): void {
    let token = sessionStorage.getItem('token');

    if (token) {
      this.user = JSON.parse(window.atob(token.split('.')[1]));
    }

    this.api.getAllRecipientReports(this.user.email).subscribe((r) => {
      console.log(r);
      this.reports = r;
    });
  }

  onTab(e: any) {
    this.tabSelected = e;
    console.log('tab', this.tabSelected);
  }

  onReportTab(e: any) {
    this.reportTabSelected = e;
    console.log('reportTab', this.reportTabSelected);

    switch (this.reportTabSelected) {
      case 'Aperte':
        this.api
          .getRecipientReportsByStatus(false, this.user.email)
          .subscribe((r) => (this.reports = r));
        break;
      case 'Completate':
        this.api
          .getRecipientReportsByStatus(true, this.user.email)
          .subscribe((r) => (this.reports = r));
        break;
      default:
        this.api.getAllRecipientReports(this.user.email).subscribe((r) => {
          this.reports = r;
        });
    }
  }

  onOpen(e: any): void {
    this.modalVisible = e;
  }

  onClose(e: any): void {
    this.modalVisible = e;
  }
}
