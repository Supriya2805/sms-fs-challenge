import { TableModule } from 'primeng/table';
import { CityService } from './shared/city.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    BrowserAnimationsModule,
    TableModule
  ],
  providers: [CityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
