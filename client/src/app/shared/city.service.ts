import { CITY_API } from './properties';
import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { Observable } from "rxjs";


@Injectable()
export class CityService{

    constructor(private http: Http){
    }
  
    getAll() : Observable<any>{
        return this.http.get(CITY_API);
    }

    remove(href: string) {
        return this.http.delete(href);
      }
}