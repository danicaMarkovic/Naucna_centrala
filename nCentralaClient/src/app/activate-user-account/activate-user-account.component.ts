import { Component, OnInit } from '@angular/core';
import { UniversalService } from '../services/universal/universal.service';
import { RegistrationService } from '../services/registration/registration.service';
import { ActivatedRoute } from '@angular/router';

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
  private taskId : String = "";

  constructor(private service : RegistrationService, private router: ActivatedRoute) {



    let x = service.startTask("e783c9c8-3490-11ea-972e-84a6c85b4b45");
    

    x.subscribe(
      res => {
        console.log(res);
        //this.categories = res;
        this.formFieldsDto = res;
        this.formFields = res.formFields;
        this.processInstance = res.processInstanceId;
        this.processInstance = res.processInstanceId;
        this.taskId = res.taskId;

        this.formFields.forEach(element => {
          if( element.id =='emailConfirmation'){
           this.userEmail = element.defaultValue;
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
  
    this.service.finishTask(this.taskId).subscribe(data =>{
      alert("Activation done!");
      window.location.href = "http://localhost:1337/register";
    },
    err => {
      alert("some error");
    }
    );
  }

}
