import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from 'src/app/model/Article';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private  url = "http://localhost:8090/article/";

  constructor(private httpClient : HttpClient) { }

  addArticleToIndex(article : Article){

    return this.httpClient.post(this.url + "newArticle", article);
    
  }

  getAllArticles() : Observable<any> {

    return this.httpClient.get(this.url + "getArticles");
  }
}
