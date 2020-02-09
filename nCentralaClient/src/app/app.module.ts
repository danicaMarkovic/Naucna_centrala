import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { HomepageGalleryComponent } from './homepage-gallery/homepage-gallery.component';
import { HomepageTablesComponent } from './homepage-tables/homepage-tables.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { httpInterceptorProviders } from './auth/auth-interceptor.service';
import { SomeFormComponent } from './some-form/some-form.component';
import { ActivateUserAccountComponent } from './activate-user-account/activate-user-account.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { formatDate } from "@angular/common";

const appRoutes : Routes = [
          {path : '', component : HomepageComponent},
          {path : 'activate/:id', component : ActivateUserAccountComponent},
          {path : "registration", component : RegistrationComponent},
          {path : 'login', component : LoginComponent},
          {path : 'form/:id', component : SomeFormComponent},
          {path : 'file', component : FileUploadComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageGalleryComponent,
    HomepageTablesComponent,
    RegistrationComponent,
    HomepageComponent,
    LoginComponent,
    SomeFormComponent,
    ActivateUserAccountComponent,
    FileUploadComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing : true}
    ),
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

