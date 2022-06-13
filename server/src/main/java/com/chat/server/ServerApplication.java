package com.chat.server;

import com.chat.server.serverChat.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		int port = 8818;
		Server server = new Server(port);
		server.start();
		SpringApplication.run(ServerApplication.class, args);
	}

}
