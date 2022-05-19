import { AdventurePricelistDTO } from './../../models/response/http-adventure-response/adventure-pricelist';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/angular';
import { AdventureProfileDTO } from 'src/app/models/response/http-adventure-response/adventure-profile';
import { Datum } from 'src/app/models/util/datum';
import { AdventureserviceService } from 'src/app/services/adventureService/adventureservice.service';
import * as moment from 'moment';
import { SubscriptionService } from 'src/app/services/subscription/subscription.service';
import { SubscribeDTO } from 'src/app/models/subscription/subscribe';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
import { AdventureReservationService } from 'src/app/services/adventure-reservation/adventure-reservation.service';
import { AdventureReservationCalendarDTO } from 'src/app/models/response/http-adventure-response/AdventureReservationCalendarDTO';

@Component({
  selector: 'app-root-adventure-profile',
  templateUrl: './root-adventure-profile.component.html',
  styleUrls: ['./root-adventure-profile.component.css']
})
export class RootAdventureProfileComponent implements OnInit {

  subscribe_toggle: Boolean = false;


  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  adventureProfile: AdventureProfileDTO;

  dan: string = '19';
  godina: number = 2022;
  mesec: string = '05';

  username: String;

  tkn: any = "";

  my_arr: any = [];

  arr: any = [

    // {
    //   title: 'Rezervisano', start: '2022-05-11', end: '2022-05-14'
    // },
    // { title: 'Rezervisano', start: '2022-05-18', end: '2022-05-20' },

    // { title: 'Rezervisano', date: '2022-05-03' },
    // // { title: 'Rezervisano', date: `${this.godina}-${this.mesec}-${this.dan}` },
    // { title: 'Rezervisano', date: '2022-05-24' },

  ]


  calendarOptions: CalendarOptions = {

    initialView: 'dayGridMonth',

    dateClick: this.handleDateClick.bind(this),

    weekends: true,

    events: this.arr,

    eventColor: 'red'

  }

  typesPricelist: CottageComboBox[] = [];

  pricelistId: number = 0;

  ngAfterViewInit() {
    this.mapInitializer();
  }

  mapInitializer() {
    var mapOptions: google.maps.MapOptions = {
      center: { lat: this.adventureProfile.latitude, lng: this.adventureProfile.longitude },
      zoom: 8
    };
    this.map = new google.maps.Map(this.gmap.nativeElement, mapOptions);

    const marker = new google.maps.Marker({
      label: this.adventureProfile.name + "",
      position: { lat: this.adventureProfile.latitude, lng: this.adventureProfile.longitude },
      map: this.map, // this.map will contain the map object here
      title: "Start"
    });

    marker.setMap(this.map);

  }



  constructor(private as: AdventureserviceService, private ss: SubscriptionService, private ras: AdventureReservationService) {
    this.getAdventure();
  }

  ngOnInit(): void {
  }


  public setPricelist(event: any) {
    this.pricelistId = Number(event);

  }

  public getPricelist() {
    this.as.getPricelist(this.adventureProfile.id).subscribe((e: AdventurePricelistDTO[]) => {

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

    dto.entityId = this.adventureProfile.id;

    this.ss.isSubAdventure(dto).subscribe((b: Boolean) => {

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

    dto.entityId = this.adventureProfile.id;

    if (this.subscribe_toggle == false) {
      this.ss.subAdventure(dto).subscribe((b: Boolean) => {

        this.subscribe_toggle = b;

      });
      return this.subscribe_toggle = true;
    }

    this.ss.unsubAdventure(dto).subscribe((b: Boolean) => {
      this.subscribe_toggle = !b;

    });

    return this.subscribe_toggle = false;
  }


  public getCalendarData() {

    this.ras.getAllAdventureReservations(this.adventureProfile.id).subscribe((liste: AdventureReservationCalendarDTO) => {
      liste.adventureResevations.forEach(element => {
        // console.log(element);
        // console.log({ title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).subtract(1, 'months').format('YYYY-MM-DD')}` });
        this.arr = [...this.arr, { borderColor: '#EB5353', backgroundColor: "#EB5353", title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Reservated', start: `${moment(element.reservationStart).format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).format('YYYY-MM-DD')}` });
      });
      liste.adventureActionsReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#F9D923', backgroundColor: "#F9D923", title: 'Action', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      liste.adventureActionsUnReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#187498', backgroundColor: "#187498", title: 'Action Un.', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      this.calendarOptions.events = this.arr;

    });
    // this.calendarOptions.events = this.arr;
    // console.log("KALENDAR" + this.calendarOptions.events);
  }




  public getAdventure() {
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];

    this.as.getOneAdventure(parseInt(id)).subscribe((apdto: AdventureProfileDTO) => {

      this.adventureProfile = apdto;

      for (let i = 0; i < this.adventureProfile.adventureImages.length; i++) {

        this.imgCollection.push({


          image: `assets/${this.adventureProfile.adventureImages[i].name}`,
          thumbImage: `assets/${this.adventureProfile.adventureImages[i].name}`,
          title: `Cottage Picture ${i}`
        });
      }

      this.imgCollection.forEach(e => console.log(e));

      this.setSubscribeToggle();

      this.getPricelist();

      this.getCalendarData();

    }
    );


  }

  handleDateClick(arg: any) {

    for (let message of this.arr) {

      if (message['start'] != undefined && message['end'] != undefined) {

        var ret_val: Boolean = this.check(message['start'], message['end'], arg.dateStr);
        // console.log("START" + message['start']);
        // console.log("END" + message['end']);
        // console.log("MY" + arg.dateStr);
        if (ret_val) {
          // console.log("USOO");
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

    this.my_arr = [...this.my_arr, { borderColor: '#36AE7C', backgroundColor: "#36AE7C", title: 'Rezervisano', date: arg.dateStr }];

    this.calendarOptions.events = this.arr.concat(this.my_arr);

  }
  // toggleWeekends() {
  //   this.calendarOptions.weekends = !this.calendarOptions.weekends // toggle the boolean!
  // }


  check(date1: any, date2: any, checkDate: any): Boolean {

    var d1;

    var d2;

    var c;

    if (date1 != undefined) {

      d1 = date1.split("-");

    }

    if (date2 != undefined) {

      d2 = date2.split("-");

    }

    if (checkDate != undefined) {

      c = checkDate.split("-");

    }

    var m1 = moment(d1).subtract(1, 'M').format('YYYY-MM-DD');
    var m2 = moment(d2).subtract(1, 'M').format('YYYY-MM-DD');
    var cc = moment(c).subtract(1, 'M').format('YYYY-MM-DD');
    console.log(cc);
    console.log(m1);
    console.log(m2);
    if (cc < moment().add(3, 'days').format('YYYY-MM-DD')) {
      console.log("OPS");
      return true;
    }
    if (m1 <= cc && cc < m2) {

      return true;

    }

    else {
      return false;
    }
    // if (from <= check && check < to) {
    //   console.log("from" + d1);
    //   console.log("from" + to);
    //   console.log("from" + check);
    //   console.log("USOO JEDNOM");
    //   return true;

    // }

    // else {
    //   return false;
    // }

  }

  makeReservation() {

    const sorted = this.my_arr.sort((a: any, b: any) => {

      return +new Date(a['date']) - +new Date(b['date'])

    });

    // var start = sorted[0]['date'];
    // var end = sorted[sorted.length - 1]['date'];
    // console.log("START: " + sorted[0]['date']);
    // console.log("END: " + sorted[sorted.length - 1]['date']);

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
