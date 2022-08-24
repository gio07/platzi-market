package com.platzi.market.web.controller;

import com.platzi.market.domain.Client;
import com.platzi.market.domain.services.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    @ApiOperation("Get all clients")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Client>> getAll() {

        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<Client> getClient(
            @ApiParam(value = "The id of the client", required = true, example = "1")
            @PathVariable("id") String clientId
    ) {

        return clientService.getClient(clientId)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Save or update client")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Client> save(@RequestBody Client client) {

        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity delete(
            @ApiParam(value = "The id of the client", required = true, example = "1")
            @PathVariable("id") String clientId
    ) {

        if (clientService.delete(clientId)) {

            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
