import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiNoIntService {
  private http: HttpClient;
  url: string = 'http://localhost:8080/';

  constructor(private handler: HttpBackend) {
    this.http = new HttpClient(this.handler);
  }

  createReport(r: any): Observable<string> {
    return this.http.post(this.url + 'creaSegnalazione', r, {
      responseType: 'text',
    });
  }

  getReportByCode(c: string): Observable<any> {
    return this.http.get<any>(this.url + 'infoSegnalazione/' + c);
  }

  sendNotificationMail(): Observable<string> {
    return this.http.post(this.url + 'sendMail', '', { responseType: 'text' });
  }

  createComment(c: any): Observable<any> {
    return this.http.post<any>(this.url + 'commento/' + c.reportId, c);
  }
}
