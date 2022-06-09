import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ValidationAccountTokenDTO } from 'src/app/models/register/IsValid';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-validate-user',
  templateUrl: './validate-user.component.html',
  styleUrls: ['./validate-user.component.css']
})
export class ValidateUserComponent implements OnInit {

  validate_token: string = "";

  validateForm = this.fb.group({
    token: [null],
  })

  constructor(private ls: LoginService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {

    this.getValidateToken();
  }



  public getValidateToken() {
    var mytkn = localStorage.getItem('user_token');
    var myUsername = "";
    if (mytkn != null) {
      myUsername = JSON.parse(atob(mytkn.split('.')[1]))['sub'];
    }

    this.ls.isValid(myUsername).subscribe((vat: ValidationAccountTokenDTO) => {
      console.log("BACK TOKEN JE " + vat.token);
      this.validate_token = vat.token;
    });
  }

  public activate() {

    var mytkn = localStorage.getItem('user_token');
    var myUsername = "";
    if (mytkn != null) {
      myUsername = JSON.parse(atob(mytkn.split('.')[1]))['sub'];
    }

    if (this.validate_token == this.validateForm.value.token) {
      this.ls.validate(myUsername).subscribe((b: Boolean) => {
        console.log("USPESNO VALIDIRANJE? " + b);



        this.router.navigate(['/', 'register_home']);
      });
    }


  }

}
