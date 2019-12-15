import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Routes } from '@angular/router';
import { HomepageTextComponent } from './homepage-text/homepage-text.component';
import { HomepageGalleryComponent } from './homepage-gallery/homepage-gallery.component';
import { HomepageTablesComponent } from './homepage-tables/homepage-tables.component';

const appRoutes : Routes = [
          
];

@NgModule({
  declarations: [
    AppComponent,
    HomepageTextComponent,
    HomepageGalleryComponent,
    HomepageTablesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
