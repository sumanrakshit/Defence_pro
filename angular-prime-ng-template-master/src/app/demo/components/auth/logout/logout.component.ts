import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { UserserviceService } from 'src/app/demo/service/userservice.service';
import { StorageService } from 'src/app/layout/service/storage.service';
import { UstorageService } from 'src/app/layout/service/ustorage.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss'],
  providers: [MessageService]
})
export class LogoutComponent implements OnInit {

  constructor(private ustorageservice:UstorageService,private storageservice : StorageService,public userservice:UserserviceService,  private router : Router,private messageService: MessageService) { }

  ngOnInit(): void {
    this.storageservice.clean();
    this.ustorageservice.clearUser();
    this.userservice.getLogoutUser().then(data=>
      {
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'You are Log out', life: 3000 });
            //  this.router.navigate(['']); 
      }
    )
    
  }


  logoutUser()
  {
    this.storageservice.clean();
    this.ustorageservice.clearUser();
    this.userservice.getLogoutUser().then(data=>
      {
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'You are Log out', life: 3000 });
             this.router.navigate(['']); 
      }
    )
  }

}
