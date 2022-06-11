import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  reg_user = this.fb.group({
    username: [null],
    password: [null],
    password2: [null],
    firstName: [null],
    lastName: [null],
    email: [null],
    phoneNumber: [null],
    adresa: [null],
    grad: [null],
    drzava: [null]
  })

  constructor(private ls: LoginService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
  }

  public register() {
    console.log(this.reg_user.value)

    if (this.reg_user.value.password != this.reg_user.value.password2) {
      console.log("DIFFERENT PASSWORD");
      return;
    }

    this.ls.register(this.reg_user.value).subscribe((b: Boolean) => {
      if (b) {
        this.router.navigate(['/', 'login']);
      }
    }
    );

  }

}
