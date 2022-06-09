import { Router } from '@angular/router';
import { Token } from './../../models/response/login/login-token';
import { LoginService } from './../../services/login-service/login.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ValidationAccountTokenDTO } from 'src/app/models/register/IsValid';

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

      var mytkn = localStorage.getItem('user_token');
      var myUsername = "";
      if (mytkn != null) {
        myUsername = JSON.parse(atob(mytkn.split('.')[1]))['sub'];
      }


      // var millisecondsToWait = 500;
      // setTimeout(() => {

      // }, millisecondsToWait);


      this.ls.isValid(myUsername).subscribe((vat: ValidationAccountTokenDTO) => {
        console.log("USOO");
        console.log(vat.activate);
        if (!(vat.activate == false)) {
          let my_roles = JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles'].split(',');
          if (my_roles.includes('ROLE_ADMIN')) {
            this.router.navigate(['/', 'admin_home']);
            // console.log("IMA I ADMIN")
          }
          if (my_roles.includes('ROLE_USER')) {
            this.router.navigate(['/', 'register_home']);
            // console.log("IMA I USER")
          }
        }
        else {
          this.router.navigate(['/', 'validate']);
        }

      });


      // console.log(JSON.parse(atob(tkn.token.split('.')[1])));
      // this.token = tkn;
      // this.router.navigate(['/', 'register_home']);
    }
    );

  }



}
