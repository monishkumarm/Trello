import {Component, OnInit, Inject} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef, MatDialog} from '@angular/material/dialog';
import {IssueType, Task} from 'src/app/interfaces/schema.model';
import {MatChipInputEvent} from '@angular/material/chips';
import {appConstants} from 'src/app/appConstants';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit {

  formGroup!: FormGroup;
  issueTypesArrayWithColor = Object.values(appConstants.issueTypeListWithColor);

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { task: Task, edit: boolean },
    private dialogRef: MatDialogRef<EditTaskComponent>,
    public formBuilder: FormBuilder,
    public colorPickerdialog: MatDialog
  ) {
  }

  ngOnInit() {
    const task = this.data && this.data.task ? this.data.task : null;
    this.formGroup = this.formBuilder.group({
      text: [task && task.name ? task.name : '', Validators.required],
      speaker: [task && task.speaker ? task.speaker : '', Validators.required],
      image: [task && task.image ? task.image : ''],
      issueType: [task && task.issueType ? task.issueType : ''],
      createdAt: [task && task.createdOn ? task.createdOn : new Date()]
    });
  }

  onSubmit() {
    this.dialogRef.close(this.formGroup.value);
  }
}
