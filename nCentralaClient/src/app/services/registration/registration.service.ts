import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationDTO } from 'src/app/model/RegistrationDTO';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient: HttpClient) { }

  startProcess() : Observable<any>{
    return this.httpClient.get('http://localhost:8090/user/startRegistration');
  }
  
  finishProcess(dto : RegistrationDTO){
    return this.httpClient.post('http://localhost:8090/user/finishRegistration',dto);
  }
}
