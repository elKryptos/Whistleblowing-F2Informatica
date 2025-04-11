import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { inject } from '@angular/core';
import { ApiService } from '../services/api.service';

export class AdminGuard {}

export const adminGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): Promise<boolean> => {
  let r = inject(Router);
  let s = inject(ApiService);
  let token = sessionStorage.getItem('token');

  return new Promise<boolean>((res, rej) => {
    if (token) {
      let claims = JSON.parse(window.atob(token.split('.')[1]));
      s.verifyToken().subscribe({
        next: () => {
          if (claims.ruolo == 'Admin') {
            res(true);
          } else {
            r.navigateByUrl('signIn');
            rej(false);
          }
        },
        error: () => {
          r.navigateByUrl('signIn');
          rej(false);
        },
      });
    } else {
      r.navigateByUrl('signIn');
      rej(false);
    }
  });
};
