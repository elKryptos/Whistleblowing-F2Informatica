import { Component } from '@angular/core';
import { ApiNoIntService } from 'src/app/services/api-no-int.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css'],
})
export class ReportComponent {
  constructor(private api: ApiNoIntService, public util: UtilService) {}

  codeGenerated!: string;
  reportedYet: boolean = false;

  leftInputs: any[] = [
    { label: 'Titolo', type: 'text', field: 'titolo', required: true },
    {
      label: 'Dove è accaduto',
      type: 'text',
      field: 'dove',
      required: true,
    },
    {
      label: 'Quando è accaduto',
      type: 'text',
      field: 'quando',
      required: true,
    },
  ];

  rightInputs: any[] = [
    {
      label: "Descrivi l'accaduto nel dettaglio",
      field: 'descrizione',
      required: true,
    },
    {
      label: "Qual è l'outcome che vuoi ottenere con il nostro supporto",
      field: 'scopo',
      required: true,
    },
  ];

  options: string[] = [
    'Sono una vittima',
    "E' una voce che ho sentito",
    'Sono coinvolto nella faccenda',
    'Sono un testimone',
    'Mi è stato confidato da un testimone',
  ];

  data: any = {};
  alert: any = {};

  createReport(): void {
    if (
      this.data.titolo &&
      this.data.dove &&
      this.data.quando &&
      this.data.descrizione &&
      this.data.scopo &&
      this.data.coinvolgimento &&
      this.data.segnalazionePrecedente &&
      this.data.personeInformate
    ) {
      this.api.createReport(this.data).subscribe({
        next: (r) => {
          this.codeGenerated = JSON.parse(r).msg;
        },
        error: (e) => {
          this.alert = this.util.showAlert(JSON.parse(e.error).msg, true);
        },
      });
    } else {
      this.alert = this.util.showAlert('Compila tutti i campi', true);
    }
  }
}
