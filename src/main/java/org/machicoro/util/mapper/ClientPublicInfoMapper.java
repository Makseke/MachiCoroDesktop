package org.machicoro.util.mapper;

import org.machicoro.to.domain.server.ClientPublicInfoTO;
import org.machicoro.to.domain.server.ClientTO;

public class ClientPublicInfoMapper {
    public static ClientPublicInfoTO toTransferObject(ClientTO client){
        return new ClientPublicInfoTO(
                client.getName(),
                client.getUserType()
        );
    }
}
