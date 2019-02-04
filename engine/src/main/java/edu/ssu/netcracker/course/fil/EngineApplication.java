package edu.ssu.netcracker.course.fil;

import edu.ssu.netcracker.course.fil.service.GameSockets;
import edu.ssu.netcracker.course.fil.service.MainSockets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineApplication.class, args);
		new Thread(new GameSockets()).start();
		new Thread(new MainSockets()).start();
	}

}

