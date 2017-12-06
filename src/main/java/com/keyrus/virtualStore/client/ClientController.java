package com.keyrus.virtualStore.client;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    
    @Autowired
    private IClientService clientService;

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientModel client){

        try{
            clientService.addClient(client);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getClients() {
        List<ClientModel> clients= new ArrayList<>();
        try{
            clients= clientService.findAll();
            return new ResponseEntity<List<ClientModel>>(clients,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<List<ClientModel>>(clients,HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long clientId) {
        try{
            clientService.deleteClient(clientId);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(VirtualStoreException e){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientModel> getOneClient(@PathVariable("clientId") Long clientId) {
        ClientModel client = new ClientModel();
        try{
            client = clientService.findClient(clientId);
            return new ResponseEntity<ClientModel>(client,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<ClientModel>(client,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<ClientModel> updateClient(@PathVariable("clientId") Long clientId, @RequestBody ClientModel updatedClient) {
        ClientModel client = new ClientModel();
        try{
            client = clientService.updateClient(clientId,updatedClient);
            return new ResponseEntity<ClientModel>(client,HttpStatus.OK);
        }catch(VirtualStoreException e){
            return new ResponseEntity<ClientModel>(client,HttpStatus.NOT_FOUND);
        }
    }

}
