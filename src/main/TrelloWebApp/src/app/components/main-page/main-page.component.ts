import {Component, OnInit} from '@angular/core';
import {BoardService} from "../../services/board.service";
import {Board} from "../../interfaces/schema.model";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  boardFields = {
    id: '',
    createdBy: '',
    createdOn: '',
    isActive: Boolean,
    lastChangeBy: '',
    lastChangedOn: '',
    name: ''
  }

  boards: Board[] = [];
  showForm: Boolean;
  specificBoardId: bigint;
  public data;

  constructor(private _boardService: BoardService, private router: Router, private route: ActivatedRoute) {
    this._boardService.getBoards().subscribe(res => {
      this.boards = res;
    });

    this.showForm = false;
    this.specificBoardId = BigInt(0);
  }

  ngOnInit() {

  }

  showFormMethod() {
    this.showForm = !this.showForm;
  }

  createBoard() {
    console.log(this._boardService.createBoard(this.boardFields).subscribe());
    location.reload();
  }
}



