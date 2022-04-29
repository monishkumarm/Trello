import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Board } from '../interfaces/schema.model';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  url = 'http://localhost:5050';

  constructor(private httpClient: HttpClient) { }

  getBoards(): Observable<Board[]> {

    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization: "Bearer " + token
      }
    )
    return this.httpClient.get<Board[]>(`${this.url}/tasks/getBoards`, { 'headers': header });
  }
}