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

    save(city: any): Observable<any> {
        let result: Observable<Object>;
        if (city['href']) {
          result = this.http.put(city.href, city);
        } else {
          result = this.http.post(CITY_API, city);
        }
        return result;
      }

    remove(href: string) {
        return this.http.delete(href);
      }
}