import { LoyalityDTO } from './../../models/response/loyality/loyality';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-admin-loyality-pag',
  templateUrl: './admin-loyality-pag.component.html',
  styleUrls: ['./admin-loyality-pag.component.css']
})
export class AdminLoyalityPagComponent implements OnInit {


  profileForm = this.fb.group({
    appProfit: [null],
    silverPercent: [null],
    goldPercent: [null],
    silverPoints: [null],
    goldPoints: [null],

  })

  loyality: LoyalityDTO = new LoyalityDTO();

  constructor(private ass: AdminServiceService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getLoyality();
  }

  public editLoyality() {
    this.loyality.appProfit = this.profileForm.value.appProfit;
    this.loyality.silverPercent = this.profileForm.value.silverPercent;
    // this.myUser.email = this.profileForm.value.email;
    this.loyality.goldPercent = this.profileForm.value.goldPercent;

    // this.myUser.point = this.profileForm.value.point;
    this.loyality.silverPoints = this.profileForm.value.silverPoints;
    this.loyality.goldPoints = this.profileForm.value.goldPoints;

    this.ass.saveLoyality(this.loyality).subscribe((bln: Boolean) => {

      this.ngOnInit();

    }
    );
  }


  public getLoyality() {
    this.ass.loyality().subscribe((l: LoyalityDTO) => {
      console.log(l);
      this.loyality = l;
    }
    );
  }

}
