import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { AdminComplaintsComponent } from './components/admin-complaints/admin-complaints.component';
import { AdminMarksComponent } from './components/admin-marks/admin-marks.component';
import { AdminLoyalityPagComponent } from './components/admin-loyality-pag/admin-loyality-pag.component';
import { AdminUpdateProfilComponent } from './components/admin-update-profil/admin-update-profil.component';
import { AdminAddAdminComponent } from './components/admin-add-admin/admin-add-admin.component';
import { AdminDeleteOwnersComponent } from './components/admin-delete-owners/admin-delete-owners.component';
import { AdminDeleteUserComponent } from './components/admin-delete-user/admin-delete-user.component';
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


  /* ADMIN URLS */
  { path: 'admin_home', component: AdminHomePageComponent },
  { path: 'admin_login', component: AdminLoginComponent },
  { path: 'admin_new_pass', component: AdminNewPassComponent },
  { path: 'delete_entity', component: AdminDeleteEntityComponent },
  { path: 'delete_users', component: AdminDeleteUserComponent },
  { path: 'delete_owners', component: AdminDeleteOwnersComponent },
  { path: 'add_admin', component: AdminAddAdminComponent },
  { path: 'update_profile', component: AdminUpdateProfilComponent },
  { path: 'loyality_program', component: AdminLoyalityPagComponent },
  { path: 'admin_mark', component: AdminMarksComponent },
  { path: 'admin_complaint', component: AdminComplaintsComponent },
  { path: 'report', component: AdminReportsComponent },


  /* REGISTER USER URLS */
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
