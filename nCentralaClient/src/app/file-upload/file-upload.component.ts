import { Component, OnInit } from '@angular/core';
import { FileService } from '../services/file/file.service';


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

}
