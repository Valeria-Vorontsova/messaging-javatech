package com.example.messagingrabbitmq;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chat started");
        System.out.println("Type message:");

        while (true) {

            String text = scanner.nextLine();

            rabbitTemplate.convertAndSend(
                    "chat-exchange",
                    "",
                    text
            );
        }
    }

}
