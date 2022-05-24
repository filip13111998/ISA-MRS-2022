import { AdminLoginDTO } from './../../models/response/admin-login/adminLogin';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';

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


  constructor(private fb: FormBuilder, private router: Router, private as: AdminServiceService) { }

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
          this.router.navigate(['/', 'admin_home']);
        }

      }
      );

    }



  }

}
