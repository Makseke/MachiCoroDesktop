package org.machicoro.util.mapper;

import org.machicoro.entity.Client;
import org.machicoro.to.domain.server.ClientTO;

public class ClientMapper {
    public static ClientTO toTransferObject(Client client){
        return new ClientTO(
                client.getIp(),
                client.getConnectionId(),
                client.getName(),
                client.getUserType()
        );
    }

    public static Client toObject(ClientTO client){
        return new Client(
                client.getIp(),
                client.getConnectionId(),
                client.getName(),
                client.getUserType()
        );
    }
}
