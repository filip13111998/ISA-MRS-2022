import { ValidateUserComponent } from './components/validate-user/validate-user.component';
import { AdminDeleteEntityComponent } from './components/admin-delete-entity/admin-delete-entity.component';
import { AdminNewPassComponent } from './components/admin-new-pass/admin-new-pass.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { MyUserActionsComponent } from './components/my-user-actions/my-user-actions.component';
import { MyUserSubscribeEntityComponent } from './components/my-user-subscribe-entity/my-user-subscribe-entity.component';
import { RegisterUserHistoryReservationComponent } from './components/register-user-history-reservation/register-user-history-reservation.component';
import { LoginBoatProfileComponent } from './components/login-boat-profile/login-boat-profile.component';
import { LoginAdventureProfileComponent } from './components/login-adventure-profile/login-adventure-profile.component';
import { LoginCottageProfileComponent } from './components/login-cottage-profile/login-cottage-profile.component';
import { AdminHomePageComponent } from './components/admin-home-page/admin-home-page.component';
import { RegisterUserHomePageComponent } from './components/register-user-home-page/register-user-home-page.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileBoatComponent } from './components/profile-boat/profile-boat.component';
import { ProfileAdventureComponent } from './components/profile-adventure/profile-adventure.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MyUserProfileComponent } from './components/my-user-profile/my-user-profile.component';

const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'register_home', component: RegisterUserHomePageComponent },
  { path: 'register_reservation', component: RegisterUserHistoryReservationComponent },
  { path: 'register_profile', component: MyUserProfileComponent },
  { path: 'register_subscribe', component: MyUserSubscribeEntityComponent },
  { path: 'register_actions', component: MyUserActionsComponent },



  { path: 'admin_home', component: AdminHomePageComponent },
  { path: 'admin_login', component: AdminLoginComponent },
  { path: 'admin_new_pass', component: AdminNewPassComponent },
  { path: 'delete_entity', component: AdminDeleteEntityComponent },

  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'validate', component: ValidateUserComponent },
  { path: 'validate/:username', component: ValidateUserComponent },

  { path: 'profileCottage/:id', component: ProfileComponent },
  { path: 'loginProfileCottage/:id', component: LoginCottageProfileComponent },
  { path: 'profileAdventure/:id', component: ProfileAdventureComponent },
  { path: 'loginProfileAdventure/:id', component: LoginAdventureProfileComponent },
  { path: 'profileBoat/:id', component: ProfileBoatComponent },
  { path: 'loginProfileBoat/:id', component: LoginBoatProfileComponent },
  // { path: 'register_home/:username', component: MyUserProfileComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
