import { MyUserDTO } from './../../models/response/my-user/my-user';
import { MyUserService } from './../../services/my-user-service/my-user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-my-user-profile',
  templateUrl: './my-user-profile.component.html',
  styleUrls: ['./my-user-profile.component.css']
})
export class MyUserProfileComponent implements OnInit {

  profileForm = this.fb.group({
    email: [null],
    password: [null],
    password2: [null],
    firstName: [null],
    lastName: [null],
  })

  myUser: MyUserDTO = new MyUserDTO;

  constructor(private mss: MyUserService, private fb: FormBuilder) { }

  ngOnInit(): void {
    var path = window.location.href;
    var username = path.split("/")[path.split("/").length - 1];
    this.getUser(username);
    this.profileForm.get("email")?.disable;
  }

  public editUser() {

    if (this.profileForm.value.password != this.profileForm.value.password2) {
      console.log("DIFFERENT PASSWORD");
      return;
    }

    this.myUser.firstName = this.profileForm.value.firstName;
    this.myUser.lastName = this.profileForm.value.lastName;
    this.myUser.email = this.profileForm.value.email;
    this.myUser.password = this.profileForm.value.password;

    this.mss.editMyUser(this.myUser).subscribe((bln: Boolean) => {
      console.log(bln);

    }
    );
  }

  public getUser(username: string) {
    this.mss.getMyUser(username).subscribe((mus: MyUserDTO) => {
      console.log(mus);
      this.myUser = mus;
    }
    );



  }

}
