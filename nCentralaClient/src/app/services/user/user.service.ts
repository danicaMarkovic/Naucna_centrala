import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient : HttpClient) { }

  getUserByRole(name : String) : Observable<any>{
    
    return this.httpClient.get('http://localhost:8090/user/getByRole/'+name);
  }

  getUserByUsername(username : String) : Observable<any>{

    return this.httpClient.get('http://localhost:8090/user/getUser/'+username);
  }

  getReviewersForArticle() : Observable<any> {

    return this.httpClient.get('http://localhost:8090/user/getReviewers');
  }

}
