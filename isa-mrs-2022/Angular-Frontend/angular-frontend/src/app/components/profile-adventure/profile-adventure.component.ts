import { AdventureserviceService } from './../../services/adventureService/adventureservice.service';
import { AdventureProfileDTO } from './../../models/response/http-adventure-response/adventure-profile';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-profile-adventure',
  templateUrl: './profile-adventure.component.html',
  styleUrls: ['./profile-adventure.component.css']
})
export class ProfileAdventureComponent implements OnInit {

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  // lat = 40.73061;
  // lng = -73.935242;

  // coordinates = new google.maps.LatLng(this.lat, this.lng);

  displayedColumns: string[] = ['id', 'maxPeopleNum', 'description', 'price', 'startAction', 'endAction'];

  imgCollection: Array<object> = [];

  adventureProfile: AdventureProfileDTO;

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

    // const mark2 = new google.maps.Marker({
    //   label: "TEST",
    //   position: { lat: 37.37, lng: -122.03 },
    //   map: this.map,
    //   title: "TESTERSZ"
    // });

    marker.setMap(this.map);
    // mark2.setMap(this.map);
  }



  constructor(private as: AdventureserviceService) {
    this.getAdventure();
  }

  ngOnInit(): void {
    // this.getCottage();
  }


  // public getimgs(): Array<object> {
  //   return this.imgCollection;
  // }

  public getAdventure() {
    var path = window.location.href;
    var id = path.split("/")[path.split("/").length - 1];
    // console.log(window.location.href);
    // console.log("IDDDD " + id);
    this.as.getOneAdventure(parseInt(id)).subscribe((apdto: AdventureProfileDTO) => {
      console.log(apdto);
      this.adventureProfile = apdto;
      console.log(this.adventureProfile.adventureImages.length + "BR SLIKA")
      for (let i = 0; i < this.adventureProfile.adventureImages.length; i++) {
        console.log(i);
        console.log(this.adventureProfile.adventureImages[i].name);
        this.imgCollection.push({


          image: `assets/${this.adventureProfile.adventureImages[i].name}`,
          thumbImage: `assets/${this.adventureProfile.adventureImages[i].name}`,
          title: `Cottage Picture ${i}`
        });
      }
      console.log("AEEEE");
      this.imgCollection.forEach(e => console.log(e));
    }
    );


  }

}
