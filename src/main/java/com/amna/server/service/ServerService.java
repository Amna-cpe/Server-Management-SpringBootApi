package com.amna.server.service;

import com.amna.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int Limit);
    Server get(Long id);
    Server update(Server server);
    boolean delete(Long id);

}
