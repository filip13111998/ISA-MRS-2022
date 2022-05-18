import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-user-actions',
  templateUrl: './my-user-actions.component.html',
  styleUrls: ['./my-user-actions.component.css']
})
export class MyUserActionsComponent implements OnInit {

  entity_type: Number;

  constructor() { }

  ngOnInit(): void {
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

}
