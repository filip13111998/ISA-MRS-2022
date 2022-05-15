import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-boat-profile',
  templateUrl: './login-boat-profile.component.html',
  styleUrls: ['./login-boat-profile.component.css']
})
export class LoginBoatProfileComponent implements OnInit {

  entity_type: Number;

  menu_page: Number;



  constructor() {
    this.set_home_page();
    // this.set_entity_type_cottage();
  }

  ngOnInit(): void {
  }

  public set_home_page(): void {
    this.menu_page = 0;
  }
  public set_history_page(): void {
    this.menu_page = 1;
  }
  public set_profile_page(): void {
    this.menu_page = 2;
  }




  public logout() {
    localStorage.clear();
  }

}
