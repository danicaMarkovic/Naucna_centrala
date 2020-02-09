import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniversalService {

  constructor(private httpClient : HttpClient) { }

  getAllPaymentMethods() : Observable<any>{

    return this.httpClient.get('http://localhost:8090/method/all');
  }

  getAllScienceAreas() : Observable<any>{

    return this.httpClient.get('http://localhost:8090/area/all');
  }

  getArticleReviews() : Observable<any> {
   
    return this.httpClient.get('http://localhost:8090/review/articleReview');
  }
  
}
