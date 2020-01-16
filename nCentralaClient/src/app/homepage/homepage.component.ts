import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/authService';
import { TokenStorageService } from '../auth/token-storage.service';
import { UserService } from '../services/user/user.service';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { CamundaService } from '../services/camunda/camunda.service';
import { element } from 'protractor';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  private isLoggedIn = false;
  private isLoginFailed = false;
  private roles: string[] = [];
  private user   = new UserTaskDTO();
  private taskDtos = [];
  private editorLoggedIn = false;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
              private userServ : UserService, private camundaService : CamundaService) { }

  ngOnInit() {

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      
      this.roles.forEach(element =>{

          if(element == 'ROLE_EDITOR')
          {
            this.editorLoggedIn = true;
          }
      });

      this.userServ.getUserByUsername(this.tokenStorage.getUsername()).subscribe(res=>{
        this.user = res;
      });

      this.camundaService.getTasksForLogedUser().subscribe(res =>{
        this.taskDtos = res;
      }, err => {
        alert("Some error happend during getting tasks for user");
      });
    }

  }

  
  logout(){
    this.tokenStorage.signOut();
    this.isLoggedIn = false;
    window.location.href="http://localhost:1337";    
  }

  completeTask(taskId : String){

    window.location.href = "http://localhost:1337/form/"+taskId;
  }

  startJournalProcess(){
    
    this.camundaService.startProcessJournal().subscribe(res => {

      window.location.href = "http://localhost:1337";

    }, err => {
      alert("Error");
    });
  }

  startRegistrationProcess() {
    alert("Klik");
  }

}
