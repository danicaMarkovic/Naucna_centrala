import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserTaskDTO } from 'src/app/model/UserTaskDTO';

@Injectable({
  providedIn: 'root'
})
export class CamundaService {

  private  url = "http://localhost:8090/task";

  constructor(private httpClient : HttpClient) { }

  getTasksForLogedUser() : Observable<any> {
    
    return this.httpClient.get(this.url + "/getActiveTasks");
  }

  getTaskFields(taskId : String) : Observable<any>{

    return this.httpClient.get(this.url + "/getTaskField/" + taskId);
  }

  finishTaskById(taskId : String,formFields : Array<{}>) {

    return this.httpClient.post(this.url + "/finishTask/"+ taskId, formFields);
  }

  startRegistrationProcess() : Observable<any>{
    return this.httpClient.get(this.url + '/startRegistrationProcess');
  }

  startProcessJournal() : Observable<any>{

    return this.httpClient.get(this.url + "/startJournalProcess");
  }
  
  finishNewJournalForm(dto : UserTaskDTO){

    return this.httpClient.post(this.url + "/finishNewJournal", dto);
  }

  finishRegistrationProcess(dto : UserTaskDTO){
    return this.httpClient.post(this.url + '/finishRegistration',dto);
  }

  getNextActiveTask(processInstanceId : String) : Observable<any> {
    return this.httpClient.get(this.url + '/get/tasks/'+ processInstanceId);
  }

  
}
