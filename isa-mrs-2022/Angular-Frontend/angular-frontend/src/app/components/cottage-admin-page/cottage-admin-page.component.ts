import { AdminServiceService } from './../../services/admin/admin-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
import { CottageDTO } from 'src/app/models/response/http-cottage-response/cottage-dto';
import { CottageServiceService } from 'src/app/services/cottageService/cottage-service.service';
@Component({
  selector: 'app-cottage-admin-page',
  templateUrl: './cottage-admin-page.component.html',
  styleUrls: ['./cottage-admin-page.component.css']
})
export class CottageAdminPageComponent implements OnInit {

  cottages: CottageComboBox[] = [
    { value: 'name', viewValue: 'Name' },
    { value: 'address', viewValue: 'Address' },
    { value: 'room_number', viewValue: 'Room Number' },
    { value: 'bed_number', viewValue: 'Bed Number' },
  ];

  profileForm = this.fb.group({
    name: [null],
    address: [null],
    numberOfRoom: [null],
    averageMark: [null],
    start: [null],
    end: [null]
  })

  cttList: CottageDTO[] = [];

  pageNum: number = 0;

  sortType: String = "name";

  constructor(private css: CottageServiceService, private fb: FormBuilder, private ass: AdminServiceService) {
  }

  ngOnInit() {
    this.getAllCottage();
  }

  public setSortType(event: any) {
    if (event === "name") {
      this.sortType = "name";
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        console.log(cdto);
        this.cttList = cdto;
      }
      );

    } else if (event === "address") {
      this.sortType = "address";
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        console.log(cdto);
        this.cttList = cdto;
      }
      );

    }
    else if (event === "room_number") {
      this.sortType = "numberOfRoom";
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        console.log(cdto);
        this.cttList = cdto;
      }
      );

    }
    else if (event === "bed_number") {
      this.sortType = "numberOfBedPerRoom";
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        console.log(cdto);
        this.cttList = cdto;
      }
      );
      console.log("BED SORT");

    }
  }

  public resetForm() {

    if (this.profileForm.value.name == "") {
      this.profileForm.value.name = null;
    }
    if (this.profileForm.value.address == "") {
      this.profileForm.value.address = null;
    }
    if (this.profileForm.value.numberOfRoom == "") {
      this.profileForm.value.numberOfRoom = null;
    }
    if (this.profileForm.value.averageMark == "") {
      this.profileForm.value.averageMark = null;
    }

  }

  public filterCottage() {
    this.resetForm();
    if (this.profileForm.valid) {
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        // console.log(cdto);
        this.cttList = cdto;
      }
      );
    }

  }

  public getAllCottage() {
    this.css.getAllCotages(this.pageNum).subscribe((cdto: CottageDTO[]) => {
      console.log(cdto);
      this.cttList = cdto;
    }
    );

  }



  public delete(id: number): any {
    this.css.delete(id).subscribe((b: Boolean) => {
      console.log("REZULTAT JE: " + b);
      if (b == false) {
        this.ngOnInit();
        window.alert("ENTITY IS DELETED!");
      }
      this.ngOnInit();
    }
    );
  }


  public getImageName(name: string): String {
    return "assets/" + name;
  }

  public pagePlus() {
    if (this.cttList.length != 9) {
      return;
    }
    this.pageNum += 1;
    this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
      console.log(cdto);
      this.cttList = cdto;
    }
    );
    if (this.cttList.length != 9) {
      this.pageNum -= 1;
      this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
        console.log(cdto);
        this.cttList = cdto;
      }
      );
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
      console.log(cdto);
      this.cttList = cdto;
    }
    );
  }

}
