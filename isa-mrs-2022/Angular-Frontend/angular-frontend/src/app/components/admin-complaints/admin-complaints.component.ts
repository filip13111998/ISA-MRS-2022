import { ExtendComplaintDTO } from './../../models/response/admin/extended-complaint';
import { Component, OnInit } from '@angular/core';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { animate, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-admin-complaints',
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
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  entity_type: Number;

  displayedColumns: string[] = ['id', 'entityID', 'entityName', 'date', 'description', 'Answer'];
  displayedColumnsBoat: string[] = ['id', 'entityID', 'entityName', 'date', 'description', 'Answer'];
  displayedColumnsAdventure: string[] = ['id', 'entityID', 'entityName', 'date', 'description', 'Answer'];

  cottageMarks: ExtendComplaintDTO[];
  boatMarks: ExtendComplaintDTO[];
  adventureMarks: ExtendComplaintDTO[];

  username: String;

  tkn: any = "";

  id: number;

  entityID: number;

  answer: any = "";

  cottageComplaintForm = this.fb.group({

    answer: [null],
  });

  boatComplaintForm = this.fb.group({

    answer: [null],
  });

  adventureComplaintForm = this.fb.group({

    answer: [null],
  });


  constructor(private ass: AdminServiceService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getAllCottageComplaint();
    this.getAllBoatComplaint();
    this.getAllAdventureComplaint();
  }

  public remember(id: number, entityID: number) {
    this.id = id;
    this.entityID = entityID;
  }

  public getAllCottageComplaint() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllCottageComplaint().subscribe((l: ExtendComplaintDTO[]) => {
      this.cottageMarks = l;
      console.log(l.length + "BROJ ZALBI CTG");
    });

  }

  public getAllBoatComplaint() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllBoatComplaint().subscribe((l: ExtendComplaintDTO[]) => {
      this.boatMarks = l;
      console.log(l.length + "BROJ ZALBI BOAT");
    });

  }

  public getAllAdventureComplaint() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.getAllCAdventureComplaint().subscribe((l: ExtendComplaintDTO[]) => {
      this.adventureMarks = l;
      console.log(l.length + "BROJ ZALBI ADV");
    });

  }


  public answerCottageComplaint() {
    this.ass.answerCottageComplaint({ 'id': this.id, 'description': this.cottageComplaintForm.value.answer }).subscribe((b: Boolean) => {
      this.ngOnInit();
    });

  }


  public answerBoatComplaint() {
    this.ass.answerBoatComplaint({ 'id': this.id, 'description': this.boatComplaintForm.value.answer }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_boat();
    })
  }


  public answerAdventureComplaint() {
    this.ass.answerAdventureComplaint({ 'id': this.id, 'description': this.adventureComplaintForm.value.answer }).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_adventure();
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
