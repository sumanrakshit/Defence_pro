import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanDeactivate, CanLoad, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsermanageComponent } from 'src/app/demo/components/usermanage/usermanage.component';
import { StorageService } from '../service/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate , CanActivateChild,CanDeactivate<UsermanageComponent>, CanLoad {


  constructor(private storageservice : StorageService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.checkAuth();
  }


  // canActivate(): boolean {
  //   return this.checkAuth();
  // }

  canActivateChild(): boolean {
    return this.checkAuth();
  }

  canDeactivate(component: UsermanageComponent): boolean {
    // if (component.l) {
    //   return window.confirm('You have unsaved changes. Do you really want to leave?');
    // }
    return true;
  }

  canLoad(): boolean {
    return this.checkAuth();
  }

  private checkAuth(): boolean {
    if (this.storageservice.isLoggedIn()) {
      return true;
    } else {
      // Redirect to the login page if the user is not authenticated
      this.router.navigate(['/auth/login']);
      return false;
    }
  }
  
}
