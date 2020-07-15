import { CityService } from './shared/city.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  cities: City[];
  cols: any[];

  constructor(private cityService:CityService){

  }

  ngOnInit(){
   this.cityService.getAll().subscribe(data => {
    this.cities = data.json();
   });

    this.cols = [
      { field: 'name', header: 'City' },
      { field: 'startDate', header: 'Start Date', data: true , format: `MM/dd/yyyy` },
      { field: 'endDate', header: 'End Date', data: true , format: `MM/dd/yyyy` },
      { field: 'price', header: 'Price' },
      { field: 'status', header: 'Status' },
      { field: 'color', header: 'Color' },
  ];
  }
}

export interface City {
  id;
  name;
  startDate;
  endDate;
  price;
  status;
  color;
}
