package org.machicoro.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.machicoro.util.mapper.ClientPublicInfoMapper;
import org.machicoro.to.domain.server.ClientPublicInfoTO;
import org.machicoro.to.domain.server.ClientTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ClientsRepository {
    @Getter
    @Setter
    private static List<ClientTO> clients = new ArrayList<>();

    @Getter
    @Setter
    private static List<ClientPublicInfoTO> clientsPublicInfoTO = new ArrayList<>();

    public static List<ClientPublicInfoTO> getClientPublicInfo() {
        return clients
                .stream()
                .map(ClientPublicInfoMapper::toTransferObject)
                .toList();
    }
    public static ClientTO getClientToByConnectionId(int connection){
        return clients
                .stream()
                .filter(client -> client.getConnectionId() == connection)
                .findFirst().get();
    }

}
