import { CottageProfileDTO } from './../../models/response/http-cottage-response/cottage-profile';
import { CottageServiceService } from './../../services/cottageService/cottage-service.service';
import { Component, AfterViewInit, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  cottageProfile: CottageProfileDTO;

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
      title: "Start"
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



  constructor(private cs: CottageServiceService) {
    this.getCottage();
  }

  ngOnInit(): void {
    // this.getCottage();
  }


  // public getimgs(): Array<object> {
  //   return this.imgCollection;
  // }

  public getCottage() {
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];
    // console.log(window.location.href);
    // console.log("IDDDD " + id);
    this.cs.getOneCotages(parseInt(id)).subscribe((cpdto: CottageProfileDTO) => {
      console.log(cpdto);
      this.cottageProfile = cpdto;
      console.log(this.cottageProfile.cottageImages.length + "BR SLIKA")
      for (let i = 0; i < this.cottageProfile.cottageImages.length; i++) {
        console.log(i);
        console.log(this.cottageProfile.cottageImages[i].name);
        this.imgCollection.push({


          image: `assets/${this.cottageProfile.cottageImages[i].name}`,
          thumbImage: `assets/${this.cottageProfile.cottageImages[i].name}`,
          title: `Cottage Picture ${i}`
        });
      }
      console.log("AEEEE");
      this.imgCollection.forEach(e => console.log(e));
    }
    );


  }



}
