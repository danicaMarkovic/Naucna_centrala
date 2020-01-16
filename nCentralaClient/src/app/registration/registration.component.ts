import { Component, OnInit } from '@angular/core';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { CamundaService } from '../services/camunda/camunda.service';

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

  constructor(private camundaService : CamundaService) {
    

    let x = camundaService.startRegistrationProcess();

    x.subscribe(
      res => {
        
       
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.regDTO.processId = res.processInstanceId;
        this.regDTO.taskId = res.taskId;
        this.formFields.forEach( (field) =>{
          
          if( field.type.name=='enum'){
            this.enumValues = Object.keys(field.type.values);
          }
        });
      },
      err => {
        //console.log("Error occured");
      }
    );

  }

  ngOnInit() {

  }

  onSubmit(value, form){
    
    let formFields = new Array();

    for (var property in value) {
     
      if(property == 'areas')
      {
        formFields.push({fieldId : property, areas : value[property]});
      }else
      {
        formFields.push({fieldId : property, fieldValue : value[property]});
      }
    }

    this.regDTO.formFields = formFields;

    this.camundaService.finishRegistrationProcess(this.regDTO).subscribe(data=>{
      alert("Registration done! Check email to activate your account!")
    },err =>{
      alert("Error!!!");
    });
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
