import { Injectable } from '@angular/core';
import { Users } from 'src/app/demo/api/user';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UstorageService {

 private  currentUser:any;

  constructor() { }





  setUser(user: any): void {
    this.currentUser = JSON.stringify(user);
  }

  getUser(): any {
    return JSON.parse(this.currentUser);
  }

  clearUser(): void {
    this.currentUser = null;
  }


}
