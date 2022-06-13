import { animate, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { OwnerEntityDTO } from 'src/app/models/response/admin/owner-entity';
import { AdminServiceService } from 'src/app/services/admin/admin-service.service';

@Component({
  selector: 'app-admin-delete-owners',
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
  templateUrl: './admin-delete-owners.component.html',
  styleUrls: ['./admin-delete-owners.component.css']
})
export class AdminDeleteOwnersComponent implements OnInit {

  entity_type: Number;

  displayedColumns: string[] = ['id', 'name', 'email', 'Delete'];
  displayedColumnsBoat: string[] = ['id', 'name', 'email', 'Delete'];
  displayedColumnsAdventure: string[] = ['id', 'name', 'email', 'Delete'];

  cottageOwners: OwnerEntityDTO[];
  boatOwners: OwnerEntityDTO[];
  adventureOwners: OwnerEntityDTO[];

  username: String;

  tkn: any = "";


  constructor(private ass: AdminServiceService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.set_entity_type_cottage();
    this.getAllCottageOwners();
    this.getAllBoatOwners();
    this.getAllAdventureOwners();
  }

  public getAllCottageOwners() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.findAllCottageOwner().subscribe((l: OwnerEntityDTO[]) => {
      this.cottageOwners = l;

    });

  }

  public getAllBoatOwners() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.findAllBoatOwner().subscribe((l: OwnerEntityDTO[]) => {
      this.boatOwners = l;

    });

  }

  public getAllAdventureOwners() {
    // this.tkn = localStorage.getItem('user_token');
    // this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ass.findAllInstructor().subscribe((l: OwnerEntityDTO[]) => {
      this.adventureOwners = l;
    });

  }


  public deleteCottageOwner(id: number) {
    this.ass.deleteCottageOwner(id).subscribe((b: Boolean) => {
      this.ngOnInit();
    });

  }





  public deleteBoatOwner(id: number) {
    this.ass.deleteBoatOwner(id).subscribe((b: Boolean) => {
      this.ngOnInit();
      this.set_entity_type_boat();
    });

  }





  public deleteInstructor(id: number) {
    this.ass.deleteInstructor(id).subscribe((b: Boolean) => {
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
