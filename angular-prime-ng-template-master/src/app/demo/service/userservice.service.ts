import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users } from '../api/user';
import { StorageService } from 'src/app/layout/service/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  model={
    username:'',
    password:''
  };
  drFlag:boolean=false;
  privilegeLists:String[]=[];
  token:string='';
  currentUser:any;
  auth_token:string='';
  constructor(private http:HttpClient,private storageservice : StorageService) { 


    this.getUserPrivileges().then(res=>
      {
        this.privilegeLists=res;
        console.log("checkkkkkkkkkkk")
        // console.log(res);
        console.log(this.privilegeLists)
      }
    )

  }
  


  getLoginUser(user:any)
  {
    return this.http.post('http://localhost:8080/v1/login_user', user).toPromise()
    .then(res=>res as Users[])
    

    // .then(data=>data);
  }

 
getLoginUser1(user: { username: string; password: string; })
  {
    return this.http.post<any>('http://localhost:8080/v1/login_user', user);
  }

  getAllUsers(token:any)
  {
    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/json',
    //   'Authorization': `Bearer ${token}`
    // })


// console.log(token);



// this.currentUser=this.storageservice.getUser();
// this.token=this.currentUser.token;
// console.log(this.token)
// const headers = new HttpHeaders({
//   'Content-Type': 'application/json',
//   'Authorization': 'Bearer ' + this.token,
// });

// const options = { withCredentials: true, headers: headers };
// this.auth_token=token;
// console.log(this.auth_token)
// // this.auth_token=this.usercompnent.getToken();
// const headers = new HttpHeaders({
//   'Content-Type': 'application/json',
//   'Authorization': `Bearer ${this.auth_token}`
// })
    return this.http.get<any>('http://localhost:8080/v1/users')
    .toPromise()
    .then(res => res as Users[])
    .then(data => data);
  }
 
  getUsers()
  {
    return this.http.get<any>('http://localhost:8080/v1/users');
  }
  getAddUser(user:Users)
  {
    return this.http.post<any>('http://localhost:8080/v1/users',user)
    .toPromise()
    .then(res => res as Users[])
    .then(data => data);


  }

  getUsersByOrg(token:any)
  {
    // const headers = new HttpHeaders({
    //   'Content-Type': 'application/json',
    //   'Authorization': `Bearer ${token}`
    // })


// console.log(token);
// this.auth_token=token;
// // this.auth_token=this.usercompnent.getToken();
// console.log("check loh token")
// console.log(this.auth_token);
// const headers = new HttpHeaders({
//   'Content-Type': 'application/json',
//   'Authorization': ` ${this.auth_token}`
// })
// console.log(headers)

    return this.http.get<any>('http://localhost:8080/v1/org_users')
    .toPromise()
    .then(res => res as Users[])
    .then(data => data);
  }
  

  getEditUser( id:any, user:any)
  {
    return this.http.put<any>('http://localhost:8080/v1/users/'+id,user)
    .toPromise()
    .then(res => res as Users[])
    .then(data => data);

  }

  getDeleteUser(id ?:String)
  {
    return this.http.delete<any>('http://localhost:8080/v1/users/'+id)
    .toPromise()
    .then(res => res )
    .then(data => data);
   

  }
  getLogoutUser()
  {
    return this.http.post<any>('http://localhost:8080/v1/logout_user',{})
    .toPromise()
    .then(res => res )
    .then(data => data);

  }
  

  getUserPrivileges()
  {
    return this.http.get<any>('http://localhost:8080/v1/user_privilege')
    .toPromise()
    .then(res => res )
    .then(data => data);
  }

  getChangePassword(Password:any)
  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<any>('http://localhost:8080/v1/users_password', Password )
    .toPromise()
    .then(res => res  as Users[])
    .then(data => data);
  }



   
    isAccessDecoy():boolean
    {
        
        
    
    
    console.log(" access Decoy");
    
    console.log(this.storageservice.isLoggedIn());
    
    if(this.storageservice.isLoggedIn())
      {
        console.log("login check")
        this.privilegeLists.forEach(res=>
          {
            console.log(res)
            if(res==='ACCESS_DECOY')
              {
                this.drFlag=true;
              }
              // else if(res===''){
              //   this.duFlag=false;
              // }
              // else{
              //   this.duFlag=false;
              // }
          }
        )
      
      }
      else
      {
        this.drFlag=false;
      }
      
      console.log("check decoy")
    console.log(this.drFlag);
     
      return this.drFlag;
    }

}
