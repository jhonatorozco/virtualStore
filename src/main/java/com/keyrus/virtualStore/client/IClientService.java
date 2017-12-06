package com.keyrus.virtualStore.client;

import com.keyrus.virtualStore.exception.VirtualStoreException;


import java.util.List;

public interface IClientService {

    void addClient(ClientModel Client) throws VirtualStoreException;
    ClientModel updateClient(Long id, ClientModel Client) throws VirtualStoreException;
    ClientModel findClient(Long id) throws VirtualStoreException;
    List<ClientModel> findAll() throws VirtualStoreException;
    void deleteClient(Long id) throws VirtualStoreException;
}
