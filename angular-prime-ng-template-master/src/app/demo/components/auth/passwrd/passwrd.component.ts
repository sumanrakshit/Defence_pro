import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Users } from 'src/app/demo/api/user';
import { UserserviceService } from 'src/app/demo/service/userservice.service';
import { StorageService } from 'src/app/layout/service/storage.service';

@Component({
  selector: 'app-passwrd',
  templateUrl: './passwrd.component.html',
  styleUrls: ['./passwrd.component.scss'],
  providers: [MessageService]
})
export class PasswrdComponent implements OnInit {

  valCheck: string[] = ['remember'];
    users1: Users[] = [];


    // password!: string;
    successMeassage:String='';


  model={
    password:''
  };

  constructor(public userservice:UserserviceService,private router : Router, private storageservice : StorageService,private messageService: MessageService) { }

  ngOnInit(): void {
  }





  changePassword()
  {
    this.userservice.getChangePassword(this.model).then(res=>
      {
        // this.successMeassage=JSON.parse(res);

        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Change Password Successful', life: 3000 });
        console.log("Password change successfully" + res)
      
        this.router.navigate(['/auth/login']);
      }
    )

  }
}
