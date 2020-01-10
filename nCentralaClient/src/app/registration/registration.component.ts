import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration/registration.service';
import { RegistrationDTO } from '../model/RegistrationDTO';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private formFields = [];
  private processInstance = "";
  private formFieldsDto = null;
  private regDTO : RegistrationDTO = new RegistrationDTO();

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
     
      console.log("Property: " + property); //id
      console.log("Value: " + value[property]); //unesena vrednost

      formFields.push({fieldId : property, fieldValue : value[property]});
    }

    this.regDTO.formFields = formFields;

    //console.log("taskID: " + this.regDTO.taskId);
    //console.log("pID: " + this.regDTO.processId);
    //console.log("niz: " + this.regDTO.formFields.length);
    this.regService.finishProcess(this.regDTO).subscribe(data=>{
      console.log(data);
    });
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
