import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http: HttpClient;
  url: string = 'http://localhost:8080/';

  constructor(private handler: HttpBackend) {
    this.http = new HttpClient(this.handler);
  }

  signIn(u: any): Observable<string> {
    return this.http.post(this.url + 'login', u, { responseType: 'text' });
  }
}
