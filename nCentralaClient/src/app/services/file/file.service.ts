import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private url = "http://localhost:8090/file"
  constructor(private httpClient : HttpClient) { }

  storeFile(formData : FormData){
    return this.httpClient.post(this.url + "/savefile", formData, { responseType: 'text'})
  }

}
