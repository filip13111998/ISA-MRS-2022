import { UserDeleteDTO } from 'src/app/models/response/admin/user-delete';
import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-delete-user',
  templateUrl: './admin-delete-user.component.html',
  styleUrls: ['./admin-delete-user.component.css']
})
export class AdminDeleteUserComponent implements OnInit {


  displayedColumns: string[] = ['id', 'email', 'username', 'firstName', 'lastName', 'description', 'Answer'];

  deletedUsers: UserDeleteDTO[];

  username: String;

  constructor(private ass: AdminServiceService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.findAllDeletedUsers();

  }

  public remember(username: string) {
    this.username = username;
  }

  public findAllDeletedUsers() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.findAllDeletedUsers().subscribe((usrs: UserDeleteDTO[]) => {
      this.deletedUsers = usrs;
    });

  }




  public acceptCottageMark() {
    this.ass.acceptDeleteUser(this.username + "").subscribe((b: Boolean) => {
      this.ngOnInit();
    });

  }


  public declineCottageMark() {
    this.ass.declineDeleteUser(this.username + "").subscribe((b: Boolean) => {
      this.ngOnInit();
    })
  }



}
