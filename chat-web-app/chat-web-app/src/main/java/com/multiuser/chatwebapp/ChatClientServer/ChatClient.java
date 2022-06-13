package com.multiuser.chatwebapp.ChatClientServer;


import java.io.*;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private OutputStream serverOut;
    private InputStream serverIn;
    private PrintWriter writer;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ChatClient() {
    }

    public boolean connect() throws IOException {
        socket = new Socket("localhost", 8818);
        serverOut = socket.getOutputStream();
         serverIn = socket.getInputStream();
         writer = new PrintWriter(socket.getOutputStream());
         objectOutputStream = new ObjectOutputStream(serverOut);
         objectInputStream = new ObjectInputStream(serverIn);
         return true;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getServerOut() {
        return serverOut;
    }

    public void setServerOut(OutputStream serverOut) {
        this.serverOut = serverOut;
    }

    public InputStream getServerIn() {
        return serverIn;
    }

    public void setServerIn(InputStream serverIn) {
        this.serverIn = serverIn;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

}
