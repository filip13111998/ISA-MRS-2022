import { BoatserviceService } from './../../services/boatService/boatservice.service';
import { BoatDTO } from './../../models/response/http-boat-response/boat-dto';
import { Component, OnInit } from '@angular/core';
import { BoatComboBox } from 'src/app/models/combo-home-page/boat-combo-box';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-boats-home-page',
  templateUrl: './boats-home-page.component.html',
  styleUrls: ['./boats-home-page.component.css']
})
export class BoatsHomePageComponent implements OnInit {

  boats: BoatComboBox[] = [
    { value: 'name', viewValue: 'Name' },
    { value: 'type', viewValue: 'Type' },
    { value: 'address', viewValue: 'Address' },
    { value: 'lenght', viewValue: 'Lenght' },
    { value: 'engine_number', viewValue: 'Engine Number' },
    { value: 'engine_power', viewValue: 'Engine Power' },
    { value: 'max_speed', viewValue: 'Max Speed' },

  ];

  profileForm = this.fb.group({
    name: [null],
    type: [null],
    address: [null],
    lenghtFrom: [null],
    lenghtTo: [null],
    engineNumFrom: [null],
    engineNumTo: [null],
    enginePowerFrom: [null],
    enginePowerTo: [null],
    maxSpeedFrom: [null],
    maxSpeedTo: [null],
    averageMark: [null]
  })


  btList: BoatDTO[] = [];

  pageNum: number = 0;

  sortType: String = "name";

  constructor(private bs: BoatserviceService, private fb: FormBuilder) { }

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


  public setSortType(event: any) {
    if (event === "name") {
      this.sortType = "name";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );

    } else if (event === "address") {
      this.sortType = "address";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );

    }
    else if (event === "type") {
      this.sortType = "type";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );

    }
    else if (event === "lenght") {
      this.sortType = "lenght";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );
    }
    else if (event === "engine_number") {
      this.sortType = "engineNum";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );
    }
    else if (event === "engine_power") {
      this.sortType = "enginePower";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );
    }
    else if (event === "max_speed") {
      this.sortType = "maxSpeed";
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );


    }

  }

  public resetForm() {

    if (this.profileForm.value.name == "") {
      this.profileForm.value.name = null;
    }
    if (this.profileForm.value.address == "") {
      this.profileForm.value.address = null;
    }
    if (this.profileForm.value.type == "") {
      this.profileForm.value.type = null;
    }
    if (this.profileForm.value.lenghtFrom == "") {
      this.profileForm.value.lenghtFrom = null;
    }
    if (this.profileForm.value.lenghtTo == "") {
      this.profileForm.value.lenghtTo = null;
    }
    if (this.profileForm.value.engineNumFrom == "") {
      this.profileForm.value.engineNumFrom = null;
    }
    if (this.profileForm.value.engineNumTo == "") {
      this.profileForm.value.engineNumTo = null;
    }
    if (this.profileForm.value.maxSpeedFrom == "") {
      this.profileForm.value.maxSpeedFrom = null;
    }
    if (this.profileForm.value.maxSpeedTo == "") {
      this.profileForm.value.maxSpeedTo = null;
    }
    if (this.profileForm.value.enginePowerFrom == "") {
      this.profileForm.value.enginePowerFrom = null;
    }
    if (this.profileForm.value.enginePowerTo == "") {
      this.profileForm.value.enginePowerTo = null;
    }
    if (this.profileForm.value.averageMark == "") {
      this.profileForm.value.averageMark = null;
    }

  }

  public filterBoat() {
    this.resetForm();
    if (this.profileForm.valid) {
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        // console.log(cdto);
        this.btList = bdto;
      }
      );
    }

  }

  public goProfile(id: number): any {
    return ['/profileBoat', id];
  }

  public getImageName(name: string): String {
    return "assets/" + name;
  }

  public pagePlus() {
    if (this.btList.length != 9) {
      return;
    }
    this.pageNum += 1;
    this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
      console.log(bdto);
      this.btList = bdto;
    }
    );
    if (this.btList.length != 9) {
      this.pageNum -= 1;
      this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
        console.log(bdto);
        this.btList = bdto;
      }
      );
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.bs.filterBoat(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((bdto: BoatDTO[]) => {
      console.log(bdto);
      this.btList = bdto;
    }
    );
  }

}
