package com.andersonrbernal.server_manager.servermanager.servers;

import com.andersonrbernal.server_manager.servermanager.servers.entities.Server;
import com.andersonrbernal.server_manager.servermanager.servers.enums.Status;
import com.andersonrbernal.server_manager.servermanager.servers.http.ServerResponse;
import com.andersonrbernal.server_manager.servermanager.servers.services.ServersServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServersController {
    private final ServersServiceImpl serversService;

    @GetMapping
    public ResponseEntity<ServerResponse> getServers() {
        Map<?, ?> data = Map.of("servers", serversService.list(30));

        ServerResponse response = ServerResponse
                .builder()
                .timeStamp(now())
                .data(data)
                .message("Servers retrieved.")
                .status(OK)
                .statusCode(OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<ServerResponse> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        try {
            Server server = serversService.ping(ipAddress);
            Map<?, ?> data = Map.of("server", server);
            String message = server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed";

            ServerResponse response = ServerResponse
                    .builder()
                    .timeStamp(now())
                    .data(data)
                    .message(message)
                    .status(OK)
                    .statusCode(OK.value())
                    .build();
            return ResponseEntity.ok(response);
        } catch (IOException ioException) {
            String message = "Error while pinging the server: " + ioException.getMessage();

            ServerResponse response = ServerResponse
                    .builder()
                    .timeStamp(now())
                    .message(message)
                    .status(INTERNAL_SERVER_ERROR)
                    .statusCode(INTERNAL_SERVER_ERROR.value())
                    .build();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ServerResponse> saveServer(@RequestBody @Valid Server server) {
        Server createdServer = serversService.create(server);
        Map<?, ?> data = Map.of("server", createdServer);

        ServerResponse response = ServerResponse
                .builder()
                .timeStamp(now())
                .data(data)
                .message("Server created.")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServerResponse> deleteServer(@PathVariable("id") Long id) {
        Boolean deletedServer = serversService.delete(id);
        Map<?, ?> data = Map.of("deleted", deletedServer);

        ServerResponse response = ServerResponse
                .builder()
                .timeStamp(now())
                .data(data)
                .message("Server deleted.")
                .status(OK)
                .statusCode(OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/images/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getServerImage(@PathVariable("filename") String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("images/" + filename);

        if (resource.exists()) {
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        }

        return ResponseEntity.notFound().build();
    }
}
