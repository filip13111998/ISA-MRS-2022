import { CottageReservationService } from 'src/app/services/cottage-reservation/cottage-reservation.service';
import { CottagePricelistDTO } from './../../models/response/http-cottage-response/cottage-pricelist';
import { SubscribeDTO } from './../../models/subscription/subscribe';
import { SubscriptionService } from './../../services/subscription/subscription.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/angular';
import { CottageProfileDTO } from 'src/app/models/response/http-cottage-response/cottage-profile';
import { Datum } from 'src/app/models/util/datum';
import { CottageServiceService } from 'src/app/services/cottageService/cottage-service.service';
import * as moment from 'moment';
import { CottageComboBox } from 'src/app/models/combo-home-page/cottage-combo-box';
import { CottageReservationCalendarDTO } from 'src/app/models/response/http-cottage-response/CottageReservationCalendarDTO';

@Component({
  selector: 'app-root-cottage-profile',
  templateUrl: './root-cottage-profile.component.html',
  styleUrls: ['./root-cottage-profile.component.css']
})
export class RootCottageProfileComponent implements OnInit {

  subscribe_toggle: Boolean = false;

  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;

  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  cottageProfile: CottageProfileDTO;

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

  typesPricelist: CottageComboBox[] = [
    // { value: 'all', viewValue: 'All' },
    // { value: 'not_held', viewValue: 'Not Held' },

  ];

  pricelistId: number = 0;

  ngAfterViewInit() {
    this.mapInitializer();
  }

  mapInitializer() {
    var mapOptions: google.maps.MapOptions = {
      center: { lat: this.cottageProfile.latitude, lng: this.cottageProfile.longitude },
      zoom: 8
    };
    this.map = new google.maps.Map(this.gmap.nativeElement, mapOptions);

    const marker = new google.maps.Marker({
      label: this.cottageProfile.name + "",
      position: { lat: this.cottageProfile.latitude, lng: this.cottageProfile.longitude },
      map: this.map, // this.map will contain the map object here
      title: "Place"
    });

    // const mark2 = new google.maps.Marker({
    //   label: "TEST",
    //   position: { lat: 37.37, lng: -122.03 },
    //   map: this.map,
    //   title: "TESTERSZ"
    // });

    marker.setMap(this.map);
    // mark2.setMap(this.map);
  }



  constructor(private cs: CottageServiceService, private ss: SubscriptionService, private rcs: CottageReservationService) {
    this.getCottage();

  }

  ngOnInit(): void {

  }

  public setPricelist(event: any) {
    this.pricelistId = Number(event);

  }

  public getPricelist() {
    this.cs.getPricelist(this.cottageProfile.id).subscribe((e: CottagePricelistDTO[]) => {


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

    dto.entityId = this.cottageProfile.id;

    this.ss.isSubCotages(dto).subscribe((b: Boolean) => {

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

    dto.entityId = this.cottageProfile.id;

    if (this.subscribe_toggle == false) {
      this.ss.subCotages(dto).subscribe((b: Boolean) => {

        this.subscribe_toggle = b;

      });
      return this.subscribe_toggle = true;
    }

    this.ss.unsubCotages(dto).subscribe((b: Boolean) => {
      this.subscribe_toggle = !b;

    });

    return this.subscribe_toggle = false;
  }


  public getCalendarData() {

    this.rcs.getAllCottageReservations(this.cottageProfile.id).subscribe((liste: CottageReservationCalendarDTO) => {
      liste.cottageResevations.forEach(element => {
        // console.log(element);
        // console.log({ title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).subtract(1, 'months').format('YYYY-MM-DD')}` });
        this.arr = [...this.arr, { borderColor: '#EB5353', backgroundColor: "#EB5353", title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Reservated', start: `${moment(element.reservationStart).format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).format('YYYY-MM-DD')}` });
      });
      liste.cottageActionsReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#F9D923', backgroundColor: "#F9D923", title: 'Action', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      liste.cottageActionsUnReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#187498', backgroundColor: "#187498", title: 'Action Un.', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      this.calendarOptions.events = this.arr;

    });
    // this.calendarOptions.events = this.arr;
    // console.log("KALENDAR" + this.calendarOptions.events);
  }

  public getCottage() {
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];

    this.cs.getOneCotages(parseInt(id)).subscribe((cpdto: CottageProfileDTO) => {
      this.cottageProfile = cpdto;

      for (let i = 0; i < this.cottageProfile.cottageImages.length; i++) {

        this.imgCollection.push({


          image: `assets/${this.cottageProfile.cottageImages[i].name}`,
          thumbImage: `assets/${this.cottageProfile.cottageImages[i].name}`,
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

    this.my_arr = [...this.my_arr, { borderColor: '#36AE7C', backgroundColor: "#36AE7C", title: 'Rezervisano', date: arg.dateStr }];

    this.calendarOptions.events = this.arr.concat(this.my_arr);

  }



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

    if (cc < moment().add(3, 'days').format('YYYY-MM-DD')) {
      return true;
    }

    if (m1 <= cc && cc < m2) {

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
