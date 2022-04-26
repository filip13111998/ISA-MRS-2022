import { BoatserviceService } from './../../services/boatService/boatservice.service';
import { BoatDTO } from './../../models/response/http-boat-response/boat-dto';
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

  btList: BoatDTO[] = [];

  pageNum: number = 0;

  constructor(private bs: BoatserviceService) { }

  ngOnInit(): void {
    this.getAllBoat();
  }


  public getAllBoat() {
    this.bs.getAllBoats(this.pageNum).subscribe((bdto: BoatDTO[]) => {
      console.log(bdto);
      this.btList = bdto;
    }
    );

  }

  public getImageName(name: string): String {
    return "assets/" + name;
  }

  public pagePlus() {
    if (this.btList.length != 9) {
      return;
    }
    this.pageNum += 1;
    this.getAllBoat();
    if (this.btList.length != 9) {
      this.pageNum -= 1;
      this.getAllBoat();
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.getAllBoat();
  }

}
