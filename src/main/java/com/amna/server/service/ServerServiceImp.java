package com.amna.server.service;

import com.amna.server.model.Server;
import com.amna.server.repository.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import com.amna.server.model.Status;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService{


    private final ServerRepo serverRepo;



    @Override
    public Server create(Server server) {
        log.info("Saving new server : {} ", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {} ", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int Limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0 , Limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server ID: {}" , id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}" , server.getName());
        return serverRepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("deleting Server id: {}" , id);
        serverRepo.deleteById(id);
        return true;
    }


    private String setServerImageUrl() {
        String[] imgNames = {"server1.png" , "server2.png" };
        Random rand = new Random();
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/server/image/" + imgNames[new Random().nextInt(2)]).toUriString();
    }
}
