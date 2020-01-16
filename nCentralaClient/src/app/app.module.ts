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

const appRoutes : Routes = [
          {path : '', component : HomepageComponent},
          {path : "registration", component : RegistrationComponent},
          {path : 'login', component : LoginComponent},
          {path : 'form/:id', component : SomeFormComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageGalleryComponent,
    HomepageTablesComponent,
    RegistrationComponent,
    HomepageComponent,
    LoginComponent,
    SomeFormComponent
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

