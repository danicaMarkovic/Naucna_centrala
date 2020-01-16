import { Component, OnInit } from '@angular/core';
import { LoginInfo } from '../auth/login';
import { AuthService } from '../auth/authService';
import { TokenStorageService } from '../auth/token-storage.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username = "";
  private password = "";
  private loginInfo: LoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
  }

  onSubmit(){

    if(this.isBlank(this.username))
    {
      alert("You must enter username");
    }else if(this.isBlank(this.password))
    {
      alert("You must enter password");
    }else
    {

      this.loginInfo = new LoginInfo(
        this.username,
        this.password);

       this.authService.attemptAuth(this.loginInfo).subscribe(res =>{
          alert("User " + this.username + " successfully loged in");
          this.tokenStorage.saveToken(res.token);
          this.tokenStorage.saveUsername(res.username);
          this.tokenStorage.saveAuthorities(res.authorities);
          window.location.href="http://localhost:1337";
        },err => { 
          this.handleAuthError(err)
        }); 

    }

  }


  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

  handleAuthError(err: HttpErrorResponse) {
  
    if (err.status === 400) {
      alert('Check userame!');
    }else if(err.status === 403) {
      alert('Account is not approved!');
    }
    
  }

}
