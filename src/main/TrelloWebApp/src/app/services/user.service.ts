import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../interfaces/schema.model";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = 'http://localhost:5050';
  token = localStorage.getItem("token");
  header = new HttpHeaders(
    {
      Authorization: "Bearer " + this.token
    })

  constructor(private httpClient: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.url}/getUsers`, {'headers': this.header});
  }
}
