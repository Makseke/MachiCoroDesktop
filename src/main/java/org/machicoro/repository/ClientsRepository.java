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
@NoArgsConstructor
@Getter
@Setter
public class ClientsRepository {
    private static ClientsRepository instance;

    public static ClientsRepository getInstance() {
        if (instance == null) {
            instance = new ClientsRepository();
        }
        return instance;
    }

    private List<ClientTO> clients = new ArrayList<>();

    private List<ClientPublicInfoTO> clientsPublicInfoTO = new ArrayList<>();

    public List<ClientPublicInfoTO> getClientPublicInfo() {
        return clients
                .stream()
                .map(ClientPublicInfoMapper::toTransferObject)
                .toList();
    }

    public ClientTO getClientToByConnectionId(int connection) {
        return clients
                .stream()
                .filter(client -> client.getConnectionId() == connection)
                .findFirst().get();
    }

}
