import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import { MenuItem, MessageService } from 'primeng/api';
import { StorageService } from './service/storage.service';
import { UserserviceService } from '../demo/service/userservice.service';
import { Router } from '@angular/router';
import { UstorageService } from './service/ustorage.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
     providers: [MessageService]
})
export class AppMenuComponent implements OnInit {

    model: MenuItem[] = [];
    flag:boolean=false;
    drFlag:boolean=false;
    privilegeLists:String[]=[]
    checkFlag:boolean=false;

    constructor(public layoutService: LayoutService,private storageservice : StorageService,public userservice:UserserviceService,private messageService: MessageService,
        private ustorageservice:UstorageService,  private router : Router,
    ) { 
        
        // this.isAccessDecoy();



    }

     ngOnInit() {

        // this.userservice.getUserPrivileges().then(res=>
        //     {
        //       this.privilegeLists=res;
        //       console.log("checkkkkkkkkkkk")
        //       // console.log(res);
        //       console.log(this.privilegeLists)
        //     }
        //   )

        
    // this.isAccessDecoy();



    this.privilegeLists=this.userservice.privilegeLists;
    
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
      
console.log("flag check")
console.log(this.drFlag);
    






        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }
                ]
            },
            // {
            //     label: 'UI Components',
            //     items: [
            //         { label: 'Form Layout', icon: 'pi pi-fw pi-id-card', routerLink: ['/uikit/formlayout'] },
            //         { label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/uikit/input'] },
            //         { label: 'Float Label', icon: 'pi pi-fw pi-bookmark', routerLink: ['/uikit/floatlabel'] },
            //         { label: 'Invalid State', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/uikit/invalidstate'] },
            //         { label: 'Button', icon: 'pi pi-fw pi-mobile', routerLink: ['/uikit/button'], class: 'rotated-icon' },
            //         { label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table'] },
            //         { label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list'] },
            //         { label: 'Tree', icon: 'pi pi-fw pi-share-alt', routerLink: ['/uikit/tree'] },
            //         { label: 'Panel', icon: 'pi pi-fw pi-tablet', routerLink: ['/uikit/panel'] },
            //         { label: 'Overlay', icon: 'pi pi-fw pi-clone', routerLink: ['/uikit/overlay'] },
            //         { label: 'Media', icon: 'pi pi-fw pi-image', routerLink: ['/uikit/media'] },
            //         { label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/uikit/menu'], preventExact: true },
            //         { label: 'Message', icon: 'pi pi-fw pi-comment', routerLink: ['/uikit/message'] },
            //         { label: 'File', icon: 'pi pi-fw pi-file', routerLink: ['/uikit/file'] },
            //         { label: 'Chart', icon: 'pi pi-fw pi-chart-bar', routerLink: ['/uikit/charts'] },
            //         { label: 'Misc', icon: 'pi pi-fw pi-circle', routerLink: ['/uikit/misc'] },
            //         { label: 'MyDashBoard', icon: 'pi pi-fw pi-id-card', routerLink: ['/mydashboard'] }
            //     ]
            // },
            // {
            //     label: 'Prime Blocks',
            //     items: [
            //         { label: 'Free Blocks', icon: 'pi pi-fw pi-eye', routerLink: ['/blocks'], badge: 'NEW' },
            //         { label: 'All Blocks', icon: 'pi pi-fw pi-globe', url: ['https://www.primefaces.org/primeblocks-ng'], target: '_blank' },
            //     ]
            // },
            // {
            //     label: 'Utilities',
            //     items: [
            //         { label: 'PrimeIcons', icon: 'pi pi-fw pi-prime', routerLink: ['/utilities/icons'] },
            //         { label: 'PrimeFlex', icon: 'pi pi-fw pi-desktop', url: ['https://www.primefaces.org/primeflex/'], target: '_blank' },
            //     ]
            // },
            
            {
                label: 'Pages',
                icon: 'pi pi-fw pi-briefcase',
                routerLink: ['/pages'],
                items: [
                    // {
                    //     label: 'Landing',
                    //     icon: 'pi pi-fw pi-globe',
                    //     routerLink: ['/landing']
                    // },
                    {
                        label: 'User Management',
                        icon: 'pi pi-fw pi-globe',
                        routerLink: ['/usermanagent']
                    },
                    {
                        label: 'Auth',
                        icon: 'pi pi-fw pi-user',
                        items: [
                            {
                                label: 'Login',
                                icon: 'pi pi-fw pi-sign-in',
                                // disabled:  this.isAuthenticate(),
                                visible: !this.isAuthenticate(),
                                routerLink: ['/auth/login']
                            },
                            {
                                label: 'Logout',
                                icon: 'pi pi-fw pi-times-circle',
                                // disabled: this.isAuthenticate(),
                                visible: this.isAuthenticate(),
                               
                                routerLink: ['/auth/logout']
                            },
                            // {
                            //     label: 'Access Denied',
                            //     icon: 'pi pi-fw pi-lock',
                            //     routerLink: ['/auth/access']
                            // }
                        ]
                    },
                    {
                        label: 'Template',
                        icon: 'pi pi-fw pi-pencil',
                        visible: this.isAuthenticate(),
                        routerLink: ['/pages/template']
                        
                    },
                    // {
                    //     label: 'Template',
                    //     icon: 'pi pi-fw pi-calendar',
                    //     routerLink: ['/pages/timeline']
                    // },
                    // {
                    //     label: 'Not Found',
                    //     icon: 'pi pi-fw pi-exclamation-circle',
                    //     routerLink: ['/pages/notfound']
                    // },
                    // {
                    //     label: 'Empty',
                    //     icon: 'pi pi-fw pi-circle-off',
                    //     routerLink: ['/pages/empty']
                    // },
                ]
            },
            // {
            //     label: 'Hierarchy',
            //     items: [
            //         {
            //             label: 'Submenu 1', icon: 'pi pi-fw pi-bookmark',
            //             items: [
            //                 {
            //                     label: 'Submenu 1.1', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 1.1.1', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 1.1.2', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 1.1.3', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 1.2', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 1.2.1', icon: 'pi pi-fw pi-bookmark' }
            //                     ]
            //                 },
            //             ]
            //         },
            //         {
            //             label: 'Submenu 2', icon: 'pi pi-fw pi-bookmark',
            //             items: [
            //                 {
            //                     label: 'Submenu 2.1', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 2.1.1', icon: 'pi pi-fw pi-bookmark' },
            //                         { label: 'Submenu 2.1.2', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 2.2', icon: 'pi pi-fw pi-bookmark',
            //                     items: [
            //                         { label: 'Submenu 2.2.1', icon: 'pi pi-fw pi-bookmark' },
            //                     ]
            //                 },
            //             ]
            //         }
            //     ]
            // },
            // {
            //     label: 'Get Started',
            //     items: [
            //         {
            //             label: 'Documentation', icon: 'pi pi-fw pi-question', routerLink: ['/documentation']
            //         },
            //         {
            //             label: 'View Source', icon: 'pi pi-fw pi-search', url: ['https://github.com/primefaces/sakai-ng'], target: '_blank'
            //         }
            //     ]
            // }
        ];
    }

    isAuthenticate(): boolean
    {

if(this.storageservice.isLoggedIn())
    {
        this.flag= true;
    }
    else{
        this.flag=false;
    }

    console.log("IsAuthenticated")
    console.log(this.flag);
    return this.flag;


    }


isAccess():boolean
{
    if(this.drFlag==true)
        return true;
    else
    return false;
}

    
isLogoutUser():boolean
{
  this.storageservice.clean();
  this.ustorageservice.clearUser();
  this.userservice.getLogoutUser().then(data=>
    {
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'You are Log out', life: 3000 });
        //    this.router.navigate(['']); 
        if(data)
            {
                this.checkFlag=true;
            }
            else
            {
                this.checkFlag=false;
            }
        
    }
  )
  return this.checkFlag;
}



    // isAccessDecoy():boolean
    // {
        
    //     // this.userservice.getUserPrivileges().then(res=>
    //     //     {
    //     //       this.privilegeLists=res;
    //     //       console.log("checkkkkkkkkkkk")
    //     //       // console.log(res);
    //     //       console.log(this.privilegeLists)
    //     //     }
    //     //   )
    
    //     this.privilegeLists=this.userservice.privilegeLists;
    
    // console.log(" access Decoy");
    
    // console.log(this.storageservice.isLoggedIn());
    
    // if(this.storageservice.isLoggedIn())
    //   {
    //     console.log("login check")
    //     this.privilegeLists.forEach(res=>
    //       {
    //         console.log(res)
    //         if(res==='ACCESS_DECOY')
    //           {
    //             this.drFlag=true;
    //           }
    //           // else if(res===''){
    //           //   this.duFlag=false;
    //           // }
    //           // else{
    //           //   this.duFlag=false;
    //           // }
    //       }
    //     )
      
    //   }
    //   else
    //   {
    //     this.drFlag=false;
    //   }
      
    //   console.log("check decoy")
    // console.log(this.drFlag);
     
    //   return this.drFlag;
    // }
    


    // async isAccessDecoy(): Promise<boolean> {
    //     try {
    //         // const res = await this.userservice.getUserPrivileges();
    //         this.privilegeLists = res;
    //         console.log("checkkkkkkkkkkk");
    //         console.log(this.privilegeLists);
            
    //         if(this.storageservice.isLoggedIn())
    //             {
    //               console.log("login check")
    //               this.privilegeLists.forEach(res=>
    //                 {
    //                   console.log(res)
    //                   if(res==='ACCESS_DECOY')
    //                     {
    //                       this.drFlag=true;
    //                     }
    //                     // else if(res===''){
    //                     //   this.duFlag=false;
    //                     // }
    //                     // else{
    //                     //   this.duFlag=false;
    //                     // }
    //                 }
    //               )
                
    //             }
    //             else
    //             {
    //               this.drFlag=false;
    //             }
                
    //             console.log("check decoy")
    //           console.log(this.drFlag);
               
    //             // return this.drFlag;
            
    //         // Rest of your code follows...
    //     } catch (error) {
    //         console.error("Error getting user privileges:", error);
    //         // Handle error appropriately
    //     }
    //     console.log("decoy retur type")
    //     console.log(this.drFlag)
    //     return this.drFlag;
    // }
   
}
