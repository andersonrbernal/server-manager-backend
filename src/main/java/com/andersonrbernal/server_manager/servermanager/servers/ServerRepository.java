package com.andersonrbernal.server_manager.servermanager.servers;

import com.andersonrbernal.server_manager.servermanager.servers.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
