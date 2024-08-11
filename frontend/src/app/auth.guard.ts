import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const isAuthenticated = authService.hasToken();
  if (isAuthenticated) {
    console.log('safe');
    return true;
  } else {
    router.navigate(['/login']); // Redirect to login if not authenticated
    return false;
  }
};
