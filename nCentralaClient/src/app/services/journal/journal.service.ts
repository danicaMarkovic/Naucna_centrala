import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Journal } from 'src/app/model/Journal';

@Injectable({
  providedIn: 'root'
})
export class JournalService {

  constructor(private http : HttpClient) { }

  private url = "http://localhost:8090/journal/";

  getAllActiveJournals() : Observable<Journal>{

    return this.http.get<Journal>(this.url + "all");

  }
}
