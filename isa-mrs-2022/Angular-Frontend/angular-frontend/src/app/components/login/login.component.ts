import { Router } from '@angular/router';
import { Token } from './../../models/response/login/login-token';
import { LoginService } from './../../services/login-service/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    username: [null],
    password: [null]

  })

  token: Token;

  constructor(private ls: LoginService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
  }

  public login() {
    // console.log(this.loginForm.value)
    this.ls.login(this.loginForm.value).subscribe((tkn: Token) => {
      // console.log(tkn);
      // console.log(JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles']);
      localStorage.setItem('user_token', tkn.accessToken)

      let my_roles = JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles'].split(',');
      if (my_roles.includes('ROLE_ADMIN')) {
        this.router.navigate(['/', 'admin_home']);
        // console.log("IMA I ADMIN")
      }
      if (my_roles.includes('ROLE_USER')) {
        this.router.navigate(['/', 'register_home']);
        // console.log("IMA I USER")
      }
      // console.log(JSON.parse(atob(tkn.token.split('.')[1])));
      this.token = tkn;
      // this.router.navigate(['/', 'register_home']);
    }
    );

  }

}
