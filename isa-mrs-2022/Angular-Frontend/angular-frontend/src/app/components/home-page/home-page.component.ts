import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatGridListModule } from '@angular/material/grid-list';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {

  entity_type: Number;


  name = new FormControl('');
  radio = new FormControl('');
  checkbox = new FormGroup({
    one: new FormControl(true),
    two: new FormControl(false),
  });

  model = {
    left: true,
    middle: false,
    right: false
  };
  constructor() {
    this.set_entity_type_cottage();
  }

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
