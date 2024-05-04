import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'crud', loadChildren: () => import('./crud/crud.module').then(m => m.CrudModule) },
        { path: 'empty', loadChildren: () => import('./empty/emptydemo.module').then(m => m.EmptyDemoModule) },
        { path: 'timeline', loadChildren: () => import('./timeline/timelinedemo.module').then(m => m.TimelineDemoModule) },
        { path: 'template', loadChildren: () => import('./template/template.module').then(m => m.TemplateModule) },
        { path: 'field/:id', loadChildren: () => import('./field/field.module').then(m => m.FieldModule) },
    ])],


    exports: [RouterModule]
})
export class PagesRoutingModule { }
