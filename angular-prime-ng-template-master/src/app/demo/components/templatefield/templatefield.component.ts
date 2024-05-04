import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { StorageService } from 'src/app/layout/service/storage.service';
import { TemplateFileds } from '../../api/templatefield';
import { TemplateService } from '../../service/template.service';
import { HttpClient } from '@angular/common/http';




@Component({
  selector: 'app-templatefield',
  templateUrl: './templatefield.component.html',
  styleUrls: ['./templatefield.component.scss'],
  providers: [MessageService]
})
export class TemplatefieldComponent implements OnInit {


  uploadedFiles: any[] = [];


  fieldList:String[]=[];
  fields: TemplateFileds []=[];


  templatefield:TemplateFileds={};
  
  field:TemplateFileds={};
  check:any[]=[];
  lists:any[]=[];
  id:String[]=[];

  constructor( private router : Router, private messageService: MessageService, private storageservice : StorageService, private templateService: TemplateService,private route: ActivatedRoute,private http: HttpClient) { }
  


  ngOnInit(): void {

    this.check = [
      "True", "False"
  ];

  this.route.params.subscribe(params => {
    this.id = params['id'];
  });

  this.templateService.getTemplateListById(this.id).then(res=>
    {
      this.fields=res;


    }
  )


  
    
  }



  addField(newField: string) {
    if (newField) {
      this.fieldList.push(newField);
    }
  }



  saveField()
  {


    


    if (this.field.has_host && this.field.has_user   && this.fieldList.length>0) {
    
    
    
      const listData 
       = {
        has_user: this.field.has_user,
        has_host: this.field.has_host,
        addfields: this.lists,
        decoyId: this.id
        
      };
  
  
      this.fieldList.forEach(org => {
        console.log(typeof(org));
          console.log(org);
          listData.addfields.push(org);  
        });
  
      

      // Replace 'YOUR_BACKEND_API_URL' with your actual backend API URL


      this.templateService.getAddField(this.id, listData).then(response=>
        {
          console.log('List field saved successfully:', response);
          this.fieldList=[];
          this.field={};

          this.templateService.getTemplateListById(this.id).then(res=>
            {
              this.fields=res;
        
              
            }
          )
        }
      )
      
          // this.router.navigate(["/usermanagent"]); 
       

  }
  this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Field add successfull', life: 3000 });
}



saveDecoy()
{

}



onUpload(event: any) {
  for (const file of event.files) {
      this.uploadedFiles.push(file);
  }

  // this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded' });
}


downlaod() {
  console.log("downlading!...");
  // const URL =
  //   "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
  const URL =
    "https://staging.safegold.com/display/sales-invoice/da771e90-aa8f-4147-bc7c-805b73bb1283";

  this.http.get(URL, { responseType: "arraybuffer" }).subscribe(
    pdf => {
      const blob = new Blob([pdf], { type: "application/pdf" });
      // var url= window.URL.createObjectURL(blob);
      // window.open(url);
      const fileName = "sumantest.pdf";
      // saveAs(blob, fileName);
    },
    err => {
      console.log("err->", err);
    }
  );
}

//  downloadPdf() {
//     const pdfUrl =
//       "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
//     // const pdfUrl =
//     //   "https://staging.safegold.com/display/sales-invoice/da771e90-aa8f-4147-bc7c-805b73bb1283";
//     const pdfName = "invoice.pdf";
//     FileSaver.saveAs(pdfUrl, pdfName);
//   }

}


// function saveAs(blob: Blob, fileName: string) {
//   throw new Error('Function not implemented.');
// }
// function saveAs(blob: Blob, fileName: string) {
//   throw new Error('Function not implemented.');
// }
