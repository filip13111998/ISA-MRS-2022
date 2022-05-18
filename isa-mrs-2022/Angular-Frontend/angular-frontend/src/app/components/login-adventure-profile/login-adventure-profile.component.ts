import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-adventure-profile',
  templateUrl: './login-adventure-profile.component.html',
  styleUrls: ['./login-adventure-profile.component.css']
})
export class LoginAdventureProfileComponent implements OnInit {

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
  } public set_subscribe_entity(): void {
    this.menu_page = 3;
  }
  public set_actions_page(): void {
    this.menu_page = 4;
  }




  public logout() {
    localStorage.clear();
  }

}
