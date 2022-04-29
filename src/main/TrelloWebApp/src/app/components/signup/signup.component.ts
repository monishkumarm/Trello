import { Component, OnInit } from '@angular/core';
import {SignupService} from "../../services/signup.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  credentials={
    id:'',
    fullName:'',
    password:'',
    email:'',
    salt:'',
    isActive:'',
    timeZoneId:'',
    createdOn:''

  }
  constructor(private signupService:SignupService) { }

  ngOnInit(): void {

  }

  showMsg:boolean =false;
  onSubmit(){

    if(this.credentials.fullName != '' && this.credentials.password != '' && this.credentials.email!=''){
           this.signupService.signup(this.credentials).subscribe(
             (response:any) => {
               this.showMsg=true;
               //window.location.href="/login";
             },
             error => {
               console.log(error);
             }
           );
    }
    else{

    }
  }
}
