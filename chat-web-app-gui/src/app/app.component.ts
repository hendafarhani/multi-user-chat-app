import { Component, OnDestroy, OnInit } from '@angular/core';
import { Message } from './models/message';
import { Person } from './models/person';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ChatClientService } from './services/chat-client.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  accessChatRoom: boolean;
  person: Person;
  messages: Message[] = [];

  webSocketEndPoint: string = 'http://localhost:8086/app';
  history: string = "/chat/history";
  msgSent: string = "/chat/msgSent";
  stompClient: any;
  ws: any;

  constructor(
    private chatClientService: ChatClientService) {
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  nameEntered(name) {
    this.person = new Person();
    this.person.name = name;
    this.chatClientService.connect(this.person).subscribe(() => {
      this.connect();
    })
  }

  sendMessage(message: string) {
    this.chatClientService.sendMessage(new Message(message, this.person.name)).subscribe(() => {
    });
  }

  connect() {
    const thisRef = this;
    this.ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(this.ws);
    this.stompClient.connect({}, function (frame) {
      thisRef.stompClient.subscribe(thisRef.history, function (message) {
        let messages = thisRef.createMessages(message);
        thisRef.messages = [...messages];
      });
      thisRef.stompClient.subscribe(thisRef.msgSent, function (message) {
        let res = JSON.parse(message.body);
        let msg = new Message(res.textMsg, res.person.name, res.date);
        if (thisRef.messages.length == 100) {
          thisRef.messages.shift();
        }
        thisRef.messages = [...thisRef.messages, msg];
      });
    }, this.errorCallBack);
  }

  private disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
  }

  private errorCallBack(error) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  private createMessages(message) {
    let res = JSON.parse(message.body);
    let messages = [];
    for (let i = res["messages"].length - 1; i >= 0; i--) {
      messages.push(new Message(res["messages"][i].textMsg, res["messages"][i].person.name, res["messages"][i].date));
    }
    return messages;
  }
}
