import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplatefieldComponent } from './templatefield.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild([
    { path: '', component: TemplatefieldComponent }
])],
  exports: [RouterModule]
})
export class TemplatefieldRoutingModule { }
