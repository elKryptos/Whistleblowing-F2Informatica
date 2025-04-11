import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-reports-tabs',
  templateUrl: './reports-tabs.component.html',
  styleUrls: ['./reports-tabs.component.css'],
})
export class ReportsTabsComponent {
  @Input() tabs: string[] = [];
  @Output() e = new EventEmitter<string>();
  @Input() tabSelected!: string;
}
