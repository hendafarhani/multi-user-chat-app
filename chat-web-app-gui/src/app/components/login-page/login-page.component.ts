import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  name: string;

  @Output()
  nameEntered: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  login() {
    this.nameEntered.emit(this.name);
  }
}
