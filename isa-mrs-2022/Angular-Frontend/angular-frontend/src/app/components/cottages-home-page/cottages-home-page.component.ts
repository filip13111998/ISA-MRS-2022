import { CottageDTO } from './../../models/response/http-cottage-response/cottage-dto';
import { CottageServiceService } from './../../services/cottage-service.service';
import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
import { DomSanitizer } from '@angular/platform-browser';
import { AmdDependency } from 'typescript';
@Component({
  selector: 'app-cottages-home-page',
  templateUrl: './cottages-home-page.component.html',
  styleUrls: ['./cottages-home-page.component.css']
})
export class CottagesHomePageComponent implements OnInit {
  cottages: CottageComboBox[] = [
    { value: 'name', viewValue: 'Name' },
    { value: 'address', viewValue: 'Address' },
    { value: 'room_number', viewValue: 'Room Number' },
    { value: 'bed_number', viewValue: 'Bed Number' },
  ];

  cttList: CottageDTO[] = [];

  pageNum: number = 0;

  constructor(private css: CottageServiceService, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.getAllCottage();
  }


  public getAllCottage() {
    console.log("dsa");
    this.css.getAllCotages(this.pageNum).subscribe((cdto: CottageDTO[]) => {
      console.log(cdto);
      this.cttList = cdto;
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
    this.getAllCottage();
    if (this.cttList.length != 9) {
      this.pageNum -= 1;
      this.getAllCottage();
    }
  }

  public pageMinus() {
    if (this.pageNum == 0) {
      return;
    }
    this.pageNum -= 1;
    this.getAllCottage();
  }


}




