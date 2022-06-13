export class Message {

    textMsg: string;
    personName: string;
    date: Date;

    constructor(textMsg: string, personName: string, date?:Date) {
        this.textMsg = textMsg;
        this.personName = personName;
        this.date = date;
    }
}
