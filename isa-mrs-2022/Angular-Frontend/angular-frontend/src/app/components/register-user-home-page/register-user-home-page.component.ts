import { Component, OnInit } from '@angular/core';
import { trigger, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-register-user-home-page',
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [   // :enter is alias to 'void => *'
        style({ opacity: 0 }),
        animate(500, style({ opacity: 1 }))
      ]),
      transition(':leave', [   // :leave is alias to '* => void'
        animate(500, style({ opacity: 0 }))
      ])
    ])
  ],
  templateUrl: './register-user-home-page.component.html',
  styleUrls: ['./register-user-home-page.component.css']
})
export class RegisterUserHomePageComponent implements OnInit {

  entity_type: Number;

  menu_page: Number;

  constructor() {
    this.set_home_page();
    this.set_entity_type_cottage();
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


  public set_entity_type_cottage(): void {
    this.entity_type = 0;
  }
  public set_entity_type_boat(): void {
    this.entity_type = 1;
  }
  public set_entity_type_adventure(): void {
    this.entity_type = 2;
  }
  public set_entity_type_instructor(): void {

  }



  public logout() {
    localStorage.clear();
  }

}
