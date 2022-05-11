import { MyUserService } from './../../services/my-user-service/my-user.service';
import { CottageReservationHistoryDTO } from './../../models/response/http-cottage-response/cottage-history-reservation';
import { Component, OnInit } from '@angular/core';
import { BoatReservationHistoryDTO } from 'src/app/models/response/http-boat-response/boat-history-reservation';
import { AdventureReservationHistoryDTO } from 'src/app/models/response/http-adventure-response/adventure-history-reservation';
import { animate, style, transition, trigger } from '@angular/animations';
import { Router } from '@angular/router';

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

  displayedColumns: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'cottageID', 'complaints', 'mark'];
  displayedColumnsBoat: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'boatID', 'complaints', 'mark'];
  displayedColumnsAdventure: string[] = ['id', 'reservationStart', 'reservationEnd', 'pricelistItem.price', 'pricelistItem.description', 'adventureID', 'complaints', 'mark'];

  cottageHistoryReservation: CottageReservationHistoryDTO[];
  boatHistoryReservation: BoatReservationHistoryDTO[];
  adventureHistoryReservation: AdventureReservationHistoryDTO[];

  pageNum: Number = 0;

  username: String;

  tkn: any = "";


  constructor(private mus: MyUserService, private router: Router) {

  }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getCottages();
    this.getBoats();
    this.getAdventures();
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
