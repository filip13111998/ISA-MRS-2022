import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { BoatProfileDTO } from 'src/app/models/response/http-boat-response/boat-profile';
import { BoatserviceService } from 'src/app/services/boatService/boatservice.service';

@Component({
  selector: 'app-root-boat-profile',
  templateUrl: './root-boat-profile.component.html',
  styleUrls: ['./root-boat-profile.component.css']
})
export class RootBoatProfileComponent implements OnInit {

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  boatProfile: BoatProfileDTO;

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

    // const mark2 = new google.maps.Marker({
    //   label: "TEST",
    //   position: { lat: 37.37, lng: -122.03 },
    //   map: this.map,
    //   title: "TESTERSZ"
    // });

    marker.setMap(this.map);
    // mark2.setMap(this.map);
  }



  constructor(private bs: BoatserviceService) {
    this.getBoat();
  }

  ngOnInit(): void {
    // this.getCottage();
  }


  // public getimgs(): Array<object> {
  //   return this.imgCollection;
  // }

  public getBoat() {
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];
    // console.log(window.location.href);
    // console.log("IDDDD " + id);
    this.bs.getOneBoat(parseInt(id)).subscribe((bpdto: BoatProfileDTO) => {
      console.log(bpdto);
      this.boatProfile = bpdto;
      console.log(this.boatProfile.boatImages.length + "BR SLIKA")
      for (let i = 0; i < this.boatProfile.boatImages.length; i++) {
        console.log(i);
        console.log(this.boatProfile.boatImages[i].name);
        this.imgCollection.push({


          image: `assets/${this.boatProfile.boatImages[i].name}`,
          thumbImage: `assets/${this.boatProfile.boatImages[i].name}`,
          title: `Cottage Picture ${i}`
        });
      }
      console.log("AEEEE");
      this.imgCollection.forEach(e => console.log(e));
    }
    );


  }

}
