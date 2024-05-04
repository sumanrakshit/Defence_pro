import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsermanageComponent } from './usermanage.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild([
    { path: '', component: UsermanageComponent }
])],
  exports: [RouterModule]
})
export class UsermanageRoutingModule { }
