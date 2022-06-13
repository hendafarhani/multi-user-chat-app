import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../models/message';
import { Person } from '../models/person';

@Injectable({
  providedIn: 'root'
})
export class ChatClientService {

  constructor(private http: HttpClient) { }

  connect(person: Person) {
    return this.http.post<any>('http://localhost:8086/chat-client/connect', person);
  }
 
  sendMessage(message: Message) {
    return this.http.post<any>('http://localhost:8086/chat-client/send', message);
  }
}
