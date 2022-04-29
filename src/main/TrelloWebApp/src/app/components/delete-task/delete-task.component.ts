import {Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Task} from 'src/app/interfaces/schema.model';

@Component({
  selector: 'app-delete-task',
  templateUrl: './delete-task.component.html',
  styleUrls: ['./delete-task.component.scss']
})
export class DeleteTaskComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public task: Task) {
  }

  ngOnInit(): void {
  }

}
