import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-status-reports',
  templateUrl: './status-reports.component.html',
  styleUrls: ['./status-reports.component.css'],
})
export class StatusReportsComponent {
  constructor(private api: ApiService, private util: UtilService) {}

  statusItems: any[] = [
    { label: 'Urgenze', bg: '#E72929' },
    { label: 'In corso', bg: '#0036c0' },
    { label: 'Completate', bg: '#12b3a8' },
  ];

  ngOnInit(): void {
    this.api.getTotalCompletedReports().subscribe((r) => {
      this.statusItems[
        this.util.myFindIndex(this.statusItems, 'Completate')
      ].data = r;
    });

    this.api.getUrgentReports().subscribe((r) => {
      this.statusItems[
        this.util.myFindIndex(this.statusItems, 'Urgenze')
      ].data = r;
    });

    this.api.getOpenReports().subscribe((r) => {
      this.statusItems[
        this.util.myFindIndex(this.statusItems, 'In corso')
      ].data = r;
    });
  }
}
