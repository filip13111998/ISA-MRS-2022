import { AdventureComboBox } from 'src/app/models/combo-home-page/adventure-combo-box';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-adventures-home-page',
  templateUrl: './adventures-home-page.component.html',
  styleUrls: ['./adventures-home-page.component.css']
})
export class AdventuresHomePageComponent implements OnInit {

  adventures: AdventureComboBox[] = [
    { value: 'steak-0', viewValue: 'Name' },
    { value: 'pizza-1', viewValue: 'Address' },
    { value: 'tacos-2', viewValue: 'Room Number' },
    { value: 'tacos-2', viewValue: 'Bed Number' },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
interface Cottage {
  value: string;
  viewValue: string;
}
