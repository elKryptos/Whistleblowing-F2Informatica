import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent {
  constructor(public util: UtilService, private api: ApiService) {}

  items: any[] = [
    { label: 'Statistiche', icon: 'lucideLineChart' },
    { label: 'Segnalazioni', icon: 'lucideFileSpreadsheet' },
    { label: 'Utenti', icon: 'lucideUsers' },
  ];
  reportsTabs: string[] = ['Tutte', 'Aperte', 'Completate', 'Da assegnare'];

  alert: any = {};
  tabSelected: string = 'Statistiche';
  reportTabSelected: string = 'Tutte';
  modalVisible: string = '';
  reports: any[] = [];
  users: any[] = [];
  recipients: any[] = [];
  reportSelected: any = {};

  ngOnInit(): void {
    this.api.getAllReports().subscribe((r) => {
      this.reports = r;
      console.log('reports', this.reports);
    });

    this.api.getAllUsers().subscribe((res) => {
      this.users = res;
      this.recipients = this.users.filter((u) => u.ruolo == 'Recipient');
      console.log('users', this.users);
      console.log('recipients', this.recipients);
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
      case 'Da assegnare':
        this.api.getReportsToAssign().subscribe((r) => (this.reports = r));
        break;
      case 'Aperte':
        this.api.getReportsByStatus(false).subscribe((r) => (this.reports = r));
        break;
      case 'Completate':
        this.api.getReportsByStatus(true).subscribe((r) => (this.reports = r));
        break;
      default:
        this.api.getAllReports().subscribe((r) => {
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
