import { Component, Input } from '@angular/core';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-answers-summary',
  templateUrl: './answers-summary.component.html',
  styleUrls: ['./answers-summary.component.css'],
})
export class AnswersSummaryComponent {
  constructor(private util: UtilService) {}

  @Input() report: any;
  summaryOpen: boolean = true;

  rows: any[] = [
    [
      { label: 'Titolo', field: 'titolo', val: 0 },
      { label: 'Dove è accaduto', field: 'dove', val: 4 },
    ],
    [
      { label: 'Quando è accaduto', field: 'quando', val: 0 },
      {
        label: "Come sei coinvolto nell'accaduto?",
        field: 'coinvolgimento',
        val: 4,
      },
    ],
    [
      {
        label:
          'Hai riportato i fatti accaduti ad altre organizzazioni e/o invividui?',
        field: 'segnalazionePrecedente',
        val: 0,
      },
      {
        label:
          "Cordialmente, elenca le organizzazioni e/o gli indivudui a cui hai riportato l'accaduto",
        field: 'personeInformate',
        val: 4,
      },
    ],
    [
      {
        label: "Descrivi l'accaduto nel dettaglio",
        field: 'descrizione',
        val: 0,
      },
      {
        label: "Qual è l'outcome che vuoi ottenere con il nostro supporto",
        field: 'scopo',
        val: 4,
      },
    ],
  ];

  // qsLeft: any[] = [
  //   { label: 'Titolo', field: 'titolo' },
  //   { label: 'Dove è accaduto', field: 'dove' },
  //   { label: 'Quando è accaduto', field: 'quando' },
  //   { label: "Come sei coinvolto nell'accaduto?", field: 'coinvolgimento' },
  // ];

  // qsRight: any[] = [
  //   {
  //     label:
  //       'Hai riportato i fatti accaduti ad altre organizzazioni e/o invividui?',
  //     field: 'segnalazionePrecedente',
  //   },
  //   {
  //     label:
  //       "Cordialmente, elenca le organizzazioni e/o gli indivudui a cui hai riportato l'accaduto",
  //     field: 'personeInformate',
  //   },
  //   { label: "Descrivi l'accaduto nel dettaglio", field: 'descrizione' },
  //   {
  //     label: "Qual è l'outcome che vuoi ottenere con il nostro supporto",
  //     field: 'scopo',
  //   },
  // ];
}
