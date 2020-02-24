import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BasicSearch } from 'src/app/model/BasicSearch';
import { Observable } from 'rxjs';
import { AdvancedSearchDataDTO } from 'src/app/model/AdvancedSearchDataDTO';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private  url = "http://localhost:8090/search";

  constructor(private http : HttpClient) { }

  getAllReviewers() : Observable<any> {

    return this.http.get("http://localhost:8090/reviewer/getAllReviewers");
  }

  basicSearch(query : BasicSearch) : Observable<any>{
    return this.http.post(this.url + "/basicSearch", query);
  }

  advancedSearch(query : AdvancedSearchDataDTO) : Observable<any>{
    return this.http.post(this.url + "/advancedSearch", query);
  }

  findLongitudeAndLatitude(adresa: String){
    console.log("adresa: "+adresa);
    return this.http.get('https://nominatim.openstreetmap.org/search?q=%20"+'+adresa+'+"%20&format=json');
  }

  scienceAreaSearch(id : number) : Observable<any> {
    return this.http.get(this.url + "/areaFilter/" + id);
  }

  geoSearch(id : number) : Observable<any>{
    return this.http.get(this.url + "/geoSearch/"+id);
  }

  moreLikeThisSearch(id : number) : Observable<any> {
    return this.http.get(this.url + "/moreLikeThis/" + id);
  }
}
