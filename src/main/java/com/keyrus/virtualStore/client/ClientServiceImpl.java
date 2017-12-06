package com.keyrus.virtualStore.client;

import com.keyrus.virtualStore.exception.VirtualStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public void addClient(ClientModel client) throws VirtualStoreException {
        try {
            clientRepository.save(client);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }

    @Override
    public ClientModel updateClient(Long id, ClientModel updatedClient) throws VirtualStoreException {
        ClientModel client;
        try{
            client = clientRepository.findOne(id);
            if(client == null){
                throw new VirtualStoreException("El cliente no existe");
            }
            client.setName(updatedClient.getName());
            client.setEmail(updatedClient.getEmail());
            client.setPassword(updatedClient.getPassword());
            client.setAddress(updatedClient.getAddress());
            client = clientRepository.save(client);
        }catch (VirtualStoreException e){
            throw new VirtualStoreException("");
        }
        return client;

    }

    @Override
    public ClientModel findClient(Long id) throws VirtualStoreException {
        ClientModel client;
        try {
            client = clientRepository.findOne(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return client;
    }

    @Override
    public List<ClientModel> findAll() throws VirtualStoreException {
        List<ClientModel> clients;
        try {
            clients= clientRepository.findAll();
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }
        return clients;
    }

    @Override
    public void deleteClient(Long id) throws VirtualStoreException {
        try {
            clientRepository.delete(id);
        } catch (HibernateJdbcException e) {
            throw new VirtualStoreException("This operation is unavailable right now. Try later");
        }

    }
}
