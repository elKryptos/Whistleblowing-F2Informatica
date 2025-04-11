import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-reports-table',
  templateUrl: './reports-table.component.html',
  styleUrls: ['./reports-table.component.css'],
})
export class ReportsTableComponent {
  constructor(public util: UtilService) {}

  @Input() role!: string;
  @Output() e = new EventEmitter<string>();
  @Output() rs = new EventEmitter<any>();
  @Input() reports: any[] = [];

  openModal(r: any): void {
    if (this.role == 'admin') {
      this.e.emit('assignment');
      this.rs.emit(r);
    } else {
      this.e.emit('reportView');
      this.rs.emit(r);
    }
  }
}
