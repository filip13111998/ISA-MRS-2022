import { BoatPricelistDTO } from './../../models/response/http-boat-response/boat-pricelist';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/angular';
import { BoatProfileDTO } from 'src/app/models/response/http-boat-response/boat-profile';
import { BoatserviceService } from 'src/app/services/boatService/boatservice.service';
import * as moment from 'moment';
import { Datum } from 'src/app/models/util/datum';
import { SubscriptionService } from 'src/app/services/subscription/subscription.service';
import { SubscribeDTO } from 'src/app/models/subscription/subscribe';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
@Component({
  selector: 'app-root-boat-profile',
  templateUrl: './root-boat-profile.component.html',
  styleUrls: ['./root-boat-profile.component.css']
})
export class RootBoatProfileComponent implements OnInit {

  subscribe_toggle: Boolean = false;


  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  boatProfile: BoatProfileDTO;

  dan: string = '19';
  godina: number = 2022;
  mesec: string = '05';

  username: String;

  tkn: any = "";

  my_arr: any = [];

  arr = [

    {
      title: 'Rezervisano', start: '2022-05-11', end: '2022-05-14'
    },
    { title: 'Rezervisano', start: '2022-05-18', end: '2022-05-20' },

    { title: 'Rezervisano', date: '2022-05-03' },
    // { title: 'Rezervisano', date: `${this.godina}-${this.mesec}-${this.dan}` },
    { title: 'Rezervisano', date: '2022-05-24' },

  ]


  calendarOptions: CalendarOptions = {

    initialView: 'dayGridMonth',

    dateClick: this.handleDateClick.bind(this),

    weekends: true,

    events: this.arr,

    eventColor: 'red'

  }

  //PRICELIST TWO VARIABLE
  typesPricelist: CottageComboBox[] = [];

  pricelistId: number = 0;



  ngAfterViewInit() {
    this.mapInitializer();
  }

  mapInitializer() {
    var mapOptions: google.maps.MapOptions = {
      center: { lat: this.boatProfile.latitude, lng: this.boatProfile.longitude },
      zoom: 8
    };
    this.map = new google.maps.Map(this.gmap.nativeElement, mapOptions);

    const marker = new google.maps.Marker({
      label: this.boatProfile.name + "",
      position: { lat: this.boatProfile.latitude, lng: this.boatProfile.longitude },
      map: this.map, // this.map will contain the map object here
      title: "Start"
    });


    marker.setMap(this.map);

  }



  constructor(private bs: BoatserviceService, private ss: SubscriptionService) {
    this.getBoat();
  }

  ngOnInit(): void {

  }


  public setPricelist(event: any) {
    this.pricelistId = Number(event);

  }

  public getPricelist() {
    this.bs.getPricelist(this.boatProfile.id).subscribe((e: BoatPricelistDTO[]) => {

      e.forEach(element => {
        this.typesPricelist.push({ value: `${element.id}`, viewValue: `${element.description} - ${element.price}` });
      });
    });
  }

  public setSubscribeToggle() {

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dto = new SubscribeDTO();

    dto.username = this.username + "";

    dto.entityId = this.boatProfile.id;

    this.ss.isSubBoat(dto).subscribe((b: Boolean) => {
      this.subscribe_toggle = b;

    });
  }

  public sub_disable(): Boolean {

    if (localStorage.getItem("user_token") == null) {

      return true;

    }

    return false;

  }

  public subscrube_toggle(): Boolean {

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var dto = new SubscribeDTO();

    dto.username = this.username + "";

    dto.entityId = this.boatProfile.id;

    if (this.subscribe_toggle == false) {
      this.ss.subBoat(dto).subscribe((b: Boolean) => {
        this.subscribe_toggle = b;

      });
      return this.subscribe_toggle = true;
    }

    this.ss.unsubBoat(dto).subscribe((b: Boolean) => {
      this.subscribe_toggle = !b;

    });

    return this.subscribe_toggle = false;
  }



  public getBoat() {

    var path = window.location.href;

    var id = path.split("/")[path.split("/").length - 1];

    this.bs.getOneBoat(parseInt(id)).subscribe((bpdto: BoatProfileDTO) => {

      this.boatProfile = bpdto;

      for (let i = 0; i < this.boatProfile.boatImages.length; i++) {

        this.imgCollection.push({

          image: `assets/${this.boatProfile.boatImages[i].name}`,

          thumbImage: `assets/${this.boatProfile.boatImages[i].name}`,

          title: `Cottage Picture ${i}`

        });

      }

      this.imgCollection.forEach(e => console.log(e));

      this.setSubscribeToggle();

      this.getPricelist();

    }
    );


  }

  handleDateClick(arg: any) {

    for (let message of this.arr) {

      if (message['start'] != undefined && message['end'] != undefined) {

        var ret_val: Boolean = this.check(message['start'], message['end'], arg.dateStr);

        if (ret_val) {

          return;

        }

      }

      if (message['date'] == arg.dateStr) {

        return;

      }

    }
    for (let message of this.my_arr) {

      if (message['date'] == arg.dateStr) {

        this.my_arr = this.my_arr.filter(((day: Datum) => day.date != arg.dateStr));

        this.calendarOptions.events = this.arr.concat(this.my_arr);

        return;

      }


    }

    this.my_arr = [...this.my_arr, { title: 'Rezervisano', date: arg.dateStr }];

    this.calendarOptions.events = this.arr.concat(this.my_arr);

  }
  // toggleWeekends() {
  //   this.calendarOptions.weekends = !this.calendarOptions.weekends // toggle the boolean!
  // }


  check(date1: any, date2: any, checkDate: any): Boolean {

    var dateFrom: any = date1;

    var dateTo: any = date2;

    var dateCheck: any = checkDate;

    var d1;

    var d2;

    var c;

    if (dateFrom != undefined) {

      d1 = dateFrom.split("-");

    }

    if (dateTo != undefined) {

      d2 = dateTo.split("-");

    }

    if (dateCheck != undefined) {

      c = dateCheck.split("-");

    }

    var from = new Date(d1[2], parseInt(d1[1]) - 1, d1[0]);  // -1 because months are from 0 to 11

    var to = new Date(d2[2], parseInt(d2[1]) - 1, d2[0]);

    var check = new Date(c[2], parseInt(c[1]) - 1, c[0]);

    if (from <= check && check < to) {
      return true;

    }

    else {
      return false;
    }

  }

  makeReservation() {

    const sorted = this.my_arr.sort((a: any, b: any) => {

      return +new Date(a['date']) - +new Date(b['date'])

    });


    var counter = 1;

    for (let message of this.my_arr) {

      if (counter == this.my_arr.length) {
        //dobro je ako prodje ovde jer nije naso a stigo do kraja
      }
      else {

        var a = moment(message['date'], 'YYYY-MM-DD');

        var b = moment(this.my_arr[counter]['date'], 'YYYY-MM-DD');

        var diffDays = b.diff(a, 'days');

        if (diffDays > 1) {
          console.log("WRONG - NO RESERVATION KONJU");
        }

        console.log("DIF DAYS: " + diffDays);

      }

      counter++;

    }

  }

}
