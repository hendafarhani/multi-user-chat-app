# multi-user-chat-app
Project:
Three projects:
- chat-web-app-gui: Frontend project built in Angular11.
- chat-web-app: Backend project built with spring boot and java8. It is the API that receives 
requests coming from front-end and routes them to chat server. It plays the role of a gateway 
between frontend and the chat-server.
- server: Backend project built with spring boot and java11. It is the chat server that connects to 
sql database. It takes care of saving chat users as well as their messages and retrieving them 
from the database. It is also responsible on broadcasting messages to all users.
Communication between server and chat-web-app happens via Socket.
Communication between chat-web-app-gui and chat-web-app happens via webSocket.

## How to run the project?
1) Install Angular 11. 
2) Once Angular11 is installed, go under folder to chat-web-app-gui and run npm install command.
3) Once npm insall command finishes run npm start.
4) Install SQL database and Microsoft SQL Server Management Studio.
5) Using Microsoft SQL Server Management Studio, create a user of SQL Server whose name is 
guest and a password guest.
6) Create a database chat_web_app and associate it to user guest.
7) Install Java JDK11. 
8) Install Apache Maven.
9) Run command mvn clean install under server folder.
10) Run main class ServerApplication under server project. And then run ChatWebApplication
under server project

## Database :
Two tables : Person and Message. <br />


Table Person: <br />
- Cols: <br />
o id: bigint, not null. <br />
o name: varchar(255), null. <br />


Table Message: <br />
- Cols:
o id: bigint, not null. <br />
o date: datetime, null. <br />
o text_msg: varchar(255), null. <br />
o person_id: foreign key, bigint, not null. <br />


Relation between Person and Message Tables is OneToMany. Means one record of table person can be 
related to multiple records of table Message.
