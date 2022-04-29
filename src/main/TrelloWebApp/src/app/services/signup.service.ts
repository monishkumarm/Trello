import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SignupService {
  url = "http://localhost:5050";

  constructor(private http: HttpClient) { }

   signup(credentials: any){
    credentials.timeZoneId=34;
    console.log(credentials);
    return this.http.post(`${this.url}/sign-up`,credentials);
}
}
