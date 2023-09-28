package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {

		GameThread gameThread = new GameThread();

		gameThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		SpringApplication.run(BackendApplication.class, args);

	}

}
