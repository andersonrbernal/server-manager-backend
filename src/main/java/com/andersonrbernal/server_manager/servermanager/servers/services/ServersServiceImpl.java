package com.andersonrbernal.server_manager.servermanager.servers.services;

import com.andersonrbernal.server_manager.servermanager.servers.entities.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServersServiceImpl {
    Server ping(String ipAddress) throws IOException;
    Server create(Server server);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}