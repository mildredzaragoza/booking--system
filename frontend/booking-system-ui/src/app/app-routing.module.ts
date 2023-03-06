import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuestFormComponent } from './components/guest-form/guest-form.component';
import { GuestsListComponent } from './components/guests-list/guests-list.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MainViewComponent } from './components/main-view/main-view.component';

const routes: Routes = [
  { path: 'guests', component: GuestsListComponent},
  { path: 'guest-form', component: GuestFormComponent},
  { path: 'main', component: MainViewComponent},
  { path: 'login', component: LoginFormComponent},
  { path: '', redirectTo: '/main', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
