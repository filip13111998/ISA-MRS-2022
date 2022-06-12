import { LoginService } from 'src/app/services/login-service/login.service';
import { AdminLoginDTO } from './../../models/response/admin-login/adminLogin';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { Token } from 'src/app/models/response/login/login-token';

@Component({
  selector: 'app-admin-new-pass',
  templateUrl: './admin-new-pass.component.html',
  styleUrls: ['./admin-new-pass.component.css']
})
export class AdminNewPassComponent implements OnInit {

  loginForm = this.fb.group({
    password1: [null],
    password2: [null]

  });


  constructor(private fb: FormBuilder, private router: Router, private as: AdminServiceService, private ls: LoginService) { }

  ngOnInit(): void {
  }

  public login() {
    if (this.loginForm.value['password1'] != this.loginForm.value['password2']) {
      return;
    }

    var token = localStorage.getItem('user_token');

    if (token != null) {

      var dto = new AdminLoginDTO();

      dto.password = this.loginForm.value['password1'];
      dto.username = JSON.parse(atob(token.split('.')[1]))['sub'];;

      this.as.newPassword(dto).subscribe((b: Boolean) => {
        if (b) {

          var millisecondsToWait = 1000;
          setTimeout(() => {
            this.automaticLogin(dto.username, dto.password);
            this.router.navigate(['/', 'admin_home']);
            // this.router.navigate(['/', 'register_profile']);
            this.ngOnInit();
          }, millisecondsToWait);


        }

      }
      );

    }



  }

  public automaticLogin(username: String, password: String) {

    this.ls.login({ "username": username, "password": password }).subscribe((tkn: Token) => {
      // console.log("NEW TOKE: " + tkn.accessToken);
      localStorage.clear();
      localStorage.setItem('user_token', tkn.accessToken);

      // let my_roles = JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles'].split(',');
      // if (my_roles.includes('ROLE_ADMIN')) {
      //   this.router.navigate(['/', 'admin_home']);
      //   // console.log("IMA I ADMIN")
      // }
      // if (my_roles.includes('ROLE_USER')) {
      //   this.router.navigate(['/', 'login']);
      //   console.log("IMA I USER")
      // }
    }
    );

  }

}
