import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdminProfileDTO } from 'src/app/models/response/admin/admin-profile';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';

@Component({
  selector: 'app-admin-add-admin',
  templateUrl: './admin-add-admin.component.html',
  styleUrls: ['./admin-add-admin.component.css']
})
export class AdminAddAdminComponent implements OnInit {

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

  constructor(private ass: AdminServiceService, private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  public saveUser() {
    this.admin.email = this.profileForm.value.email;
    this.admin.username = this.profileForm.value.username;
    this.admin.password = this.profileForm.value.password;
    this.admin.firstName = this.profileForm.value.firstName;
    this.admin.lastName = this.profileForm.value.lastName;


    this.ass.saveAdmin(this.admin).subscribe((bln: Boolean) => {


      var millisecondsToWait = 1000;
      setTimeout(() => {

        this.ngOnInit();
      }, millisecondsToWait);


    }
    );
  }

}
