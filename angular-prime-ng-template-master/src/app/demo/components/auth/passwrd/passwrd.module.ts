import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PasswrdRoutingModule } from './passwrd-routing.module';
import { PasswrdComponent } from './passwrd.component';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { PasswordModule } from 'primeng/password';


@NgModule({
  declarations: [PasswrdComponent],
  imports: [
    CommonModule,
    PasswrdRoutingModule,
    ButtonModule,
    CheckboxModule,
    InputTextModule,
    FormsModule,
    PasswordModule
  ]
})
export class PasswrdModule { }
