package com.amna.server.controller;

import com.amna.server.model.Response;
import com.amna.server.model.Server;
import com.amna.server.model.Status;
import com.amna.server.service.ServerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImp serverServiceImp;

    @GetMapping("/")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers" , serverServiceImp.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> ping(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImp.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , server))
                        .message(server.getStatus()== Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }



    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid Server server)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server created" , serverServiceImp.create(server)))
                        .message("Server Created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid Server server)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server Updated" , serverServiceImp.update(server)))
                        .message("Server Updated")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , serverServiceImp.get(id)))
                        .message("Server was retrieved ")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server" , serverServiceImp.delete(id)))
                        .message("Server was deleted ")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }



    @GetMapping(path="/image/{image}" , produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("image") String image) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") +"Downloads/images/"+image));
    }

}
