package org.machicoro.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.machicoro.ConsoleApp;
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
    private List<ClientTO> clients = new ArrayList<>();

    public static List<ClientPublicInfoTO> getClientPublicInfo() {
        return ConsoleApp.clientsRepository.getClients()
                .stream()
                .map(ClientPublicInfoMapper::toTransferObject)
                .toList();
    }
    public static ClientTO getClientToByConnectionId(int connection){
        return ConsoleApp.clientsRepository.getClients()
                .stream()
                .filter(client -> client.getConnectionId() == connection)
                .findFirst().get();
    }

}
