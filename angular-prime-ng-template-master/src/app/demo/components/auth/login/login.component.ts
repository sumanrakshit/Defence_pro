import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Users } from 'src/app/demo/api/user';
import { UserserviceService } from 'src/app/demo/service/userservice.service';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { StorageService } from 'src/app/layout/service/storage.service';
import { UstorageService } from 'src/app/layout/service/ustorage.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .p-password input {
            width: 100%;
            padding:1rem;
        }

        :host ::ng-deep .pi-eye{
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }

        :host ::ng-deep .pi-eye-slash{
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `]
})
export class LoginComponent implements OnInit{

    valCheck: string[] = ['remember'];
    users1: Users[] = [];


    password!: string;
    id:number=0;
  isLoggedin=false;
  isSuccess=false;
  

  model={
    username:'',
    password:''
  };
  data:boolean=false;

    constructor(private router : Router, private storageservice : StorageService, public userservice:UserserviceService, private ustorageservice:UstorageService) { }

    ngOnInit(): void {
        if(this.storageservice.isLoggedIn())
        {
          this.isLoggedin=true;
    
        }
        // this.loginUser();
        // console.log("checkkkk");
    
      }

      loginUser()
      {
       

        console.log(this.model);
        this.userservice.getLoginUser1(this.model).subscribe((res:Users)=>
          
        {

          console.log("checkkkkk");
             
            
                if(res.is_FirstLogin == true)
                  {
                    this.storageservice.saveUser(res);
                    this.isLoggedin=true;
                    this.router.navigate(['/auth/password']);
                  }
                  
                  else{
                    this.storageservice.saveUser(res.token);
                    this.ustorageservice.setUser(res);
                    this.isLoggedin=true;
                    this.router.navigate(['']);
                  }

              
    
        },
        (error) => {
          console.error("Error:", error);
          if (error.status === 401) {
            this.router.navigate(['/auth/error']);
          } 
        }

      
      );
      }

      userLogin()
      {
        this.userservice.getLoginUser(this.model).then(users=>
            {
             
              
              // console.log(users)
                this.users1=users;
                console.log(this.users1)
                
                 
               // }
              // else{
              //   this.router.navigate(['/auth/access']);
              // }
        //   this.id=res.id;
    
        //   if(res.isFirstLogin==true)
        //   {
        //     this.router.navigate(['/changepassword/{res.id}' ]);
        //   }
        //   else{
        //     this.storageservice.saveUser(res);
        //     this.isLoggedin=true;
               
    
        //     // this.redirectBasedOnRole(res.privelege);
        //   }
        // console.log(res);
        // this.router.navigate(['']);
                
                
            }

            

        )
       
       
      }
}
