import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/layout/guards/auth.guard';

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'error', loadChildren: () => import('./error/error.module').then(m => m.ErrorModule) },
        { path: 'access', loadChildren: () => import('./access/access.module').then(m => m.AccessModule) ,canActivate: [AuthGuard]},
        { path: 'login', loadChildren: () => import('./login/login.module').then(m => m.LoginModule) },
        { path: 'logout', loadChildren: () => import('./logout/logout.module').then(m => m.LogoutModule) ,canActivate: [AuthGuard]},
        // { path: 'password', loadChildren: () => import('./password/password.module').then(m => m.PasswordModule) }
        { path: 'password', loadChildren: () => import('./passwrd/passwrd.module').then(m => m.PasswrdModule) ,canActivate: [AuthGuard]}

    ])],
    exports: [RouterModule]
})
export class AuthRoutingModule { }
