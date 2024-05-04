import { Component, OnInit } from '@angular/core';
import { MenuItem, MessageService, PrimeIcons, SelectItem } from 'primeng/api';
import { Product } from '../../api/product';
import { ProductService } from '../../service/product.service';
import { Table } from 'primeng/table';
import { Group, Users } from '../../api/user';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/layout/service/storage.service';
import { UserserviceService } from '../../service/userservice.service';
import { RoleService } from '../../service/role.service';
import { Roles } from '../../api/role';

import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormControl  } from "@angular/forms";
import { Groups } from '../../api/group';
import { Priviliges } from '../../api/privilige';
import { DataView } from 'primeng/dataview';
import { UstorageService } from 'src/app/layout/service/ustorage.service';

@Component({
  selector: 'app-usermanage',
  templateUrl: './usermanage.component.html',
  styleUrls: ['./usermanage.component.scss'],
  providers: [MessageService]
})
export class UsermanageComponent implements OnInit {
  items: MenuItem[] = [];

  cardMenu: MenuItem[] = [];
  sourceCities: any[] = [];

  targetCities: any[] = [];
  sortOptions: SelectItem[] = [];

    sortOrder: number = 0;

    sortField: string = '';


  productDialog: boolean = false;
  userDialog: boolean = false;
  userEditDialog:boolean=false;
  deleteUserDialog: boolean = false;
  deleteUsersDialog: boolean = false;
  roleDialog:boolean=false;
  deleteRoleDialog: boolean = false;
  deleteRolesDialog: boolean = false;

  deleteProductDialog: boolean = false;

  deleteProductsDialog: boolean = false;
  events1: any[] = [];

  events2: any[] = [];

  products: Product[] = [];

  product: Product = {};

  selectedProducts: Product[] = [];

  submitted: boolean = false;

  cols: any[] = [];

  statuses: SelectItem[] = [];
  cities: SelectItem[] = [];
  selectedDrop: SelectItem = { value: '' };

  rowsPerPageOptions = [5, 10, 20];

  users: Users[]=[];
  user: Users={};
  roles: Roles[]=[];
  role: Roles={};
  priviliges: Priviliges[]=[];
  privilige:Priviliges={};
  unirole : Roles[]=[];
  users1:any[]=[];
  organizations:any[]=[];
  selectedMulti: Groups[] = [];
//   selectRole:Users={role:''}

selectedUsers: Users[] = [];
selectedRoles:Roles[]=[];
targetPrivilege: Priviliges[] = [];

  distinctThings: Roles[]=[];
  selectedDataList: any = {};
  countries:any[]=[];
  groups: any[]=[];
  privi:any[]=[];
  id:String='';

  userId ?:String ='';
  roleId ?:String='';

  privilegeList:String[]=[];
  adFlag:boolean=false;
  euFlag:boolean=false;
  duFlag:boolean=false;
  arFlag:boolean=false;
  erFlag:boolean=false;
  drFlag:boolean=false;
  token:String='';
  privilegeLists:String[]=[];
  successMessage:String='';

   addForm:FormGroup=new FormGroup({

   }) ;




  constructor(private productService: ProductService, private messageService: MessageService, private router : Router, private storageservice : StorageService, public userservice:UserserviceService,public roleservice:RoleService,private formBuilder: FormBuilder,private ustorageservice:UstorageService) { }

  ngOnInit(): void {



    this.sourceCities = [
      { name: 'San Francisco', code: 'SF' },
      { name: 'London', code: 'LDN' },
      { name: 'Paris', code: 'PRS' },
      { name: 'Istanbul', code: 'IST' },
      { name: 'Berlin', code: 'BRL' },
      { name: 'Barcelona', code: 'BRC' },
      { name: 'Rome', code: 'RM' }];

  this.targetCities = [];


    this.events1 = [
      { status: 'Ordered', date: '15/10/2020 10:30', icon: PrimeIcons.SHOPPING_CART, color: '#9C27B0', image: 'game-controller.jpg' },
      { status: 'Processing', date: '15/10/2020 14:00', icon: PrimeIcons.COG, color: '#673AB7' },
      { status: 'Shipped', date: '15/10/2020 16:15', icon: PrimeIcons.ENVELOPE, color: '#FF9800' },
      { status: 'Delivered', date: '16/10/2020 10:00', icon: PrimeIcons.CHECK, color: '#607D8B' }
  ];

  this.events2 = [
      "2020", "2021", "2022", "2023"
  ];


 



    this.items = [
      { label: 'Angular.io', icon: 'pi pi-external-link', url: 'http://angular.io' },
      { label: 'Theming', icon: 'pi pi-bookmark', routerLink: ['/theming'] }
  ];

  this.cardMenu = [
      {
          label: 'Save', icon: 'pi pi-fw pi-check'
      },
      {
          label: 'Update', icon: 'pi pi-fw pi-refresh'
      },
      {
          label: 'Delete', icon: 'pi pi-fw pi-trash'
      },
  ];



  this.addForm=this.formBuilder.group(
    {
      name:new FormControl(''),
      username:new FormControl(''),
      role:new FormControl(''),
      group:new FormControl('')
  
    }
  )




  this.productService.getProducts().then(data => this.products = data);


  this.userservice.getUserPrivileges().then(res=>
    {
      this.privilegeLists=res
      console.log("checkkkkkkkkkkk")
      // console.log(res);
      console.log(this.privilegeLists)
    }
  )
  this.roleservice.getAllRoles().then(data=>this.roles=data);
  this.roleservice.getUniqueRoles().then(data=>this.unirole=data);
  this.roleservice.getAllPrivileges().then(data=>this.priviliges=data);

  console.log("unique role "+ this.unirole)


  

//    this.distinctThings = this.roles.filter(
//     (thing, i, arr) => arr.findIndex(t => t.id === thing.id) === i
//   );
//   console.log( "checkkkk6666"+this.distinctThings);

//   this.cols = [
//       { field: 'product', header: 'Product' },
//       { field: 'price', header: 'Price' },
//       { field: 'category', header: 'Category' },
//       { field: 'rating', header: 'Reviews' },
//       { field: 'inventoryStatus', header: 'Status' }
//   ];
 

 
  this.organizations= [
    { name: "Org1" },
    { name: "Org2" },
    { name: "Org3" },
    { name: "Org4"},
    { name: "Org5"}
];
this.countries= [
    { name: 'Org1', value: 'Org1' },
    { name: 'Org2', value: 'Org2' },
    { name: 'Org3', value: 'Org3' },
    { name: 'Org4', value: 'Org4' },
    { name: 'Org5', value: 'Org5' }
];
 

this.token=this.storageservice.getUser().token;
console.log( this.token);

if(this.storageservice.isLoggedIn())
  {

    this.userservice.getUsersByOrg(this.token).then(res=>
      {
        this.users=res;
      }
    )
  }
  else{

    this.userservice.getAllUsers(this.token).then(data=>
      {
          this.users=data;
          console.log("data"+ this.users[0].username);
  
          // for(let res in data)
          //     {
          //         this.users.push(data[res]);
          //     }
          //     console.log(this.users);
      }
     
  )
  }


  

  this.userservice.getUsers().subscribe((res:any[])=>
  {
    this.users1=res;
    console.log( this.users1);
  })



  // this.getAllUserData();

  }



 

deleteSelectedProducts() {
    this.deleteProductsDialog = true;
}

editProduct(product: Product) {
    this.product = { ...product };
    this.productDialog = true;
    // this.userEditDialog=true;
}

deleteProduct(product: Product) {
    this.deleteProductDialog = true;
    this.product = { ...product };
}

confirmDeleteSelected() {
    this.deleteProductsDialog = false;
    this.products = this.products.filter(val => !this.selectedProducts.includes(val));
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
    this.selectedProducts = [];
}

confirmDelete() {
    this.deleteProductDialog = false;
    this.products = this.products.filter(val => val.id !== this.product.id);
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
    this.product = {};
}

hideDialog() {
    // this.productDialog = false;
    this.submitted = false;
    this.userDialog=false;
    this.userEditDialog=false;
    this.roleDialog=false;
}

// saveProduct() {
//     this.submitted = true;

//     if (this.product.name?.trim()) {
//         if (this.product.id) {
//             // @ts-ignore
//             this.product.inventoryStatus = this.product.inventoryStatus.value ? this.product.inventoryStatus.value : this.product.inventoryStatus;
//             this.products[this.findIndexById(this.product.id)] = this.product;
//             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
//         } else {
//             this.product.id = this.createId();
//             this.product.code = this.createId();
//             this.product.image = 'product-placeholder.svg';
//             // @ts-ignore
//             this.product.inventoryStatus = this.product.inventoryStatus ? this.product.inventoryStatus.value : 'INSTOCK';
//             this.products.push(this.product);
//             this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
//         }

//         this.products = [...this.products];
//         this.productDialog = false;
//         this.product = {};
//     }
// }

// findIndexById(id: string): number {
//     let index = -1;
//     for (let i = 0; i < this.products.length; i++) {
//         if (this.products[i].id === id) {
//             index = i;
//             break;
//         }
//     }

//     return index;
// }

// createId(): string {
//     let id = '';
//     const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
//     for (let i = 0; i < 5; i++) {
//         id += chars.charAt(Math.floor(Math.random() * chars.length));
//     }
//     return id;
// }

onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
}


selected() {
    console.log(this.selectedDataList);
  }

  openNew() {


    if(this.isAccessAddUser())
      {
        this.product = {};
        this.submitted = false;
        // this.productDialog = true;
        this.userDialog=true;
      }
      else{
        // this.router.navigate(['/auth/access']);
        this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
      }
    
    
       
    }


  editUser(users: Users) {
    if(this.isAccessEditUser())
      {
        this.user = { ...users };
        this.userEditDialog=true;
      }
   else{
    // this.router.navigate(['/auth/access']);
    this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
   }
}
delUser(users: Users) {
  if(this.isAccessDeleteUser())
    {
      this.deleteUserDialog = true;
      this.user = { ...users };
    }
    else{
      // this.router.navigate(['/auth/access']);
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
    }
 
}
// delUser(users: Users) {
  
//       this.deleteUserDialog = true;
//       this.user = { ...users };
    
// }

openRole() {

  if(this.isAccessAddRole())
    {
      this.role = {};
      this.submitted = false;
      // this.productDialog = true;
      this.roleDialog=true;
    }
    else
    {
      // this.router.navigate(['/auth/access']);
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });

    }

 
}

editRole(roles: Roles) {
  if(this.isAccessEditRole())
    {
      this.role = { ...roles };
      this.roleDialog=true;
    }
    else{
      // this.router.navigate(['/auth/access']);
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
    }
  
}
delRole(roles: Roles) {
  if(this.isAccessDeleteRole())
    {
      this.deleteRoleDialog = true;
      this.role = { ...roles };
    }
    else{
      // this.router.navigate(['/auth/access']);
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
    }
 
}


  saveUser()
  {
    this.submitted=true;
    this.organizations=this.selectedMulti;
    
  this.id=this.storageservice.getUser().id;

  // Check if the user details are valid
  if (this.user.name && this.user.username && this.user.role  && this.selectedMulti.length>0) {
    
    
    
    const userData 
     = {
      name: this.user.name,
      username: this.user.username,
      role: this.user.role,
      group: this.groups,
      creator_id: this.id
      
    };


    this.selectedMulti.forEach(org => {
      console.log(typeof(org));
        console.log(org.name);
        userData.group.push(org.name);  
      });

    
console.log(this.selectedMulti)
    console.log(userData.role)
    // Replace 'YOUR_BACKEND_API_URL' with your actual backend API URL
    this.userservice.getAddUser(userData).then(
      response => {
        console.log('User saved successfully:', response);
        
        // Reset form and dialog
        this.user = {
          name: '',
          username: '',
          
        };

        this.selectedMulti = [];
        this.submitted = false;
        this.userDialog = false;
        this.user = {};
        this.userservice.getUsersByOrg(this.token).then(data=>
          {
            this.users=data;
          }
        )
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Added', life: 3000 });
        // this.router.navigate(["/usermanagent"]); 
      },
      error => {
        console.error('Error saving user:', error);
      }
    );

    
        this.user = {};
  } else {
    this.messageService.add({ severity: 'error', summary: 'Invalid', detail: 'Please enter valid input', life: 3000 });
    console.log('Invalid user details. Please fill in all the required fields.');
  }

    
// this.userservice.getAddUser(this.addForm).then(data=>
//     {
        
//     }
// )



  }

  saveEditUser()
  {




    this.submitted=true;
    // this.organizations=this.selectedMulti;
    
this.userId=this.user.id;

  // Check if the user details are valid
  if ( this.user.role  && this.selectedMulti.length>0) {
    
    
    
    const userData 
     = {
      role: this.user.role,
      group:this.groups
      
    };


    this.selectedMulti.forEach(org => {
      console.log(typeof(org));
        console.log(org.name);
        userData.group.push(org.name);  
      });

    
console.log(this.selectedMulti)



    console.log(  userData)
    // Replace 'YOUR_BACKEND_API_URL' with your actual backend API URL
    this.userservice.getEditUser(this.userId, userData).then(
      response => {
        console.log('User saved successfully:', response);
        
       
        this.selectedMulti = [];
        this.submitted = false;
        this.userEditDialog = false;
        this.user = {};

        this.userservice.getUsersByOrg(this.token).then(data=>
          {
            this.users=data;
          }
        )
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Updated', life: 3000 });
        // this.router.navigate(["/usermanagent"]); 
      },
      error => {
        console.error('Error saving user:', error);
      }
    );
    this.users=[...this.users]
    this.user = {};
  } else {
    this.messageService.add({ severity: 'error', summary: 'Invalid', detail: 'Please enter valid input', life: 3000 });
    console.log('Invalid user details. Please fill in all the required fields.');
  }


  }


  deleteUser()
  {
    this.deleteUserDialog=false
    this.userId=this.user.id;
    this.userservice.getDeleteUser(this.userId).then(response=>
      {
        console.log(response);
        // this.successMessage=JSON.parse(response);
          
            console.log('User deleted successfully:', this.successMessage);

        this.deleteUserDialog=false;
        this.user = {};
        
       
        // this.router.navigate(["/usermanagent"]); 
        
       
        // this.user = {};
        // this.router.navigate(["/usermanagent"]); 
        // this.getAllUserData();
        this.userservice.getUsersByOrg(this.token).then(data=>
          {
            this.users=data;
          }
        )
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Deleted', life: 3000 });
        // this.messageService.add({  summary: 'Failsed', detail: 'user Deleted', life: 3000 ,styleClass: "p-button-warning mr-2 mb-2"});

          
      
          


      },
      error => {
        console.error('Error delete user:', error);
      }

     
      
    );
   
    // this.userservice.getAllUsers().then(data=>

    //   {
    //     this.users=data;
    //   }
    // )
    this.users=[...this.users]
    this.user = {};
    console.log(this.users);
    console.log("Poisaaaaa");
    // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Deleted', life: 3000 });
    
  }
  // deleteUser() {
  //   this.deleteUserDialog = false;
  //   this.userId = this.user.id;
  //   this.userservice.getDeleteUser(this.userId).then(response => {
  //     if (response === 'Success') {
  //       console.log('User deleted successfully');
  //       this.deleteUserDialog = false;
  //       this.user = {};
  //       this.getAllUserData();
  //       this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Deleted', life: 3000 });
  //     } else {
  //       console.error('Unexpected response:', response);
  //     }
  //   }).catch(error => {
  //     console.error('Error deleting user:', error);
  //   });
  // }

  confirmDeleteRole()
  {
    
    this.deleteRoleDialog=false;
    this.roleId=this.role.id;
    this.roleservice.getDeleteRole(this.roleId).then(res=>
      {
        console.log('User deleted successfully:', res);

        this.roleservice.getAllRoles().then(data=>

          {
            this.roles=data;
          }
        )

      }
    )

  }



saveRole()
{
  this.submitted = true;

  
      if (this.role.id) {
          // @ts-ignore

          

          if ( this.targetPrivilege.length>0) {
    
    
    
            const roleData 
           = {
            name: this.role.name,
            org: this.role.org,
            privilege: this.privi,
                    
          };
        
        
            this.targetPrivilege.forEach(res => {
              // console.log(typeof(org));
              //   console.log(org.name);
                roleData.privilege.push(res.name);  
              });
        
            
        console.log(this.targetPrivilege)
            console.log(roleData)
            // Replace 'YOUR_BACKEND_API_URL' with your actual backend API URL
            this.roleservice.getEditRole(this.role.id,roleData).then(
              response => {
                console.log('Role Edit successfully:', response);
                
               
                this.role={
                  name: '',
                  org: ''
                };
        
                this.targetPrivilege = [];
                this.submitted = false;
                this.roleDialog = false;
                this.role = {};
                this.roleservice.getAllRoles().then(data=>this.roles=data);
             
                // this.router.navigate(["/usermanagent"]); 
              },
              error => {
                console.error('Error saving user:', error);
              }
            );
                 
                this.role = {};
            
                this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Role Updated', life: 3000 });
        }

          
      } else {
         const roleData 
           = {
            name: this.role.name,
            org: this.role.org,
            privilege: this.privi,
                    
          };

        if (this.role.name && this.role.org  && this.targetPrivilege.length>0) {
    
    
    
         
      
      
          this.targetPrivilege.forEach(res => {
            // console.log(typeof(org));
            //   console.log(org.name);
              roleData.privilege.push(res.name);  
            });
      
          
      console.log(this.targetPrivilege)
          console.log(roleData)
          // Replace 'YOUR_BACKEND_API_URL' with your actual backend API URL
          this.roleservice.getAddRole(roleData).then(
            response => {
              console.log('Role saved successfully:', response);
              
             
              this.role={
                name: '',
                org: ''
              };
      
              this.targetPrivilege = [];
              this.submitted = false;
              this.roleDialog = false;
              this.role = {};
              this.roleservice.getAllRoles().then(data=>this.roles=data);
              this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Role Added', life: 3000 });
              // this.router.navigate(["/usermanagent"]); 
            },
            error => {
              console.error('Error saving user:', error);
            }
          );
               
              this.role = {};
          
              this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Role Added', life: 3000 });
      }

      this.roles = [...this.roles];
      this.roleDialog = false;
      this.role = {};

}

}


  onSortChange(event: any) {
    const value = event.value;

    if (value.indexOf('!') === 0) {
        this.sortOrder = -1;
        this.sortField = value.substring(1, value.length);
    } else {
        this.sortOrder = 1;
        this.sortField = value;
    }
}

onFilter(dv: DataView, event: Event) {
    dv.filter((event.target as HTMLInputElement).value);
}



isAccessAddUser():boolean
{
// this.privilegeList=this.storageservice.getUser().privilege;
// this.privilegeList=this.ustorageservice.getUser().privilege;

// console.log(this.ustorageservice.getUser());

console.log("isAcessssss")
console.log(this.privilegeLists);

//console.log(this.privilegeList);
console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
{
  this.privilegeLists.forEach(res=>
    {
      if(res==='ACCESS_ADD_USER')
        {
          this.adFlag=true;
        }
        // else if(res===''){
        //   this.adFlag=false;
        // }
        // else{
        //   this.adFlag=false;
        // }
    }
  )

}
else
{
  this.adFlag=false;
}


console.log(this.adFlag);


  return this.adFlag;
}

isAccessEditUser():boolean
{
//   this.privilegeList=this.ustorageservice.getUser().privilege;
// console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_EDIT_USER")
//       {
//         this.euFlag=true;
//       }
//   }
// )





if(this.storageservice.isLoggedIn())
  {
    this.privilegeLists.forEach(res=>
      {
        if(res==='ACCESS_EDIT_USER')
          {
            this.euFlag=true;
          }
          // else if(res===''){
          //   this.euFlag=false;
          // }
          // else{
          //   this.euFlag=false;
          // }
      }
    )
  
  }
  else
  {
    this.euFlag=false;
  }

console.log(this.euFlag);


  return this.euFlag;
}

isAccessDeleteUser():boolean
{
// this.privilegeList=this.ustorageservice.getUser().privilege;
// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_DELETE_USER")
//       {
//         this.duFlag=true;
//       }
//   }
// )
console.log("delete user access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_DELETE_USER')
          {
            this.duFlag=true;
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
    this.duFlag=false;
  }
  

console.log(this.duFlag);


  return this.duFlag;
}

isAccessAddRole():boolean
{
// this.privilegeList=this.ustorageservice.getUser().privilege;
// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_ADD_ROLE")
//       {
//         this.arFlag=true;
//       }
//   }
// )


console.log("Add role access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_ADD_ROLE')
          {
            this.arFlag=true;
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
    this.arFlag=false;
  }

console.log(this.arFlag);


  return this.arFlag
}

isAccessEditRole():boolean
{
// this.privilegeList=this.ustorageservice.getUser().privilege;
// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_EDIT_ROLE")
//       {
//         this.erFlag=true;
//       }
//   }
// )

console.log("Edit role access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_EDIT_ROLE')
          {
            this.erFlag=true;
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
    this.erFlag=false;
  }





console.log(this.erFlag);


  return this.erFlag;
}



isAccessDeleteRole():boolean
{


console.log("Delete role access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_DELETE_ROLE')
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





console.log(this.drFlag);


  return this.drFlag;
}


// getAllUserData()
// {
//   this.userservice.getAllUsers().then(data=>

//       {
//         this.users=data;
//       }
//     )
// }

}
