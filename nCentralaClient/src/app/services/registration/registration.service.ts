import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserTaskDTO } from 'src/app/model/UserTaskDTO';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient: HttpClient) { }

  startProcess() : Observable<any>{
    return this.httpClient.get('http://localhost:8090/task/startProcess/NewRegistration');
  }

  startTask(taskId  : String) : Observable<any>{
    return this.httpClient.get('http://localhost:8090/task/startTask/' + taskId );
  }
  
  finishProcess(dto : UserTaskDTO){
    return this.httpClient.post('http://localhost:8090/user/finishRegistration',dto);
  }

  finishTask(taskId : String){
    return this.httpClient.get('http://localhost:8090/task/finishTask/'+taskId);
  }
}
