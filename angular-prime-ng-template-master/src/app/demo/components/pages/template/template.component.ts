import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { DataView } from 'primeng/dataview';
import { Product } from 'src/app/demo/api/product';
import { Templates } from 'src/app/demo/api/template';
import { ProductService } from 'src/app/demo/service/product.service';
import { TemplateService } from 'src/app/demo/service/template.service';
import { UserserviceService } from 'src/app/demo/service/userservice.service';
import { StorageService } from 'src/app/layout/service/storage.service';
import { UstorageService } from 'src/app/layout/service/ustorage.service';

@Component({
  selector: 'app-template',
  providers: [MessageService],
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  
})
export class TemplateComponent implements OnInit {

  products: Product[] = [];

  sortOptions: SelectItem[] = [];

  sortOrder: number = 0;

  sortField: string = '';

  sourceCities: any[] = [];

  targetCities: any[] = [];

  orderCities: any[] = [];


  templates: Templates[]=[];
  template: Templates={};

  imageList: SafeResourceUrl[] = [];
  imagePath: any;
  
  selectedTemplate: Templates[] = [];

  tamplateDialog:boolean=false;
  deletetemplateDialog:boolean=false;
  deletetemplatesDialog:boolean=false;
  submitted :boolean=false;
  selectedFile ?: File|null=null ;
  privilegeList:String[]=[];
  adFlag:boolean=false;
  etFlag:boolean=false;
  templateId ?:String='';
  privilegeLists:String[]=[];

  flag:boolean=false;
  dtFlag:boolean=false;
//   tampData:any={}
// tempateData:FormData = new FormData();
// formData = {
//   name: '',
//   description: '',
//   imgicon: null as File | null
// };


  constructor( private messageService: MessageService,private productService: ProductService, private templateService: TemplateService, private router : Router, 
    private storageservice : StorageService, public _sanitizer: DomSanitizer, private http:HttpClient,private ustorageservice:UstorageService,public userservice:UserserviceService) { }

  ngOnInit(): void {

    

    this.productService.getProducts().then(data => this.products = data);

    this.userservice.getUserPrivileges().then(res=>
      {
        this.privilegeLists=res
        console.log("checkkkkkkkkkkk")
        // console.log(res);
        console.log(this.privilegeLists)
      }
    )



   
      
    this.sourceCities = [
        { name: 'San Francisco', code: 'SF' },
        { name: 'London', code: 'LDN' },
        { name: 'Paris', code: 'PRS' },
        { name: 'Istanbul', code: 'IST' },
        { name: 'Berlin', code: 'BRL' },
        { name: 'Barcelona', code: 'BRC' },
        { name: 'Rome', code: 'RM' }];

    this.targetCities = [];

    this.orderCities = [
        { name: 'San Francisco', code: 'SF' },
        { name: 'London', code: 'LDN' },
        { name: 'Paris', code: 'PRS' },
        { name: 'Istanbul', code: 'IST' },
        { name: 'Berlin', code: 'BRL' },
        { name: 'Barcelona', code: 'BRC' },
        { name: 'Rome', code: 'RM' }];

    this.sortOptions = [
        { label: 'Price High to Low', value: '!price' },
        { label: 'Price Low to High', value: 'price' }
    ];



this.templateService.getAllTemplates().then(data=>
  {
    this.templates=data;
    this.templates.forEach((res1)=>
      {
    
          this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' 
        + res1.fileData);  
        this.imageList.push(this.imagePath);

        
        console.log(res1.fileData);
      
        
      })
      
     
  }
)

console.log(   this.imageList);

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

openNew()
{
  if(this.isAccessAddTemplate())
    {
      this.template={};
this.tamplateDialog=true;
this.submitted =false;
    }
    else
    {
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
      // this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
      this.router.navigate(['/auth/access']);
      
    }
  
}

deleteSelectedProducts()
{

}

getTemplateIndex(template: any): number {
    return this.templates.indexOf(template);
  }

  hideDialog()
  {
  this.tamplateDialog=false;

  }



  onFileSelected(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
    // this.selectedFile = event.target.files[0];
    console.log("check")
    // console.log( this.formData.imgicon)
  }
  saveTemplate() {

    // event.preventDefault(); 
    // if (!this.selectedFile) {
    //   console.error('No file selected.');
    //   return;
    // }





    //  this.tampData=
    // {
    //   name:this.template.name,
    //   description:this.template.description

    // }
    // this.tempateData.append('image', this.selectedFile, this.selectedFile.name);
    // this.tempateData.append('name', this.template.name);
    // this.tempateData.append('description', this.template.description);
   

    // const formData = new FormData(
      

    // );


    // formData.append('image', this.selectedFile);
    // formData.append('template', this.tampData);

    // console.log(formData)

   

    // this.http.post<any>('YOUR_BACKEND_ENDPOINT', formData).subscribe(
    //   response => {
    //     console.log('Upload successful!', response);
    //     // Reset form fields after successful upload
    //     this.selectedFile = null;
    //     this.name = '';
    //     this.description = '';
    //   },
    //   error => {
    //     console.error('Upload failed:', error);
    //   }
    // );

//     this.templateService.getAddTemplate(this.selectedFile, this.template.name,this.template.description,).then(res =>
//       {
//         console.log("Template add successfully", res);
//         this.template=
//         {
//           name:'',
//           description:''
//         };
//         this.selectedFile=null;
//         this.submitted=false;
//         this.tamplateDialog=false;
//         this.templateService.getAllTemplates().then(data=>
//           {
//             this.templates=data;
//           }
//         )
//         this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'User Added', life: 3000 });
//       },
//       error => {
//             console.error('Add template failed:', error);
//           }
//     )
// this.template={}

// const formData = new FormData();
// formData.append('name', this.formData.name);
// formData.append('description', this.formData.description);
// if (this.formData.imgicon) {
//   formData.append('imgicon', this.formData.imgicon,this.formData.imgicon.name);
// }




// this.templateService.getAddTemplate(formData.get("imgicon"),formData.get("name"),formData.get("description")).then(res=>
//   {
//       console.log("add data "+ res);
//       this.selectedFile=null;
//         this.submitted=false;
//         this.tamplateDialog=false;
//   }

// )

this.submitted=true;
this.templateService.getAddTemplate(this.selectedFile , this.template.name,this.template.description).subscribe(res=>
  {
    console.log("Add template Successfully" +res);
    this.selectedFile=null;
    this.template=
    {
      name:'',
      description:''
    };
    this.tamplateDialog=false;
    this.submitted=false;
    this.templateService.getAllTemplates().then(data=>

      {
        this.templates=data;
      }
    )
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Template Added', life: 3000 });

  }

);
this.template={};

  }


  isAccessAddTemplate():boolean
{
// this.privilegeList=this.storageservice.getUser().privilege;
// this.privilegeList=this.ustorageservice.getUser().privilege;
// console.log(this.ustorageservice.getUser());
// console.log(this.privilegeList);

// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_ADD_TEMPLATE")
//       {
//         this.adFlag=true;
//       }
//   }
// )

console.log("Add template access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_ADD_TEMPLATE')
          {
            this.adFlag=true;
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
    this.adFlag=false;
  }

console.log(this.adFlag);


  return this.adFlag;
}





isAccessEditTemplate():boolean
{
// this.privilegeList=this.storageservice.getUser().privilege;
// this.privilegeList=this.ustorageservice.getUser().privilege;
// console.log(this.ustorageservice.getUser());
// console.log(this.privilegeList);

// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_EDIT_TEMPLATE")
//       {
//         this.etFlag=true;
//       }
//   }
// )

console.log("Edit template access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_EDIT_TEMPLATE')
          {
            this.etFlag=true;
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
    this.etFlag=false;
  }

console.log(this.etFlag);


  return this.etFlag;
}

editDecoy()
{
  this.templateId=this.template.id;

  if(this.isAccessEditTemplate())
    {
      this.router.navigate(['/temfield', this.templateId]);
    }
    else{
      this.router.navigate(['/usermanagent']);
    }
}


isAcessDecoy()
{
// this.privilegeList=this.storageservice.getUser().privilege;
// this.privilegeList=this.ustorageservice.getUser().privilege;
// console.log(this.ustorageservice.getUser());
// console.log(this.privilegeList);

// //console.log(this.privilegeList);
// this.privilegeList.forEach(res=>
//   {
//     if(res=="ACCESS_EDIT_TEMPLATE")
//       {
//         this.etFlag=true;
//       }
//   }
// )

console.log("Edit template access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_DECOY')
          {
            this.router.navigate(['/temfield', this.templateId]);
          }
          else
          {
            this.router.navigate(['/auth/access']);
            // this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
 
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
    this.router.navigate(['/auth/access']);
    this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
 
   
  }

console.log(this.etFlag);


}

showWarningMessage()
{
  this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
 

}

delTemplate(templates:Templates)
{
  if(this.isAccessDeleteTemplate())
    {
      this.deletetemplateDialog= true;
      this.template = { ...templates };
    }
    else{
      this.router.navigate(['/auth/access']);
      this.messageService.add({ severity: 'warn', summary: 'Access Denied', detail: 'You do not have the permissions', life: 3000 });
    }

}



confirmDeleteTemplate()
{
  this.deletetemplateDialog=false;
  const id=this.template.id;
  this.templateService.getDeleteTemplate(id).then(res=>
    {
      console.log("Sucessfully deleted" +res);
    }
  )
  

  this.templateService.getAllTemplates().then(data=>
    {
      this.templates=data;
      this.templates.forEach((res1)=>
        {
      
            this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,' 
          + res1.fileData);  
          this.imageList.push(this.imagePath);
  
          
          console.log(res1.fileData);
        
          
        })
        
       
    }
  )

}
confirmDeleteSelected()
{

}



isAccessDeleteTemplate():boolean

{
  console.log("Add template access");

console.log(this.storageservice.isLoggedIn());

if(this.storageservice.isLoggedIn())
  {
    console.log("login check")
    this.privilegeLists.forEach(res=>
      {
        console.log(res)
        if(res==='ACCESS_DELETE_TEMPLATE')
          {
            this.dtFlag=true;
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
    this.dtFlag=false;
  }

console.log(this.adFlag);


  return this.dtFlag;

}


}
