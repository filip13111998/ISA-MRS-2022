import { CottageComplaintDTO } from './../../models/response/http-cottage-response/cottage-complaint';
import { AdventureMarkDTO } from './../../models/response/http-adventure-response/adventure-mark';
import { AdventureComplaintDTO } from './../../models/response/http-adventure-response/adventure-complaint';
import { BoatComplaintDTO } from './../../models/response/http-boat-response/boat-complaint';
import { CottageMarkDTO } from './../../models/response/http-cottage-response/cottage-mark';
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
import { FormBuilder } from '@angular/forms';
import { BoatMarkDTO } from 'src/app/models/response/http-boat-response/boat-mark';

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

  subtypes: CottageComboBox[] = [

    { value: 'date', viewValue: 'Date Start' },
    { value: 'price', viewValue: 'Price' },
  ];

  displayStyle = "none";

  answer: any;

  cottageMarkForm = this.fb.group({

    answer: [null],
  });

  boatMarkForm = this.fb.group({

    answer: [null],
  });

  adventureMarkForm = this.fb.group({

    answer: [null],
  });

  cottageComplaintForm = this.fb.group({

    answer: [null],
  });

  boatComplaintForm = this.fb.group({

    answer: [null],
  });

  adventureComplaintForm = this.fb.group({

    answer: [null],
  });


  cottageIdSave: number;

  constructor(private ass: AdventureserviceService, private bss: BoatserviceService, private css: CottageServiceService, private fb: FormBuilder, private mus: MyUserService, private router: Router, private cts: CottageReservationService, private bts: BoatReservationService, private ats: AdventureReservationService) {

  }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getCottages();
    this.getBoats();
    this.getAdventures();
  }



  openPopup() {
    this.displayStyle = "block";
    console.log("USO" + this.displayStyle);
  }
  closePopup() {
    this.displayStyle = "none";
  }

  saveCtgId(ids: any) {
    this.cottageIdSave = ids;
  }


  public cottageMark(id: any) {

    this.displayStyle = "none";

    var cm = new CottageMarkDTO();
    cm.date = new Date();
    cm.entityID = this.cottageIdSave;
    cm.mark = this.cottageMarkForm.value.answer;
    cm.username = this.username + "";
    this.css.addMark(cm).subscribe((b: Boolean) => {
      if (b) {

        console.log("COTTAGE MARK " + b);
      }

    });

  }

  public boatMark(id: any) {
    this.displayStyle = "none";

    var bm = new BoatMarkDTO();
    bm.date = new Date();
    bm.entityID = this.cottageIdSave;
    bm.mark = this.boatMarkForm.value.answer;
    bm.username = this.username + "";
    this.bss.addMark(bm).subscribe((b: Boolean) => {
      if (b) {

        console.log("BOAT MARK " + b);
      }

    });


  }

  public adventureMark(id: any) {
    this.displayStyle = "none";
    var am = new AdventureMarkDTO();
    am.date = new Date();
    am.entityID = this.cottageIdSave;
    am.mark = this.adventureMarkForm.value.answer;
    am.username = this.username + "";
    this.ass.addMark(am).subscribe((b: Boolean) => {
      if (b) {

        console.log("ADVENTURE MARK " + b);
      }

    });


  }

  public cottageComplaint(id: any) {
    this.displayStyle = "none";
    console.log(this.cottageIdSave);
    var cm = new CottageComplaintDTO();
    cm.date = new Date();
    cm.entityID = this.cottageIdSave;
    cm.description = this.cottageComplaintForm.value.answer;
    this.css.addComplaint(cm).subscribe((b: Boolean) => {
      if (b) {
        console.log("COTTAGE COMPLAINT " + b);
      }

    });


  }

  public boatComplaint(id: any) {
    this.displayStyle = "none";
    var bm = new BoatComplaintDTO();
    bm.date = new Date();
    bm.entityID = id;
    bm.description = this.boatComplaintForm.value.answer;
    this.bss.addComplaint(bm).subscribe((b: Boolean) => {
      if (b) {

        console.log("BOAT COMPLAINT " + b);
      }

    });

  }

  public adventureComplaint(id: any) {
    this.displayStyle = "none";

    var am = new AdventureComplaintDTO();
    am.date = new Date();
    am.entityID = id;
    am.description = this.adventureComplaintForm.value.answer;
    this.ass.addComplaint(am).subscribe((b: Boolean) => {
      if (b) {
        console.log("ADVENTURE COMPLAINT " + b);
      }

    });


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

  public checkCottageMarkComplaint(id: number): Boolean {
    var condition: Boolean = false;
    this.cottageHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationEnd);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return condition;
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

  public checkBoatMarkComplaint(id: number): Boolean {
    var condition: Boolean = false;
    this.boatHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationEnd);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return condition;
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


  public checkAdventureIdMarkComplaint(id: number): Boolean {
    var condition: Boolean = false;
    this.adventureHistoryReservation.forEach(element => {

      if (element.id === id) {
        var dt_now = new Date();
        var dt_cot = new Date(element.reservationEnd);
        // dt_cot.setDate(dt_cot.getDate() - 3);
        condition = moment(dt_cot).isAfter(dt_now);

      }
    });
    return condition;
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

  public setSortSubType(event: any) {
    if (event == "date") {
      console.log("DATE SORT");
      this.cottageHistoryReservation = [...this.cottageHistoryReservation.sort(this.compareDate)];
      this.boatHistoryReservation = [...this.boatHistoryReservation.sort(this.compareDateBoat)];
      this.adventureHistoryReservation = [...this.adventureHistoryReservation.sort(this.compareDateAdventure)];
    }
    if (event == "price") {
      console.log("PRICE SORT");
      this.cottageHistoryReservation = [...this.cottageHistoryReservation.sort(this.comparePriceCottage)];
      this.boatHistoryReservation = [...this.boatHistoryReservation.sort(this.comparePriceBoat)];
      this.adventureHistoryReservation = [...this.adventureHistoryReservation.sort(this.comparePriceAdventure)];
    }
  }

  public comparePriceCottage(o1: CottageReservationHistoryDTO, o2: CottageReservationHistoryDTO) {
    if (o1.price < o2.price) {
      return -1;
    }
    if (o1.price > o2.price) {
      return 1;
    }
    return 0;
  }

  public comparePriceBoat(o1: BoatReservationHistoryDTO, o2: BoatReservationHistoryDTO) {
    if (o1.price < o2.price) {
      return -1;
    }
    if (o1.price > o2.price) {
      return 1;
    }
    return 0;
  }

  public comparePriceAdventure(o1: AdventureReservationHistoryDTO, o2: AdventureReservationHistoryDTO) {
    if (o1.price < o2.price) {
      return -1;
    }
    if (o1.price > o2.price) {
      return 1;
    }
    return 0;
  }


  public compareDate(d1: CottageReservationHistoryDTO, d2: CottageReservationHistoryDTO) {
    if (moment(d1.reservationEnd) < moment(d2.reservationStart)) {
      return -1;
    }
    if (moment(d1.reservationStart) > moment(d2.reservationStart)) {
      return 1;
    }
    return 0;
  }

  public compareDateBoat(d1: BoatReservationHistoryDTO, d2: BoatReservationHistoryDTO) {
    if (moment(d1.reservationEnd) < moment(d2.reservationStart)) {
      return -1;
    }
    if (moment(d1.reservationStart) > moment(d2.reservationStart)) {
      return 1;
    }
    return 0;
  }

  public compareDateAdventure(d1: AdventureReservationHistoryDTO, d2: AdventureReservationHistoryDTO) {
    if (moment(d1.reservationEnd) < moment(d2.reservationStart)) {
      return -1;
    }
    if (moment(d1.reservationStart) > moment(d2.reservationStart)) {
      return 1;
    }
    return 0;
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
