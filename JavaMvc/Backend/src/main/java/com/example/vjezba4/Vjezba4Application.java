package com.example.vjezba4;

import com.example.vjezba4.service.DeviceService;
import org.springframework.boot.CommandLineRunner;
import com.example.vjezba4.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vjezba4Application implements CommandLineRunner{

	@Autowired
	private ClientService clientService;


	private static Logger LOG = LoggerFactory
			.getLogger(Vjezba4Application.class);

	public static void main(String[] args) {
		LOG.info("STARTING : Spring boot application starting");
		SpringApplication.run(Vjezba4Application.class, args);
		LOG.info("STOPPED  : Spring boot application stopped");
	}

	@Override
	public void run(String... args) throws Exception {
		clientService.startMqtt();
	}

}
