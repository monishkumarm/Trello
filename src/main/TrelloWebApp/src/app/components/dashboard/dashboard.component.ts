import {Component} from '@angular/core';
import {BoardService} from 'src/app/services/board.service';
import {MatDialog} from '@angular/material/dialog';
import {Board, Task, TaskStatus} from 'src/app/interfaces/schema.model'
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {EditTaskComponent} from '../edit-task/edit-task.component';
import {DeleteTaskComponent} from '../delete-task/delete-task.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent{

  boards: Board[] = [];

  constructor(private _boardService: BoardService, private _dialog: MatDialog) {
    this._boardService.getBoards().subscribe(res => {
      this.boards = res;
    });
  }

  taskStatusIds(boardIndex): string[] {
    return this.boards[boardIndex].taskStatuses.map(taskStatus => taskStatus.id);
  }

  onTalkDrop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }

  onTrackDrop(event: CdkDragDrop<TaskStatus[]>) {
    moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
  }

  addEditTalk(task: any, taskStatus: TaskStatus, edit = false) {
    task.taskStatusId = taskStatus.id;
    this._dialog.open(EditTaskComponent, {data: {task: task, edit}, width: '500px'})
      .afterClosed()
      .subscribe(newTalkData => edit ? Object.assign(task, newTalkData) : taskStatus.tasks.unshift(newTalkData));
  }

  deleteTalk(task: Task, taskStatus: TaskStatus) {
    this._dialog.open(DeleteTaskComponent, {data: task, width: '500px'})
      .afterClosed()
      .subscribe(response => {
        if (response) {
          taskStatus.tasks.splice(taskStatus.tasks.indexOf(task), 1);
        }
      });
  }

  filterByDate(tasks, asc = 1) {
    tasks = [...tasks.sort((a: any, b: any) => (asc) * (new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()))];
  }

}
