import { Component, OnInit } from '@angular/core';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { CamundaService } from '../services/camunda/camunda.service';
import { UniversalService } from '../services/universal/universal.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private formFields = [];
  private processInstance = "";
  private formFieldsDto = null;
  private enumValues = [];
  private regDTO : UserTaskDTO = new UserTaskDTO();
  private incorectData = 0;
  private areas = [];

  constructor(private camundaService : CamundaService, private uniService : UniversalService) {
    

    let x = camundaService.startRegistrationProcess();

    x.subscribe(
      res => {
        
       
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.regDTO.processId = res.processInstanceId;
        this.regDTO.taskId = res.taskId;
        this.formFields.forEach( (field) =>{
          
          // if( field.type.name=='enum'){
          //   this.enumValues = Object.keys(field.type.values);

          // }
        });
      },
      err => {
        //console.log("Error occured");
      }
    );

    this.uniService.getAllScienceAreas().subscribe(res => {
      this.areas = res;
    }); 

  }

  ngOnInit() {

  }

  onSubmit(value, form){
    
    let formFields = new Array();

    for (var property in value) {
     
      if(this.isBlank(value[property]))
      {
        (<HTMLInputElement>document.getElementById("not"+property)).append("You must fill " + property + " field");
        
        this.incorectData++;
      }else if(property == 'email' && !this.validateEmail(value[property]))
      {
        (<HTMLInputElement>document.getElementById("not"+property)).append("Email must be in form xxxx@yyy.com");
      }
      else
      {

          if(property == 'areas')
          {
            formFields.push({fieldId : property, areas : value[property]});
          }else
          {
            formFields.push({fieldId : property, fieldValue : value[property]});
          }

      }
    }

    if(this.incorectData == 0)
    {
      this.regDTO.formFields = formFields;

      this.camundaService.finishRegistrationProcess(this.regDTO).subscribe(data=>{
        alert("Registration done! Check email to activate your account!")
      },err =>{
        alert("Error!!!");
      });
    }else
    {
      alert("You must correct some fields");
    }
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

  validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

}
