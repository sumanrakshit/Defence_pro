import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Roles } from '../api/role';
import { Priviliges } from '../api/privilige';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http:HttpClient) { }



  getAllRoles()
  {
    return this.http.get<any>('http://localhost:8080/v1/roles')
    .toPromise()
    .then(res => res as Roles[])
    .then(data => data);
  }


  getUniqueRoles()
  {
    return this.http.get<any>('http://localhost:8080/v1/uni_roles')
    .toPromise()
    .then(res => res as Roles[])
    .then(data => data);
  }


  getAllPrivileges()
  {
    return this.http.get<any>('http://localhost:8080/v1/privileges')
    .toPromise()
    .then(res => res as Priviliges[])
    .then(data => data);
  }

  getAddRole(role:any)
  {
    return this.http.post<any>('http://localhost:8080/v1/roles',role)
    .toPromise()
    .then(res => res as Roles[])
    .then(data => data);
  }

  getEditRole(id:any,role:any)
  {
    return this.http.put<any>('http://localhost:8080/v1/roles/'+id,role)
    .toPromise()
    .then(res => res as Roles[])
    .then(data => data);
  }


  getDeleteRole(id ?:String)
  {
    return this.http.delete<any>('http://localhost:8080/v1/roles/'+id)
    .toPromise()
    .then(res => res )
    .then(data => data);

  }
}
