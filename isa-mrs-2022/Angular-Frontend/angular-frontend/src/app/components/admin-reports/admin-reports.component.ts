import { ReportSearhDTO } from './../../models/report/report-search';
import { ReportDTO } from './../../models/report/report';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';

@Component({
  selector: 'app-admin-reports',
  templateUrl: './admin-reports.component.html',
  styleUrls: ['./admin-reports.component.css']
})
export class AdminReportsComponent implements OnInit {

  tkn: any;

  username: string = "";

  search: ReportSearhDTO = new ReportSearhDTO();

  report: ReportDTO = new ReportDTO();

  searchForm = this.fb.group({
    start: [null],
    end: [null],


  });

  reportForm = this.fb.group({
    numberCottageReservation: [null],
    numberBoatReservation: [null],
    numberAdventureReservation: [null],
    priceCottageReservation: [null],
    priceBoatReservation: [null],
    priceAdventureReservation: [null],
    sum: [null],
    appProfit: [null],
    finishSum: [null],
  });

  constructor(private ass: AdminServiceService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchReq();
  }


  public searchReq() {
    this.search.start = this.searchForm.value.start;
    this.search.end = this.searchForm.value.end;



    this.ass.report(this.search).subscribe((r: ReportDTO) => {

      this.report = r

    }
    );
  }


}
