import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
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
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
