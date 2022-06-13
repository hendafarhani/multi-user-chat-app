import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.scss']
})
export class ChatRoomComponent implements OnInit {

  @Input()
  personName: string;

  @Input()
  messages: string[];

  channel: any;
  newMessage: string;

  @Output()
  sendMessage: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  send() {
    this.sendMessage.emit(this.newMessage);
    this.newMessage = undefined;
  }
}
