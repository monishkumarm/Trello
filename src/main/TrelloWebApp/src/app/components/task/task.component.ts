import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {IssueType} from 'src/app/interfaces/schema.model';
import {appConstants} from 'src/app/appConstants';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {

  issueTypesWithColor = appConstants.issueTypeListWithColor;
  issueTypes = Object.values(IssueType);
  @Output() edit = new EventEmitter<void>();
  @Output() delete = new EventEmitter<void>();
  @Input() name!: string;
  @Input() description!: string;
  @Input() author!: string;
  @Input() tags!: any;
  @Input() image!: string;
  @Input() issueType?: string;
  @Input() createdOn!: Date;

  constructor() {
  }

  ngOnInit() {
  }

}
