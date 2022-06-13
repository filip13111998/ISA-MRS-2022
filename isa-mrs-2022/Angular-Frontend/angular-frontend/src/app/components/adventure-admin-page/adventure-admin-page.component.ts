import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdventureComboBox } from 'src/app/models/combo-home-page/adventure-combo-box';
import { AdventureDTO } from 'src/app/models/response/http-adventure-response/adventure-dto';
import { AdventureserviceService } from 'src/app/services/adventureService/adventureservice.service';

@Component({
  selector: 'app-adventure-admin-page',
  templateUrl: './adventure-admin-page.component.html',
  styleUrls: ['./adventure-admin-page.component.css']
})
export class AdventureAdminPageComponent implements OnInit {

  adventures: AdventureComboBox[] = [
    { value: 'name', viewValue: 'Name' },
    { value: 'address', viewValue: 'Address' },
    { value: 'maxNumPerson', viewValue: 'Number Person' },

  ];

  profileForm = this.fb.group({
    name: [null],
    address: [null],
    averageMark: [null],
    maxNumPersonFrom: [null],
    maxNumPersonTo: [null],
    start: [null],
    end: [null]
  })


  adList: AdventureDTO[] = [];

  pageNum: number = 0;

  sortType: String = "name";

  constructor(private as: AdventureserviceService, private fb: FormBuilder) { }

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

  public setSortType(event: any) {
    if (event === "name") {
      this.sortType = "name";
      this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
        console.log(adto);
        this.adList = adto;
      }
      );

    } else if (event === "address") {
      this.sortType = "address";
      this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
        console.log(adto);
        this.adList = adto;
      }
      );

    }
    else if (event === "maxNumPerson") {
      this.sortType = "maxNumPerson";
      this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
        console.log(adto);
        this.adList = adto;
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
    if (this.profileForm.value.maxNumPersonFrom == "") {
      this.profileForm.value.maxNumPersonFrom = null;
    }
    if (this.profileForm.value.maxNumPersonTo == "") {
      this.profileForm.value.maxNumPersonTo = null;
    }
    if (this.profileForm.value.averageMark == "") {
      this.profileForm.value.averageMark = null;
    }

  }

  public filterAdventure() {
    this.resetForm();
    if (this.profileForm.valid) {
      this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
        // console.log(cdto);
        this.adList = adto;
      }
      );
    }

  }

  public delete(id: number): any {
    this.as.delete(id).subscribe((b: Boolean) => {

      this.ngOnInit();
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
    this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
      // console.log(cdto);
      this.adList = adto;
    }
    );
    if (this.adList.length != 9) {
      this.pageNum -= 1;
      this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
        // console.log(cdto);
        this.adList = adto;
      }
      );
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.as.filterAdventure(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((adto: AdventureDTO[]) => {
      // console.log(cdto);
      this.adList = adto;
    }
    );
  }
}
