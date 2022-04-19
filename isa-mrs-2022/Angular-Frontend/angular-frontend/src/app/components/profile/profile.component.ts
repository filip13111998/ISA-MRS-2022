import { Component, AfterViewInit, ViewChild, ElementRef, OnInit } from '@angular/core';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  lat = 40.73061;
  lng = -73.935242;

  coordinates = new google.maps.LatLng(this.lat, this.lng);

  mapOptions: google.maps.MapOptions = {
    center: this.coordinates,
    zoom: 8
  };

  // marker = new google.maps.Marker({
  //   position: this.coordinates,
  //   map: this.map,
  // });

  ngAfterViewInit() {
    this.mapInitializer();
  }

  mapInitializer() {
    this.map = new google.maps.Map(this.gmap.nativeElement, this.mapOptions);

    const marker = new google.maps.Marker({
      position: this.coordinates,
      map: this.map, // this.map will contain the map object here
      title: "Start"
    });

    const mark2 = new google.maps.Marker({
      label: "TEST",
      position: { lat: 37.37, lng: -122.03 },
      map: this.map,
      title: "TESTERSZ"
    });

    marker.setMap(this.map);
    mark2.setMap(this.map);
  }

  constructor() { }

  ngOnInit(): void {
  }



}
