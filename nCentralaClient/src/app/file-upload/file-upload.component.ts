import { Component, OnInit } from '@angular/core';
import { FileService } from '../services/file/file.service';
//import * as fileSaver from 'file-saver';
import {saveAs} from 'file-saver';


@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  selectedFile : File = null;
  private selectedFiles = false;

  constructor(private fileService : FileService) { }

  ngOnInit() {
   
  }

  onFileSelected(event){

    this.selectedFile = <File>event.target.files[0];
    this.selectedFiles = true;
    
    // console.log(event);
    // console.log("File name " + this.selectedFile.name);
  }

  upload() {

    const formData : FormData = new FormData();
    formData.append('file', this.selectedFile);
    // console.log("File name " + this.selectedFile.name);
    // console.log("File " + this.selectedFile);

    this.fileService.storeFile(formData).subscribe(res => {
      alert("Uspeh");
    
    });
  }

  download(){

    let v = "D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\ČLANAK 1.pdf";
    var pom = [];

    pom = v.split("\\");

    var ime = pom[pom.length - 1];
    console.log(ime);

     this.fileService.downloadFile(ime).subscribe(res => {
       console.log(res);
       saveAs(res, ime);
     });
  }

}
