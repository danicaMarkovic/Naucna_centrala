import { Component, OnInit } from '@angular/core';
import { CamundaService } from '../services/camunda/camunda.service';
import { ActivatedRoute } from '@angular/router';
import { UniversalService } from '../services/universal/universal.service';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-some-form',
  templateUrl: './some-form.component.html',
  styleUrls: ['./some-form.component.css']
})
export class SomeFormComponent implements OnInit {

  private taskId = "";
  private taskName = "";
  private formFieldsDto = null;
  private formFields = [];
  private methods = [];
  private areas = [];
  private journalDTO : UserTaskDTO = new UserTaskDTO();
  private editors = [];
  private reviewers = [];

  constructor(private camundaService : CamundaService, private router : ActivatedRoute, 
              private uniService : UniversalService,  private userS : UserService) { 

    this.taskId = this.router.snapshot.params.id;

    this.camundaService.getTaskFields(this.taskId).subscribe(res =>{

      this.formFieldsDto = res;
      this.taskName = res.taskName;
      this.formFields = res.formFields;
      this.journalDTO.processId = res.processInstanceId;
      this.journalDTO.taskId = res.taskId;

      if(this.taskName == 'Choose editors')
    {
      this.userS.getUserByRole("editor").subscribe(res =>{
        this.editors = res;
      });
    }

    if(this.taskName == 'Choose reviewers' )
    {
      console.log("USAOOOOOOOOOOOOOOOOOOOOO");
      this.userS.getUserByRole("reviewer").subscribe(res =>{

        this.reviewers = res;
        console.log("R: " + res);
  
      });
    }

    });

    this.uniService.getAllPaymentMethods().subscribe( res => {
      this.methods = res;
    });

    this.uniService.getAllScienceAreas().subscribe(res => {
      this.areas = res;
    }); 

    
  
  }

  ngOnInit() {
  }

  onSubmit(value, form){
    
    let formFields = new Array();
    let ret = "";
    let ret2 = "";
    let retRew = "";
    let countRew = 0;
    let retEditors = "";

    for (var property in value) {
     
      if(property == 'paymentMethod')
      {

        formFields.push({fieldId : property, methods : value[property]});

      }else if(property == 'scienceArea')
      {

        formFields.push({fieldId : property, areas : value[property]});

      }else if(property == 'reviewers')
      {
        value[property].forEach(element => {
          retRew = value[property] + ",";
          countRew++;
        });

        formFields.push({fieldId : property, fieldValue : 	retRew.substring(0, retRew.length - 1)});

      }else if(property == 'editors')
      {
        value[property].forEach(element => {
          retEditors = value[property] + ",";
          countRew++;
        });

        formFields.push({fieldId : property, fieldValue : 	retEditors.substring(0, retEditors.length - 1)});

      }
      else
      {
        formFields.push({fieldId : property, fieldValue : value[property]});
      }

      
    }

    if(this.taskName == 'New journal')
    {
      console.log("Usao ovde");
      this.journalDTO.formFields = formFields;
      this.camundaService.finishNewJournalForm(this.journalDTO).subscribe(res =>{
      alert("Task " + this.taskName + " done.");
      window.location.href = "http://localhost:1337";
     },  err => {
      alert("Error while completing task " + this.taskName);
     });
    }else
    {
      this.camundaService.finishTaskById(this.taskId,formFields).subscribe(res =>{
      
        alert("Task " + this.taskName + " done.");
        window.location.href = "http://localhost:1337";
      }, err =>{
        alert("Error while completing task " + this.taskName);
      });
    }
  }

}
