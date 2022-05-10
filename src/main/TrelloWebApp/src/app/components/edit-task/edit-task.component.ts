import {Component, OnInit, Inject, Optional} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef, MatDialog} from '@angular/material/dialog';
import {Task} from 'src/app/interfaces/schema.model';
import {appConstants} from 'src/app/appConstants';
import {BoardService} from 'src/app/services/board.service';
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit {

  formGroup!: FormGroup;
  issueTypesArrayWithColor = Object.values(appConstants.issueTypeListWithColor);
  usersArray: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { task: Task, edit: boolean },
    private dialogRef: MatDialogRef<EditTaskComponent>,
    public formBuilder: FormBuilder,
    private boardService: BoardService,
    private userService: UserService,
    public colorPickerdialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.getAllUsers();
    const task = this.data && this.data.task ? this.data.task : null;
    this.formGroup = this.formBuilder.group({
      name: [task && task.name ? task.name : '', Validators.required],
      description: [task && task.description ? task.description : '', Validators.required],
      taskStatus: [task && task.taskStatus ? task.taskStatus : ''],
      taskStatusId: [task && task.taskStatusId ? task.taskStatusId : '', Validators.required],
      speaker: [task && task.speaker ? task.speaker : '', Validators.required],
      image: [task && task.image ? task.image : ''],
      issueType: [task && task.issueType ? task.issueType : ''],
      createdAt: [task && task.createdOn ? task.createdOn : new Date()],
      assignees: [task && task.assignees ? task.assignees : ''],
      boardId: [task && task.boardId ? task.boardId : '']
    });
  }

  onSubmit() {
    this.boardService.saveTask(this.formGroup.getRawValue()).subscribe();
    this.dialogRef.close(this.formGroup.value);
  }

  getAllUsers() {
    this.userService.getUsers().subscribe(
      (response: any) => {
        //console.log(response);
        this.usersArray = response;
        console.log(this.usersArray);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
