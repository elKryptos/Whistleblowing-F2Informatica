import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class UtilService {
  constructor(private router: Router) {}

  showAlert(msg: string, err?: boolean): any {
    let obj = {
      err: err,
      message: msg,
      visible: true,
    };

    setTimeout(() => {
      obj.visible = false;
    }, 3000);

    return obj;
  }

  isEmailValid(email: string): boolean {
    const reg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return reg.test(email);
  }

  isPasswordValid(psw: string): boolean {
    const reg =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\^$*.\[\]{}\(\)?\-“!@#%&/,><\’:;|_~`])\S{8,99}$/;
    return reg.test(psw);
  }

  showPassword(i: any, a: boolean): void {
    if (a) {
      i.type = 'text';
    } else {
      i.type = 'password';
    }
  }

  redirect(path: string): void {
    this.router.navigateByUrl(path);
  }

  copyToClipboard(text: string): void {
    navigator.clipboard
      .writeText(text)
      .then((res) => {
        alert('Codice copiato con successo');
      })
      .catch((err) => alert(err));
  }

  dateToString(t: string): string {
    return new Date(t).toLocaleString('it-IT', {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    });
  }

  boolToString(b: boolean): string {
    return b ? 'Completata' : 'Aperta';
  }

  myFindIndex(arr: any[], val: string): number {
    return arr.findIndex((i) => i.label === val);
  }
}
