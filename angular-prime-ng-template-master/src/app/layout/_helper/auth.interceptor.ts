import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../service/storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  currentUser: any;
  token:any;

  constructor(private storageservice : StorageService) {}


  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    this.currentUser=this.storageservice.getUser();
    this.token=this.currentUser.token;


    console.log("intercept")
    console.log(this.token);

    if(this. currentUser && this.token)
      {
        const cloned = request.clone({
          withCredentials: true,
          setHeaders: {
            'Content-Type': 'application/json', 
            'Authorization': `${this.token}`,   
            'Access-Control-Allow-Origin': 'true' 
          }
      });

      return next.handle(cloned);

      }
      else{
        return next.handle(request)
      }
    // request = request.clone({
    //   withCredentials: true,
    //   setHeaders: {
    //     Authorization: `Bearer ${this.currentUser.token}`
    //   },
    // });
    // return next.handle(request);
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];