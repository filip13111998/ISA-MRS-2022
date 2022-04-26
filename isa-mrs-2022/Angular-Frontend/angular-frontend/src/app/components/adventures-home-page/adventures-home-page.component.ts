import { AdventureserviceService } from './../../services/adventureService/adventureservice.service';
import { AdventureDTO } from './../../models/response/http-adventure-response/adventure-dto';
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

  adList: AdventureDTO[] = [];

  pageNum: number = 0;

  constructor(private as: AdventureserviceService) { }

  ngOnInit(): void {
    this.getAllAdventure();
  }
  public getAllAdventure() {
    this.as.getAllAdventures(this.pageNum).subscribe((adto: AdventureDTO[]) => {
      console.log(adto);
      this.adList = adto;
    }
    );

  }

  public getImageName(name: string): String {
    return "assets/" + name;
  }

  public pagePlus() {
    if (this.adList.length != 9) {
      return;
    }
    this.pageNum += 1;
    this.getAllAdventure();
    if (this.adList.length != 9) {
      this.pageNum -= 1;
      this.getAllAdventure();
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.getAllAdventure();
  }

}
