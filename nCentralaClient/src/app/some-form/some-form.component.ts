import { Component, OnInit } from '@angular/core';
import { CamundaService } from '../services/camunda/camunda.service';
import { ActivatedRoute } from '@angular/router';
import { UniversalService } from '../services/universal/universal.service';
import { UserTaskDTO } from '../model/UserTaskDTO';
import { UserService } from '../services/user/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { JournalService } from '../services/journal/journal.service';
import { FileService } from '../services/file/file.service';
import { formatDate } from '@angular/common';
import { TokenStorageService } from '../auth/token-storage.service';

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
  private journals;
  private scienceAreas;
  selectedFile : File = null;
  private selectedFiles = false;
  private pdfPath;
  private newPdf;
  private choosenRev = [];
  private newReviewer = [];
  private enumValues = [];
  private reviews = [];
  private dataDiv = false;
  private authorLoggedIn = false;
  private editorLoggedIn = false;
  private roles: string[] = [];

  constructor(private camundaService : CamundaService, private router : ActivatedRoute, private fileService : FileService,
              private uniService : UniversalService,  private userS : UserService, private journalService : JournalService,
              private tokenStorage: TokenStorageService) { 

    this.taskId = this.router.snapshot.params.id;

    this.camundaService.getTaskFields(this.taskId).subscribe(res =>{

      this.formFieldsDto = res;
      this.taskName = res.taskName;
      this.formFields = res.formFields;
      this.journalDTO.processId = res.processInstanceId;
      this.journalDTO.taskId = res.taskId;

      this.formFields.forEach( (field) =>{
          
        if( field.type.name=='enum'){
          this.enumValues = Object.keys(field.type.values);
        }
      });

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
      this.userS.getUserByRole("reviewer").subscribe(res =>{

        this.reviewers = res;
  
      });
    }

    // if(this.taskName == 'Choose journal')
    // {
    //   this.journalService.getAllActiveJournals().subscribe(res => {

    //     this.journals = res;
        
    //   });
    // }

    // if(this.taskName == 'Article form')
    // {
    //   this.uniService.getAllScienceAreas().subscribe(res=>{
        
    //     this.scienceAreas = res;

    //   });
    // }

    if(this.taskName == 'Choose reviewers for article')
    {
      this.userS.getReviewersForArticle().subscribe(res => {

        this.choosenRev = res;
        
      });
    }

    if(this.taskName == 'Analyze review')
    {
      this.uniService.getArticleReviews().subscribe(res => {

        this.reviews = res;
        this.dataDiv = true;
      });
    }

    if(this.taskName == 'See comments from reviewers')
    {
      this.uniService.getArticleReviews().subscribe(res => {

        this.reviews = res;
        this.dataDiv = true;
        
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

    if (this.tokenStorage.getToken()) {
      
      this.roles = this.tokenStorage.getAuthorities();
      
      this.roles.forEach(element =>{

          if(element == 'ROLE_EDITOR')
          {
            this.editorLoggedIn = true;
          }

          if(element == 'ROLE_AUTHOR')
          {
            this.authorLoggedIn = true;
          }
      });
    }

  }

  onSubmit(value, form){
    console.log("Submit");
    let formFields = new Array();
    let ret = "";
    let ret2 = "";
    let retRew = "";
    let countRew = 0;
    let retEditors = "";
    let countEd = 0;
    let retArticleRev = "";
    let countArticleRev = 0;

    for (var property in value) {
      console.log("Property: " + property + " ,value: " + value[property]);
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
        // if(value[property].length  < 2)
        // {
        //  // alert("You must choose min 2 reviewers");
        // }else
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

      }else if(property == 'choosenReviewers')
      {
        value[property].forEach(element => {

          retArticleRev = value[property] + ",";
          countArticleRev++;
        });
        formFields.push({fieldId : property, fieldValue : 	retArticleRev.substring(0, retArticleRev.length - 1)});
      }
      else if(property == 'pdf')
      {
        formFields.push({fieldId : property, fieldValue : this.pdfPath}); 
      }else if(property == 'newPdf' || property == 'changedPdf')
      {
        formFields.push({fieldId : property, fieldValue : this.newPdf}); 
      }
      else if(property == 'deadline' || property == 'reviewingDeadline')
      {
        const format = 'dd/MM/yyyy';
        const locale = 'en-US';
        const formattedDate = formatDate(value[property], format, locale);
       
        formFields.push({fieldId : property, fieldValue : formattedDate});
      }
      else
      {
        
        formFields.push({fieldId : property, fieldValue : value[property]});
      }

      
    }

    if(this.taskName == 'New journal')
    {
      this.journalDTO.formFields = formFields;
      this.camundaService.finishNewJournalForm(this.journalDTO).subscribe(res =>{
      alert("Task " + this.taskName + " done.");
      window.location.href = "http://localhost:1337";
     },  err => {
       this.handleAuthError(err);
     });
    }else
    {

      // if(this.taskName == 'Choose reviewers' && countRew < 2) 
      // {
      //   alert("Minimum 2 reviewer must be choosen");

      // }else if(this.taskName == 'Choose editors' && countEd == 0)
      // {
      //   alert("Editor must be choosen");
      // }else
      if(this.taskName == 'Choose reviewers for article' && countArticleRev < 2)
      {
        alert("You must choose min 2 reviewers");
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

  onFileSelected(event){

    console.log("File select");
    this.selectedFile = <File>event.target.files[0];
    this.selectedFiles = true;

    const formData : FormData = new FormData();
    formData.append('file', this.selectedFile);
    
    this.fileService.storeFile(formData).subscribe(res => {
      //alert("Uspeh");
      this.pdfPath = res;
      this.newPdf = res;
    });
    
  }

  download(value : String){

    console.log("Stiglo " + value);
    var pom = [];
    pom = value.split("\\");

    var ime = pom[pom.length - 1];

    this.fileService.downloadFile(ime).subscribe(res => {
      console.log(res);
      saveAs(res, ime);
    });

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
