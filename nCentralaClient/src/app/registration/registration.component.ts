import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration/registration.service';
import { UserTaskDTO } from '../model/UserTaskDTO';

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

  constructor(private regService : RegistrationService) {
    
    let x = regService.startProcess();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
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
     
      //console.log("Property: " + property); //id
      //console.log("Value: " + value[property]); //unesena vrednost

      if(property == 'areas')
      {
        formFields.push({fieldId : property, areas : value[property]});
      }else
      {
        formFields.push({fieldId : property, fieldValue : value[property]});
      }
    }

    this.regDTO.formFields = formFields;

    //console.log("taskID: " + this.regDTO.taskId);
    //console.log("pID: " + this.regDTO.processId);
    //console.log("niz: " + this.regDTO.formFields.length);
    this.regService.finishProcess(this.regDTO).subscribe(data=>{
      alert("Registration done! Check email to activate your account!")
    },err =>{
      alert("Error!!!");
    });
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
