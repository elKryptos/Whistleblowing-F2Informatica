import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
})
export class OverviewComponent {
  constructor(private api: ApiService, private util: UtilService) {}

  items: any[] = [
    {
      label: 'Segnalazioni',
      icon: 'lucideFiles',
      color: '#742EFE',
      bg: '#F2EAFF',
    },
    {
      label: 'Completate',
      icon: 'lucideCheck',
      color: '#12b3a8',
      bg: '#e2f5f4',
    },
    {
      label: 'Tempo medio',
      icon: 'lucideLineChart',
      color: '#FE5152',
      bg: '#FFE8E0',
    },
    {
      label: 'Miglior recipient',
      icon: 'lucideUser',
      color: '#F0B708',
      bg: '#FFF4D2',
    },
  ];

  ngOnInit(): void {
    this.api.getTotalReports().subscribe((r) => {
      this.items[this.util.myFindIndex(this.items, 'Segnalazioni')].data = r;
    });

    this.api.getTotalCompletedReports().subscribe((r) => {
      this.items[this.util.myFindIndex(this.items, 'Completate')].data = r;
    });

    this.api.getAverageTime().subscribe((r) => {
      this.items[this.util.myFindIndex(this.items, 'Tempo medio')].data = r;
    });
  }
}
