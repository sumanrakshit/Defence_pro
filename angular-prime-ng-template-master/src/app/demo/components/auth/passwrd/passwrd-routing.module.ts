import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PasswrdComponent } from './passwrd.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild([
    { path: '', component: PasswrdComponent }
])],
  exports: [RouterModule]
})
export class PasswrdRoutingModule { }
