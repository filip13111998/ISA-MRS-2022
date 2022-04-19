import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatGridListModule } from '@angular/material/grid-list';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

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
  constructor() { }

  ngOnInit(): void {
  }

}
