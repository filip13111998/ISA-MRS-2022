import { AdminServiceService } from './../../services/admin/admin-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Token } from 'src/app/models/response/login/login-token';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  loginForm = this.fb.group({
    username: [null],
    password: [null]

  });

  token: Token;

  username: String;

  constructor(private ls: LoginService, private fb: FormBuilder, private router: Router, private as: AdminServiceService) { }

  ngOnInit(): void {
  }

  public login() {
    // console.log(this.loginForm.value)
    this.ls.login(this.loginForm.value).subscribe((tkn: Token) => {
      // console.log(tkn.split('.')[1]);
      // console.log(JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles']);
      localStorage.setItem('user_token', tkn.accessToken)

      this.token = tkn;
      // this.token = localStorage.getItem('user_token');
      this.username = JSON.parse(atob(this.token.accessToken.split('.')[1]))['sub'];


      let my_roles = JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles'].split(',');
      if (my_roles.includes('ROLE_ADMIN')) {
        this.as.check(this.username).subscribe((b: Boolean) => {
          if (b) {
            this.router.navigate(['/', 'admin_home']);
          }
          else {
            this.router.navigate(['/', 'admin_new_pass']);
          }
        }
        );

      }
      // if (my_roles.includes('ROLE_USER')) {
      //   this.router.navigate(['/', 'register_home']);
      //   // console.log("IMA I USER")
      // }
      // console.log(JSON.parse(atob(tkn.token.split('.')[1])));
      // this.token = tkn;
      // this.router.navigate(['/', 'register_home']);
    }
    );

  }

}
