import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ExtendMarkAdminDTO } from 'src/app/models/response/admin/extended-mark';
import { MarkAdminDTO } from 'src/app/models/response/admin/mark-admin';
import { animate, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-admin-marks',
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
  templateUrl: './admin-marks.component.html',
  styleUrls: ['./admin-marks.component.css']
})
export class AdminMarksComponent implements OnInit {

  entity_type: Number;

  displayedColumns: string[] = ['id', 'entityID', 'entityName', 'date', 'mark', 'Answer'];
  displayedColumnsBoat: string[] = ['id', 'entityID', 'entityName', 'date', 'mark', 'Answer'];
  displayedColumnsAdventure: string[] = ['id', 'entityID', 'entityName', 'date', 'mark', 'Answer'];

  cottageMarks: ExtendMarkAdminDTO[];
  boatMarks: ExtendMarkAdminDTO[];
  adventureMarks: ExtendMarkAdminDTO[];

  username: String;

  tkn: any = "";

  id: number;

  entityID: number;



  constructor(private ass: AdminServiceService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getAllCottageMark();
    this.getAllBoatMark();
    this.getAllAdventureMark();
  }

  public remember(id: number, entityID: number) {
    this.id = id;
    this.entityID = entityID;
  }

  public getAllCottageMark() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllCottageMark().subscribe((l: ExtendMarkAdminDTO[]) => {
      this.cottageMarks = l;

    });

  }

  public getAllBoatMark() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllBoatMark().subscribe((l: ExtendMarkAdminDTO[]) => {
      this.boatMarks = l;

    });

  }

  public getAllAdventureMark() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllAdventureMark().subscribe((l: ExtendMarkAdminDTO[]) => {
      this.adventureMarks = l;
    });

  }


  public acceptCottageMark() {
    this.ass.acceptCottageMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
    });

  }


  public declineCottageMark() {
    this.ass.declineCottageMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
    })
  }


  public acceptBoatMark() {
    this.ass.accepBoatMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_boat();
    });

  }


  public declineBoatMark() {
    this.ass.declineBoatMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_boat();
    })
  }


  public acceptAdventureMark() {
    this.ass.acceptAdventureMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_adventure();
    });

  }


  public declineAdventureMark() {
    this.ass.declineAdventureMark({ 'id': this.id, 'entityID': this.entityID }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_adventure();
    })
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
