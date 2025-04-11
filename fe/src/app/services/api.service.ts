import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  url: string = 'http://localhost:8080/';

  verifyToken(): Observable<string> {
    return this.http.get<string>(this.url + 'private');
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'registrazione');
  }

  getAllReports(): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'segnalazione');
  }

  getAllRecipientReports(email: string): Observable<any[]> {
    return this.http.post<any[]>(this.url + 'listSegnalazioniRecipient', email);
  }

  getReportsByStatus(s: boolean): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'segnFiltrate/' + s);
  }

  getRecipientReportsByStatus(s: boolean, email: string): Observable<any[]> {
    return this.http.post<any[]>(
      this.url + 'segnFiltrateRecipient/' + s,
      email
    );
  }

  getReportsToAssign(): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'segnFiltrate');
  }

  assignment(code: string, id: number): Observable<string> {
    return this.http.post(this.url + 'assegnazioneSegnalazione/' + code, id, {
      responseType: 'text',
    });
  }

  closeReport(code: string): Observable<string> {
    return this.http.post(this.url + 'chiusuraSegnalazione', code, {
      responseType: 'text',
    });
  }

  getTotalReports(): Observable<number> {
    return this.http.get<number>(this.url + 'totaleSegnalazioni');
  }

  getTotalCompletedReports(): Observable<number> {
    return this.http.get<number>(this.url + 'segnalazioniChiuse');
  }

  getUrgentReports(): Observable<number> {
    return this.http.get<number>(this.url + 'urgenze');
  }

  getAverageTime(): Observable<number> {
    return this.http.get<number>(this.url + 'mediaChiusura');
  }

  getOpenReports(): Observable<number> {
    return this.http.get<number>(this.url + 'segnalazioniAperte');
  }

  setPriority(code: string, level: string): Observable<any> {
    return this.http.post<any>(this.url + 'priorita/' + code, {
      priorita: level,
    });
  }

  signUp(u: any): Observable<string> {
    return this.http.post(this.url + 'registrazione', u, {
      responseType: 'text',
    });
  }
}
