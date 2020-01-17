import { Component, OnInit } from '@angular/core';
import { CamundaService } from '../services/camunda/camunda.service';
import { ActivatedRoute } from '@angular/router';
import { UniversalService } from '../services/universal/universal.service';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { UserService } from '../services/user/user.service';
import { HttpErrorResponse } from '@angular/common/http';

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
  private readOnlyFields = true;

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
    }else if(this.taskName == 'Check data')
    {
      (<any>document.getElementById("name")).readOnly = true;
      (<HTMLInputElement>document.getElementById("issn")).readOnly = true;
      (<HTMLInputElement>document.getElementById("openAccess")).readOnly = true;
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
    let countEd = 0;

    for (var property in value) {
     
      if(property == 'name' && this.isBlank(value[property]))
      {
        alert('Enter name!')
      
      }else if(property == 'issn' && this.isBlank(value[property]))
      {
        alert('Enter issn!');
      }
      if(property == 'scienceArea' && value[property].length == 0)
      {
        alert("Choose science area!");

      }else if(property == 'paymentMethod' && value[property].length == 0)
      {
        alert("Choose payment method!");
      }

      if(property == 'paymentMethod')
      {

        formFields.push({fieldId : property, methods : value[property]});

      }else if(property == 'scienceArea')
      {

        formFields.push({fieldId : property, areas : value[property]});

      }else if(property == 'reviewers')
      {
        if(value[property].length  < 2)
        {
          alert("You must choose min 2 reviewers");
        }else
        {
          value[property].forEach(element => {
            retRew = value[property] + ",";
            countRew++;
          });
          formFields.push({fieldId : property, fieldValue : 	retRew.substring(0, retRew.length - 1)});
        }

      }else if(property == 'editors')
      {
        if(value[property].length == 0)
        {
          alert("You must choose editor!");
        }else
        {
          value[property].forEach(element => {
            retEditors = value[property] + ",";
            countEd++;
          });
          formFields.push({fieldId : property, fieldValue : 	retEditors.substring(0, retEditors.length - 1)});
        }

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
       this.handleAuthError(err);
     });
    }else
    {

      if(this.taskName == 'Choose reviewers' && countRew < 2) 
      {
        alert("Minimum 2 reviewer must be choosen");

      }else if(this.taskName == 'Choose editors' && countEd == 0)
      {
        alert("Editor must be choosen");
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

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

  handleAuthError(err: HttpErrorResponse) {
  
    if (err.status === 410) {
      alert('Error. Fill the fields');
    }
    
  }

}
