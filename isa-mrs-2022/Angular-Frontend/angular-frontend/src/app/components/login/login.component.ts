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

  constructor(private ls: LoginService, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  public login() {
    console.log(this.loginForm.value)
    this.ls.login(this.loginForm.value).subscribe((tkn: Token) => {
      console.log(tkn);
      this.token = tkn;
    }
    );

  }

}
