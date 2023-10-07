package com.andersonrbernal.server_manager.servermanager;

import com.andersonrbernal.server_manager.servermanager.servers.ServerRepository;
import com.andersonrbernal.server_manager.servermanager.servers.entities.Server;
import com.andersonrbernal.server_manager.servermanager.servers.enums.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepository serverRepository) {
        return args -> {
            Server server1 = new Server(null, "192.168.1.160",
                    "Ubuntu Linux", "16 GB", "Personal PC",
                    "http://localhost:8000/servers/images/server1.png", Status.SERVER_UP);
            Server server2 = new Server(null, "192.168.1.161",
                    "Ubuntu Linux", "16 GB", "Personal PC",
                    "http://localhost:8000/servers/images/server1.png", Status.SERVER_UP);

            Server server3 = new Server(null, "192.168.1.162",
                    "Windows Server 2019", "32 GB", "Enterprise PC",
                    "http://localhost:8000/servers/images/server2.png", Status.SERVER_DOWN);

            serverRepository.save(server1);
            serverRepository.save(server2);
            serverRepository.save(server3);
        };
    }
}
