import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { HomepageTextComponent } from './homepage-text/homepage-text.component';
import { HomepageGalleryComponent } from './homepage-gallery/homepage-gallery.component';
import { HomepageTablesComponent } from './homepage-tables/homepage-tables.component';
import { RegistrationComponent } from './registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomepageComponent } from './homepage/homepage.component';
import { NewJournalComponent } from './new-journal/new-journal.component';
import { ActivateUserAccountComponent } from './activate-user-account/activate-user-account.component';

const appRoutes : Routes = [
          {path : '', component : HomepageComponent},
          {path : "registration", component : RegistrationComponent},
          {path : 'journal', component : NewJournalComponent},
          {path : 'activate', component : ActivateUserAccountComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageTextComponent,
    HomepageGalleryComponent,
    HomepageTablesComponent,
    RegistrationComponent,
    HomepageComponent,
    NewJournalComponent,
    ActivateUserAccountComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
