import { Token } from 'src/app/models/response/login/login-token';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdminProfileDTO } from 'src/app/models/response/admin/admin-profile';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-admin-update-profil',
  templateUrl: './admin-update-profil.component.html',
  styleUrls: ['./admin-update-profil.component.css']
})
export class AdminUpdateProfilComponent implements OnInit {

  tkn: any;

  username: string = "";

  admin: AdminProfileDTO = new AdminProfileDTO();

  profileForm = this.fb.group({
    email: [null],
    username: [null],
    password: [null],
    firstName: [null],
    lastName: [null],

  })

  constructor(private ass: AdminServiceService, private fb: FormBuilder, private ls: LoginService) { }

  ngOnInit(): void {
    this.getAdmin();
  }


  public editUser() {
    this.admin.email = this.profileForm.value.email;
    this.admin.username = this.profileForm.value.username;
    this.admin.password = this.profileForm.value.password;
    this.admin.firstName = this.profileForm.value.firstName;
    this.admin.lastName = this.profileForm.value.lastName;


    this.ass.updateAdmin(this.admin).subscribe((bln: Boolean) => {

      var millisecondsToWait = 1000;
      setTimeout(() => {
        this.automaticLogin(this.username, this.admin.password);
        // this.router.navigate(['/', 'register_profile']);
        this.ngOnInit();
      }, millisecondsToWait);

    }
    );
  }


  public getAdmin() {

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];


    this.ass.getOneAdmin(this.username).subscribe((a: AdminProfileDTO) => {

      this.admin = a;
    }
    );
  }

  public automaticLogin(username: String, password: String) {
    localStorage.clear();
    this.ls.login({ "username": username, "password": password }).subscribe((tkn: Token) => {

      localStorage.setItem('user_token', tkn.accessToken);


    }
    );
  }

}
