import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Board, Task} from '../interfaces/schema.model';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  url = 'http://localhost:5050';
  token = localStorage.getItem("token");
  header = new HttpHeaders(
    {
      Authorization: "Bearer " + this.token
    }
  )

  constructor(private httpClient: HttpClient) {
  }

  getBoards(): Observable<Board[]> {
    return this.httpClient.get<Board[]>(`${this.url}/tasks/getBoards`, {'headers': this.header});
  }

  updateTaskStatus(taskId: number, taskStatusId: number) {
    return this.httpClient.post(`${this.url}/tasks/update-status`, {
      "id": taskId,
      "taskStatusId": taskStatusId
    }, {'headers': this.header}).subscribe();
  }

  saveTask(task: any) {
    return this.httpClient.post(`${this.url}/tasks/create-task`, task, {'headers': this.header});
  }
}
