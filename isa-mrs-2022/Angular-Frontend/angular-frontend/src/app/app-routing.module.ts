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
  { path: 'admin_home', component: AdminHomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'profileCottage/:id', component: ProfileComponent },
  { path: 'profileAdventure/:id', component: ProfileAdventureComponent },
  { path: 'profileBoat/:id', component: ProfileBoatComponent },
  // { path: 'register_home/:username', component: MyUserProfileComponent },
  { path: '**', redirectTo: '/home', pathMatch: 'full' },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
