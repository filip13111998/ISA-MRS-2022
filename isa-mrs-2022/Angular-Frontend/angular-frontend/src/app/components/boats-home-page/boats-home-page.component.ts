import { Component, OnInit } from '@angular/core';
import { BoatComboBox } from 'src/app/models/combo-home-page/boat-combo-box';

@Component({
  selector: 'app-boats-home-page',
  templateUrl: './boats-home-page.component.html',
  styleUrls: ['./boats-home-page.component.css']
})
export class BoatsHomePageComponent implements OnInit {
  boats: BoatComboBox[] = [
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
