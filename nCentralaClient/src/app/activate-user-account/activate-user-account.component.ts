import { Component, OnInit } from '@angular/core';
import { UniversalService } from '../services/universal/universal.service';
import { ActivatedRoute } from '@angular/router';
import { CamundaService } from '../services/camunda/camunda.service';

@Component({
  selector: 'app-activate-user-account',
  templateUrl: './activate-user-account.component.html',
  styleUrls: ['./activate-user-account.component.css']
})
export class ActivateUserAccountComponent implements OnInit {

  private formFields = [];
  private processInstance = "";
  private formFieldsDto = null;
  private userEmail : String = "";
  private taskId = "";
  private taskName = "";

  constructor(private router: ActivatedRoute, private camundaService : CamundaService) {

    this.processInstance =  this.router.snapshot.params.id;

    this.camundaService.getNextActiveTask(this.processInstance).subscribe(res =>{

      res.forEach(element =>{
        this.taskId = element.taskId;
        this.taskName = element.name;
      });

     // let x = service.startTask(this.taskId);
     let x = camundaService.getTaskFields(this.taskId);

      x.subscribe(
        res => {
          
          this.formFieldsDto = res;
          this.formFields = res.formFields;
          this.processInstance = res.processInstanceId;
          this.processInstance = res.processInstanceId;
          this.taskId = res.taskId;

        },
        err => {
          console.log("Error occured");
        }
      );
    });

   }
   

  ngOnInit() {
  }

  onSubmit(value, form){
  
    let formFields = new Array();
    

    //this.service.finishTaskW(this.taskId).subscribe(data =>{
      this.camundaService.finishTaskById(this.taskId, formFields).subscribe(data =>{
      alert("Activation done!");
      window.location.href = "http://localhost:1337/login";
    },
    err => {
      alert("some error");
    }
    );
  }

}
