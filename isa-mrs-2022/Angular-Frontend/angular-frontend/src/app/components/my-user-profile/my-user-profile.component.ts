import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service/login.service';
import { MyUserDTO } from './../../models/response/my-user/my-user';
import { MyUserService } from './../../services/my-user-service/my-user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { Token } from 'src/app/models/response/login/login-token';
import { DeleteRequestDTO } from 'src/app/models/response/my-user/my-user-delete';



@Component({
  selector: 'app-my-user-profile',
  templateUrl: './my-user-profile.component.html',
  styleUrls: ['./my-user-profile.component.css']
})
export class MyUserProfileComponent implements OnInit {

  username: String;

  tkn: any = "";


  // reason: any;
  displayStyle = "none";

  profileForm = this.fb.group({
    email: [null],
    password: [null],
    password2: [null],
    firstName: [null],
    lastName: [null],
    userCategory: [null],
    point: [null],
    penaltyPoint: [null],
    phoneNumber: [null],
    adresa: [null],
    grad: [null],
    drzava: [null],
  })

  deleteForm = this.fb.group({

    reason: [null],
  })

  reason: any;

  myUser: MyUserDTO = new MyUserDTO;

  constructor(private mss: MyUserService, private fb: FormBuilder, private ls: LoginService, private router: Router) { }

  ngOnInit(): void {
    // var path = window.location.href;
    // var username = path.split("/")[path.split("/").length - 1];
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];
    this.getUser();
    // this.profileForm.get("email")?.disable;
  }





  public editUser() {

    if (this.profileForm.value.password != this.profileForm.value.password2) {
      console.log("DIFFERENT PASSWORD");
      return;
    }

    this.myUser.firstName = this.profileForm.value.firstName;
    this.myUser.lastName = this.profileForm.value.lastName;
    // this.myUser.email = this.profileForm.value.email;
    this.myUser.password = this.profileForm.value.password;

    // this.myUser.point = this.profileForm.value.point;
    this.myUser.phoneNumber = this.profileForm.value.phoneNumber;
    this.myUser.adresa = this.profileForm.value.adresa;
    this.myUser.grad = this.profileForm.value.grad;
    this.myUser.drzava = this.profileForm.value.drzava;

    this.mss.editMyUser(this.myUser).subscribe((bln: Boolean) => {

      var millisecondsToWait = 1000;
      setTimeout(() => {
        this.automaticLogin(this.username, this.myUser.password);
        // this.router.navigate(['/', 'register_profile']);
        this.ngOnInit();
      }, millisecondsToWait);

      console.log("HAHAHA");
      // this.automaticLogin(this.username, this.myUser.password);
      // this.automaticLogin(this.username, this.myUser.password);

    }
    );
    // this.automaticLogin(this.username, this.myUser.password);
  }

  public getUser() {
    this.mss.getMyUser(this.username).subscribe((mus: MyUserDTO) => {
      console.log(mus);
      this.myUser = mus;
      // this.tkn = localStorage.getItem('user_token');
      // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];
    }
    );



  }



  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }



  public deleteAccount() {
    // $('#myModal').on('shown.bs.modal', function () {
    //   $('#myInput').trigger('focus')
    // })
    this.displayStyle = "none";

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dlt = new DeleteRequestDTO();
    dlt.username = this.username;
    dlt.description = this.reason;
    console.log(this.reason + "OPC");
    this.mss.deleteMyUser(dlt).subscribe((b: Boolean) => {
      if (b) {
        console.log("NALOG OBRISAN");
        localStorage.clear();
        this.router.navigate(['/', 'login']);
      }

    });
    console.log("DELETE");
  }


  public automaticLogin(username: String, password: String) {
    localStorage.clear();
    this.ls.login({ "username": username, "password": password }).subscribe((tkn: Token) => {
      console.log("Brisanje tokena");

      if (localStorage.getItem('user_token') == null) {
        console.log("OBRISO");
      }
      localStorage.setItem('user_token', tkn.accessToken);

      let my_roles = JSON.parse(atob(tkn.accessToken.split('.')[1]))['roles'].split(',');
      // if (my_roles.includes('ROLE_ADMIN')) {
      //   this.router.navigate(['/', 'admin_home']);
      //   // console.log("IMA I ADMIN")
      // }
      if (my_roles.includes('ROLE_USER')) {
        // this.router.navigate(['/', 'login']);
        console.log("IMA I USER")
      }
    }
    );

  }

}
