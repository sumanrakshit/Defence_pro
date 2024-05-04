import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FieldRoutingModule } from './field-routing.module';
import { FieldComponent } from './field.component';


@NgModule({
  declarations: [FieldComponent],
  imports: [
    CommonModule,
    FieldRoutingModule
  ]
})
export class FieldModule { }
