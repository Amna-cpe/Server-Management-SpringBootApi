package com.amna.server;

import com.amna.server.model.Server;
import com.amna.server.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.amna.server.model.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(ServerRepo serverRepo){
//		return args ->{
//			serverRepo.save(new Server(null,"192.168.1.160" ,
//					"Ubuntu Linux" , "16 GB" , "Personal PC" ,
//					"http://localhost:8080/api/server/image/server1.png",SERVER_UP));
//		};
//	}

}
