package org.example.mapper;

import org.example.to.domain.ClientPublicInfoTO;
import org.example.to.domain.ClientTO;

public class ClientPublicInfoMapper {
    public static ClientPublicInfoTO toTransferObject(ClientTO client){
        return new ClientPublicInfoTO(
                client.getName(),
                client.getUserType()
        );
    }
}
