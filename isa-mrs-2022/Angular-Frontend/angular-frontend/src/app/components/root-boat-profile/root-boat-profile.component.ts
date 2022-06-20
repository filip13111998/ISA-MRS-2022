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
import { BoatReservationService } from 'src/app/services/boat-reservation/boat-reservation.service';
import { BoatReservationCalendarDTO } from 'src/app/models/response/http-boat-response/BoatReservationCalendarDTO';
import { SaveBoatReservationDTO } from 'src/app/models/response/http-boat-response/SaveBoatReservationDTO';
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

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction', 'fast'];

  imgCollection: Array<object> = [];

  boatProfile: BoatProfileDTO;

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

  //PRICELIST TWO VARIABLE
  typesPricelist: CottageComboBox[] = [];

  pricelistId: number = 0;

  pricelistList: BoatPricelistDTO[];

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



  constructor(private bs: BoatserviceService, private ss: SubscriptionService, private rbs: BoatReservationService) {
    this.getBoat();
  }

  ngOnInit(): void {

  }


  public setPricelist(event: any) {
    this.pricelistId = Number(event);

  }

  public getPricelist() {
    this.bs.getPricelist(this.boatProfile.id).subscribe((e: BoatPricelistDTO[]) => {
      this.pricelistList = e;
      e.forEach(element => {
        this.typesPricelist.push({ value: `${element.id}`, viewValue: `${element.description} - ${element.price}` });
      });
    });
  }

  public setSubscribeToggle() {

    this.tkn = localStorage.getItem('user_token');
    if (this.tkn == null) {
      return;
    }
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

  public getCalendarData() {

    this.rbs.getAllBoatReservations(this.boatProfile.id).subscribe((liste: BoatReservationCalendarDTO) => {
      liste.boatResevations.forEach(element => {
        // console.log(element);
        // console.log({ title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).subtract(1, 'months').format('YYYY-MM-DD')}` });
        this.arr = [...this.arr, { borderColor: '#EB5353', backgroundColor: "#EB5353", title: 'Reservated', start: `${moment(element.reservationStart).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).add(1, 'days').subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Reservated', start: `${moment(element.reservationStart).format('YYYY-MM-DD')}`, end: `${moment(element.reservationEnd).format('YYYY-MM-DD')}` });
      });
      liste.boatActionsReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#F9D923', backgroundColor: "#F9D923", title: 'Action', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).add(1, 'days').subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      liste.boatActionsUnReservated.forEach(element => {
        this.arr = [...this.arr, { borderColor: '#187498', backgroundColor: "#187498", title: 'Action Un.', start: `${moment(element.startAction).subtract(1, 'months').format('YYYY-MM-DD')}`, end: `${moment(element.endAction).add(1, 'days').subtract(1, 'months').format('YYYY-MM-DD')}` }];

        // this.arr.push({ title: 'Action', start: element.startAction, end: element.endAction });
      });
      this.calendarOptions.events = this.arr;

    });
    // this.calendarOptions.events = this.arr;
    // console.log("KALENDAR" + this.calendarOptions.events);
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
    // console.log("MOMENT " + moment().format('YYYY-MM-DD') + "VS " + cc);
    // console.log("MOMENT " + m1 + "VS " + m2);
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

    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];



    var pl = this.pricelistList.filter(e => e.id == this.pricelistId)[0];

    const sorted = this.my_arr.sort((a: any, b: any) => {

      return +new Date(a['date']) - +new Date(b['date'])

    });

    var start = sorted[0]['date'];
    var end = sorted[sorted.length - 1]['date'];

    var counter = 1;

    for (let message of this.my_arr) {

      if (counter == this.my_arr.length) {
        //dobro je ako prodje ovde jer nije naso a stigo do kraja
        var sctg = new SaveBoatReservationDTO();
        sctg.boatId = Number(id);
        sctg.myUsername = this.username + "";
        sctg.start = start;
        sctg.end = end;
        sctg.description = pl.description + "";
        sctg.price = pl.price;

        //dobro je ako prodje ovde jer nije naso a stigo do kraja
        this.rbs.saveBoatReservation(sctg).subscribe((bol: Boolean) => {
          console.log("Uspesna rezervacija?" + bol);
          if (bol == false) {
            // this.ngOnInit();
            window.alert("ACTION DISABLED!")
          }
          location.reload();
        });
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

  makeFastReservation(price: number, desc: string, start: Date, end: Date) {

    console.log("FAST START");
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];

    this.tkn = localStorage.getItem('user_token');
    this.username = JSON.parse(atob(this.tkn.split('.')[1]))['sub'];

    var sctg = new SaveBoatReservationDTO();
    sctg.boatId = Number(id);
    sctg.myUsername = this.username + "";
    // console.log("DATE start:" + moment(start).subtract(1, "month").format('YYYY-MM-DD'));
    sctg.start = new Date(moment(start).subtract(1, "month").format('YYYY-MM-DD'));
    sctg.end = new Date(moment(end).subtract(1, "month").format('YYYY-MM-DD'));
    sctg.description = desc + "";
    sctg.price = price;
    console.log(sctg);
    //dobro je ako prodje ovde jer nije naso a stigo do kraja
    this.rbs.saveBoatReservation(sctg).subscribe((bol: Boolean) => {
      console.log("Uspesna rezervacija?" + bol);
      if (bol == false) {
        // this.ngOnInit();
        window.alert("ACTION DISABLED!")
      }
      location.reload();
    });


    // console.log("Price" + price);
    // // console.log("Desc" + desc);
    // console.log("Start" + moment(start).subtract(1, "month").format('YYYY-MM-DD'));


  }

  public fast_action_disable(start: any): Boolean {
    console.log("TU SAM" + moment(start).subtract(1, 'month').format('MMMM Do YYYY, h:mm:ss a') + "ha");

    console.log("OP    " + moment().format('MMMM Do YYYY, h:mm:ss a') + "op");
    if (localStorage.getItem("user_token") == null) {
      console.log("USO");
      return true;

    }

    if (moment(start).subtract(1, 'M') < moment()) {
      console.log("USO222");
      return true;
    }

    return false;

  }
}
