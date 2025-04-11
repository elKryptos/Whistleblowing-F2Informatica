import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-report-assignment',
  templateUrl: './report-assignment.component.html',
  styleUrls: ['./report-assignment.component.css'],
})
export class ReportAssignmentComponent {
  constructor(private api: ApiService, public util: UtilService) {}

  levels: any[] = [
    { label: 'Bassa', color: '#272727', bg: '#ebebeb' },
    { label: 'Moderata', color: '#F0B708', bg: '#FFF4D2' },
    { label: 'Urgente', color: '#8b1313', bg: '#ffc9c9' },
  ];

  @Output() cancel = new EventEmitter<string>();
  @Output() refresh = new EventEmitter<boolean>();
  @Input() recipients: any[] = [];
  @Input() report: any;
  recipient: any = {};
  priority!: string;

  reportAssignment(): void {
    this.api
      .assignment(this.report.codiceSegnalazione, this.recipient.id)
      .subscribe({
        next: () => {
          this.report.utente = this.recipient;
          this.refresh.emit(true);
        },
        error: (e) => console.log(e),
      });
  }

  prioriryAssignment(): void {
    if (this.priority) {
      this.api
        .setPriority(this.report.codiceSegnalazione, this.priority)
        .subscribe((r) => {
          this.report = r;
          this.refresh.emit(true);
        });
    }
  }
}
