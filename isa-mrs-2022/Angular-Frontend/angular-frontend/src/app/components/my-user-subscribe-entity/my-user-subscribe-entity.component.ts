import { BoatDTO } from './../../models/response/http-boat-response/boat-dto';
import { SubscriptionService } from './../../services/subscription/subscription.service';
import { Component, OnInit } from '@angular/core';
import { CottageDTO } from 'src/app/models/response/http-cottage-response/cottage-dto';
import { AdventureDTO } from 'src/app/models/response/http-adventure-response/adventure-dto';
import { animate, style, transition, trigger } from '@angular/animations';
import { SubscribeDTO } from 'src/app/models/subscription/subscribe';

@Component({
  selector: 'app-my-user-subscribe-entity',
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
  templateUrl: './my-user-subscribe-entity.component.html',
  styleUrls: ['./my-user-subscribe-entity.component.css']
})
export class MyUserSubscribeEntityComponent implements OnInit {

  entity_type: Number;

  cttList: CottageDTO[] = [];

  adList: AdventureDTO[] = [];

  btList: BoatDTO[] = [];

  username: String;

  tkn: any = "";

  constructor(private ss: SubscriptionService) { }

  ngOnInit(): void {
    this.set_entity_type_cottage();

    this.getAllCottage();

    this.getAllBoat();

    this.getAllAdventure();

  }


  public unsubscribe_cottage(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dto = new SubscribeDTO();

    dto.username = this.username + "";

    dto.entityId = id;

    this.ss.unsubCotages(dto).subscribe((b: Boolean) => {

      this.getAllCottage();
    });
  }

  public unsubscribe_boat(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dto = new SubscribeDTO();

    dto.username = this.username + "";

    dto.entityId = id;

    this.ss.unsubBoat(dto).subscribe((b: Boolean) => {

      this.getAllBoat();
    });
  }


  public unsubscribe_adventure(id: number) {
    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dto = new SubscribeDTO();

    dto.username = this.username + "";

    dto.entityId = id;

    this.ss.unsubAdventure(dto).subscribe((b: Boolean) => {

      this.getAllAdventure();
    });
  }


  public getAllCottage() {

    this.tkn = localStorage.getItem('user_token');

    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ss.getSubCotages(this.username + "").subscribe((cdto: CottageDTO[]) => {
      console.log(cdto);
      this.cttList = cdto;

    }

    );

  }

  public getAllBoat() {

    this.tkn = localStorage.getItem('user_token');

    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ss.getSubBoats(this.username + "").subscribe((bdto: BoatDTO[]) => {

      this.btList = bdto;

    }

    );

  }

  public getAllAdventure() {

    this.tkn = localStorage.getItem('user_token');

    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    this.ss.getSubAdventures(this.username + "").subscribe((adto: AdventureDTO[]) => {

      this.adList = adto;

    }

    );

  }



  public goProfile(id: number): any {

    if (localStorage.getItem('user_token') != null) {

      return ['/loginProfileCottage', id];

    }

    return ['/profileCottage', id];

  }


  public goProfileBoat(id: number): any {

    if (localStorage.getItem('user_token') != null) {

      return ['/loginProfileBoat', id];

    }

    return ['/profileBoat', id];

  }



  public goProfileAdventure(id: number): any {

    if (localStorage.getItem('user_token') != null) {

      return ['/loginProfileAdventure', id];

    }

    return ['/profileAdventure', id];

  }



  public getImageName(name: string): String {

    return "assets/" + name;

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
