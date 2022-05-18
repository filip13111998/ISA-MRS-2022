import { DeleteCottageReservationDTO } from './../../models/response/entity-delete/cottage-delete';
import { AdventureserviceService } from './../../services/adventureService/adventureservice.service';
import { BoatserviceService } from './../../services/boatService/boatservice.service';
import { CottageServiceService } from './../../services/cottageService/cottage-service.service';
import { MyUserService } from './../../services/my-user-service/my-user.service';
import { CottageReservationHistoryDTO } from './../../models/response/http-cottage-response/cottage-history-reservation';
import { Component, OnInit } from '@angular/core';
import { BoatReservationHistoryDTO } from 'src/app/models/response/http-boat-response/boat-history-reservation';
import { AdventureReservationHistoryDTO } from 'src/app/models/response/http-adventure-response/adventure-history-reservation';
import { animate, style, transition, trigger } from '@angular/animations';
import { Router } from '@angular/router';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
import * as moment from 'moment';
import { CottageReservationService } from 'src/app/services/cottage-reservation/cottage-reservation.service';
import { DeleteBoatReservationDTO } from 'src/app/models/response/entity-delete/boat-delete';
import { BoatReservationService } from 'src/app/services/boat-reservation/boat-reservation.service';
import { AdventureReservationService } from 'src/app/services/adventure-reservation/adventure-reservation.service';
import { DeleteAdventureReservationDTO } from 'src/app/models/response/entity-delete/adventure-delete';

@Component({
  selector: 'app-register-user-history-reservation',
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [   // :enter is alias to 'void => *'
        style({ opacity: 0 }),
        animate(500, style({ opacity: 1 }))
      ]),
      transition(':leave', [   // :leave is alias to '* => void'
        animate(500, style({ opacity: 0 }))
      ])
    ])
  ],
  templateUrl: './register-user-history-reservation.component.html',
  styleUrls: ['./register-user-history-reservation.component.css']
})
export class RegisterUserHistoryReservationComponent implements OnInit {

  entity_type: Number;

  displayedColumns: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'cottageID', 'complaints', 'mark', 'cancel'];
  displayedColumnsBoat: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'boatID', 'complaints', 'mark', 'cancel'];
  displayedColumnsAdventure: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'adventureID', 'complaints', 'mark', 'cancel'];

  cottageHistoryReservation: CottageReservationHistoryDTO[];
  boatHistoryReservation: BoatReservationHistoryDTO[];
  adventureHistoryReservation: AdventureReservationHistoryDTO[];

  pageNum: Number = 0;

  username: String;

  tkn: any = "";

  types: CottageComboBox[] = [
    { value: 'all', viewValue: 'All' },
    { value: 'not_held', viewValue: 'Not Held' },

  ];

  constructor(private mus: MyUserService, private router: Router, private cts: CottageReservationService, private bts: BoatReservationService, private ats: AdventureReservationService) {

  }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getCottages();
    this.getBoats();
    this.getAdventures();
  }

  public delete_cottage(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];
    this.cottageHistoryReservation.forEach(element => {

      if (element.id === id) {

        var dto = new DeleteCottageReservationDTO();

        dto.cottageId = element.cottageID;
        dto.reservationId = element.id;
        dto.myUsername = this.username + "";

        this.cts.deleteCotage(dto).subscribe((res: Boolean) => {
          this.getCottages();
        });

      }
    });

  }

  public delete_boat(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.boatHistoryReservation.forEach(element => {

      if (element.id === id) {

        var dto = new DeleteBoatReservationDTO();

        dto.boatId = element.boatID;
        dto.reservationId = element.id;
        dto.myUsername = this.username + "";

        this.bts.deleteBoat(dto).subscribe((res: Boolean) => {
          console.log(res);
          this.getBoats();
        });

      }
    });

  }

  public delete_adventure(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.adventureHistoryReservation.forEach(element => {

      if (element.id === id) {

        var dto = new DeleteAdventureReservationDTO();

        dto.adventureId = element.adventureID;
        dto.reservationId = element.id;
        dto.myUsername = this.username + "";

        this.ats.deleteAdventure(dto).subscribe((res: Boolean) => {
          this.getAdventures();
        });

      }
    });

  }

  public checkCottageId(id: number): Boolean {
    var condition: Boolean = false;
    this.cottageHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationStart);
        dt_cot.setDate(dt_cot.getDate() - 3);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return !condition;
  }

  public checkBoatId(id: number): Boolean {
    var condition: Boolean = false;
    this.boatHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationStart);
        dt_cot.setDate(dt_cot.getDate() - 3);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return !condition;
  }

  public checkAdventureId(id: number): Boolean {
    var condition: Boolean = false;
    this.adventureHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationStart);
        dt_cot.setDate(dt_cot.getDate() - 3);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return !condition;
  }

  public setSortType(event: any) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];
    console.log(event);
    if (event === "all") {

      //get all cottage
      this.mus.getCottageHistoryReservation(this.username, this.pageNum).subscribe((cpdto: CottageReservationHistoryDTO[]) => {
        this.cottageHistoryReservation = cpdto;

      });

      //get all boats
      this.mus.getBoatHistoryReservation(this.username, this.pageNum).subscribe((bpdto: BoatReservationHistoryDTO[]) => {
        this.boatHistoryReservation = bpdto;

      });

      //get all adventure
      this.mus.getAdventureHistoryReservation(this.username, this.pageNum).subscribe((apdto: AdventureReservationHistoryDTO[]) => {
        this.adventureHistoryReservation = apdto;
      });
    }
    else if (event == "not_held") {
      // console.log("USO NOT HELD");
      //get not held cottage
      this.mus.getCottageNotHeldReservation(this.username, this.pageNum).subscribe((cpdto: CottageReservationHistoryDTO[]) => {
        this.cottageHistoryReservation = cpdto;
        console.log(cpdto);
      });

      //get not held boat
      this.mus.getBoatNotHeldReservation(this.username, this.pageNum).subscribe((cpdto: BoatReservationHistoryDTO[]) => {
        this.boatHistoryReservation = cpdto;

      });

      //get not held adventure
      this.mus.getAdventureNotHeldReservation(this.username, this.pageNum).subscribe((cpdto: AdventureReservationHistoryDTO[]) => {
        this.adventureHistoryReservation = cpdto;

      });
    }

    // if (event === "all") {
    //   this.css.getPricelist(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
    //     console.log(cdto);
    //     this.cttList = cdto;
    //   }
    //   );

    // } else if (event === "not_held") {
    //   this.sortType = "not_held";
    //   this.css.filterCottage(this.pageNum, this.sortType, true, this.profileForm.value).subscribe((cdto: CottageDTO[]) => {
    //     console.log(cdto);
    //     this.cttList = cdto;
    //   }
    //   );

    // }

  }





  public getCottages() {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.mus.getCottageHistoryReservation(this.username, this.pageNum).subscribe((cpdto: CottageReservationHistoryDTO[]) => {
      this.cottageHistoryReservation = cpdto;

    });

  }

  public getBoats() {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.mus.getBoatHistoryReservation(this.username, this.pageNum).subscribe((bpdto: BoatReservationHistoryDTO[]) => {
      this.boatHistoryReservation = bpdto;

    });

  }

  public getAdventures() {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.mus.getAdventureHistoryReservation(this.username, this.pageNum).subscribe((apdto: AdventureReservationHistoryDTO[]) => {
      this.adventureHistoryReservation = apdto;
    });

  }

  public set_entity_type_cottage(): void {
    this.entity_type = 0;
  }
  public set_entity_type_boat(): void {
    this.entity_type = 1;
  }
  public set_entity_type_adventure(): void {
    this.entity_type = 2;
  }
  public set_entity_type_instructor(): void {

  }

}
