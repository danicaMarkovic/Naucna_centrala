import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search/search.service';

@Component({
  selector: 'app-proba',
  templateUrl: './proba.component.html',
  styleUrls: ['./proba.component.css']
})
export class ProbaComponent implements OnInit {

  constructor(private searchService : SearchService) { }

  ngOnInit() {
  }

  get(){
    
    this.searchService.findLongitudeAndLatitude("Beograd+Srbija").subscribe(response => {
      var lon = response[0].lon;
      var lat = response[0].lat;
      console.log(lon)
      console.log(lat)
    });
  }

}
