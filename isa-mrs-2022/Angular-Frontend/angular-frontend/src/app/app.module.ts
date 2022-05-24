import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatSliderModule } from '@angular/material/slider';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CottagesHomePageComponent } from './components/cottages-home-page/cottages-home-page.component';
import { BoatsHomePageComponent } from './components/boats-home-page/boats-home-page.component';
import { AdventuresHomePageComponent } from './components/adventures-home-page/adventures-home-page.component';
import { InstructorsHomePageComponent } from './components/instructors-home-page/instructors-home-page.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { ProfileAdventureComponent } from './components/profile-adventure/profile-adventure.component';
import { ProfileBoatComponent } from './components/profile-boat/profile-boat.component';
import { NgImageSliderModule } from 'ng-image-slider';
import { MatTableModule } from '@angular/material/table';
import { MyUserProfileComponent } from './components/my-user-profile/my-user-profile.component';
import { RegisterUserHomePageComponent } from './components/register-user-home-page/register-user-home-page.component';
import { RegisterUserHistoryReservationComponent } from './components/register-user-history-reservation/register-user-history-reservation.component';
import { AdminHomePageComponent } from './components/admin-home-page/admin-home-page.component';
import { InterceptorServiceService } from './services/interceptor/interceptor-service.service';
import { LoginCottageProfileComponent } from './components/login-cottage-profile/login-cottage-profile.component';
import { LoginBoatProfileComponent } from './components/login-boat-profile/login-boat-profile.component';
import { LoginAdventureProfileComponent } from './components/login-adventure-profile/login-adventure-profile.component';
import { RootAdventureProfileComponent } from './components/root-adventure-profile/root-adventure-profile.component';
import { RootBoatProfileComponent } from './components/root-boat-profile/root-boat-profile.component';
import { RootCottageProfileComponent } from './components/root-cottage-profile/root-cottage-profile.component';
import { FullCalendarModule } from '@fullcalendar/angular'; // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin!
import interactionPlugin from '@fullcalendar/interaction';
import { MyUserActionsComponent } from './components/my-user-actions/my-user-actions.component';
import { MyUserSubscribeEntityComponent } from './components/my-user-subscribe-entity/my-user-subscribe-entity.component';
import { HomePageMenuComponent } from './components/home-page-menu/home-page-menu.component'; // a plugin!
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { AdminMenuComponent } from './components/admin-menu/admin-menu.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { AdminNewPassComponent } from './components/admin-new-pass/admin-new-pass.component';



FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  interactionPlugin
]);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
    CottagesHomePageComponent,
    BoatsHomePageComponent,
    AdventuresHomePageComponent,
    InstructorsHomePageComponent,
    ProfileAdventureComponent,
    ProfileBoatComponent,
    MyUserProfileComponent,
    RegisterUserHomePageComponent,
    RegisterUserHistoryReservationComponent,
    AdminHomePageComponent,
    LoginCottageProfileComponent,
    LoginBoatProfileComponent,
    LoginAdventureProfileComponent,
    RootAdventureProfileComponent,
    RootBoatProfileComponent,
    RootCottageProfileComponent,
    MyUserActionsComponent,
    MyUserSubscribeEntityComponent,
    HomePageMenuComponent,
    AdminMenuComponent,
    AdminLoginComponent,
    AdminNewPassComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    MDBBootstrapModule.forRoot(),
    MatSliderModule,
    MatGridListModule,
    MatPaginatorModule,
    MatOptionModule,
    MatSelectModule,
    HttpClientModule,
    MatInputModule,
    MatFormFieldModule,
    NgImageSliderModule,
    MatTableModule,
    FullCalendarModule, // register FullCalendar with you app
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorServiceService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
