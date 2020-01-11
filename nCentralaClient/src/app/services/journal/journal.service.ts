import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserTaskDTO } from 'src/app/model/UserTaskDTO';

@Injectable({
  providedIn: 'root'
})
export class JournalService {

  constructor(private httpClient: HttpClient) { }

  startProcess() : Observable<any>{
    return this.httpClient.get('http://localhost:8090/task/startProcess/NewJournal');
  }

  finishProcess(dto : UserTaskDTO){
    return this.httpClient.post('http://localhost:8090/journal/finish', dto);
  }
}
