package com.andersonrbernal.server_manager.servermanager.servers.services;

import com.andersonrbernal.server_manager.servermanager.servers.ServerRepository;
import com.andersonrbernal.server_manager.servermanager.servers.entities.Server;
import com.andersonrbernal.server_manager.servermanager.servers.enums.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ServersService implements ServersServiceImpl {
    private final ServerRepository serverRepository;

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        Status serverStatus = address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN;
        server.setStatus(serverStatus);
        return server;
    }

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers.");
        PageRequest request = PageRequest.of(0, limit);
        return serverRepository.findAll(request).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepository.findById(id).orElse(null);
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server: {}", id);
        serverRepository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        int randomImageIndex = new Random().nextInt(4);
        String path = "/api/v1/servers/images/" + imageNames[randomImageIndex];
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).toUriString();
    }
}