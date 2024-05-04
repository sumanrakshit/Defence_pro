import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FieldComponent } from './field.component';

// const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild([
    { path: '', component: FieldComponent }
  ])],
  exports: [RouterModule]
})
export class FieldRoutingModule { }
