import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Templates } from '../api/template';
import { TemplateFileds } from '../api/templatefield';

@Injectable({
  providedIn: 'root'
})
export class TemplateService {

  constructor(private http:HttpClient) { }



  getAllTemplates()
  {
    return this.http.get<any>('http://localhost:8080/v1/templates')
    .toPromise()
    .then(res => res as Templates[])
    .then(data => data);
  }

  getAddField(id:any,field:any)
  {
    return this.http.post<any>('http://localhost:8080/v1/template_fields/'+id, field)
    .toPromise()
    .then(res => res as TemplateFileds[])
    .then(data => data);

  }
  getTemplateList()
  {
    return this.http.get<any>('http://localhost:8080/v1/template_fields')
    .toPromise()
    .then(res => res as Templates[])
    .then(data => data);
  }
  
  getTemplateListById(id:any)
  {
    return this.http.get<any>('http://localhost:8080/v1/template_fields/'+id)
    .toPromise()
    .then(res => res as Templates[])
    .then(data => data);
  }


  getAddTemplate(selecfile:any, name:any, description:any)
  {
//     const headers = new HttpHeaders();
// headers.set('Content-Type', 'multipart/form-data');
const formData=new FormData();
formData.append('imgicon',selecfile);
formData.append('name', name);
formData.append('description', description);



    // return this.http.post<any>('http://localhost:8080/v1/templates', 
    //   {
    //     // headers: headers,
    //     params: {
    //       imgicon: imageicon,
    //       name: name,
    //       description: description
    //     }
    //   }
    // )
    // .toPromise()
    // .then(res => res as TemplateFileds[])
    // .then(data => data);

    return this.http.post('http://localhost:8080/v1/templates', formData);
  }

  getDeleteTemplate(id:any)
  {

    return this.http.get<any>('http://localhost:8080/v1/templates/'+id)
    .toPromise()
    .then(res => res as Templates[])
    .then(data => data);
  }

}
