import { Component, OnInit } from '@angular/core';
import { JournalService } from '../services/journal/journal.service';
import { UniversalService } from '../services/universal/universal.service';
import { UserTaskDTO } from '../model/UserTaskDTO';

@Component({
  selector: 'app-new-journal',
  templateUrl: './new-journal.component.html',
  styleUrls: ['./new-journal.component.css']
})
export class NewJournalComponent implements OnInit {

  private formFields = [];
  private processInstance = "";
  private formFieldsDto = null;
  private methods = [];
  private areas = [];
  private journalDTO : UserTaskDTO = new UserTaskDTO();

  constructor(private service : JournalService, private uniService : UniversalService) {

    let x = service.startProcess();

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.journalDTO.processId = res.processInstanceId;
        this.journalDTO.taskId = res.taskId;
      },
      err => {
        //console.log("Error occured");
      }
    );

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

    for (var property in value) {
     
      if(property == 'paymentMethod')
      {
        formFields.push({fieldId : property, methods : value[property]});
      }else if(property == 'scienceArea')
      {
        formFields.push({fieldId : property, areas : value[property]});
      }else
      {
        formFields.push({fieldId : property, fieldValue : value[property]});
      }

      
    }
    this.journalDTO.formFields = formFields;

    this.service.finishProcess(this.journalDTO).subscribe(res =>{
      console.log("USpeeeh")
    });
  }

}
