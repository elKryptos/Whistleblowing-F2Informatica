import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiNoIntService } from 'src/app/services/api-no-int.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-user-report-view',
  templateUrl: './user-report-view.component.html',
  styleUrls: ['./user-report-view.component.css'],
})
export class UserReportViewComponent {
  constructor(
    private route: ActivatedRoute,
    private api: ApiNoIntService,
    public util: UtilService
  ) {}

  alert: any = {};
  code!: string;
  report: any = {};

  headers: string[] = [
    'Creata il',
    'Ultimo aggiornamento',
    'Scadenza',
    'Stato',
  ];

  ngOnInit(): void {
    this.code = this.route.snapshot.params['code'];

    if (this.code) {
      this.api.getReportByCode(this.code).subscribe({
        next: (r) => {
          this.report = r;
          console.log(this.report);
        },
        error: (e) => {
          this.alert = this.util.showAlert(JSON.parse(e.error).msg, true);
        },
      });
    }
  }
}
