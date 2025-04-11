import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-report-view-modal',
  templateUrl: './report-view-modal.component.html',
  styleUrls: ['./report-view-modal.component.css'],
})
export class ReportViewModalComponent {
  constructor(private api: ApiService) {}

  @Output() cancel = new EventEmitter<string>();
  @Output() refresh = new EventEmitter<boolean>();
  @Input() report: any;

  closeReport(): void {
    this.api.closeReport(this.report.codiceSegnalazione).subscribe({
      next: () => {
        this.refresh.emit(true);
        this.cancel.emit('');
      },
      error: (e) => {
        console.log(e);
      },
    });
  }
}
