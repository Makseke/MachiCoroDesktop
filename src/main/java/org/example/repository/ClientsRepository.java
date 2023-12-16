package org.example.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.App;
import org.example.mapper.ClientPublicInfoMapper;
import org.example.to.domain.server.ClientPublicInfoTO;
import org.example.to.domain.server.ClientTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientsRepository {
    private List<ClientTO> clients = new ArrayList<>();

    public static List<ClientPublicInfoTO> getClientPublicInfo() {
        return App.clientsRepository.getClients()
                .stream()
                .map(ClientPublicInfoMapper::toTransferObject)
                .toList();
    }
    public static ClientTO getClientToByConnectionId(int connection){
        return App.clientsRepository.getClients()
                .stream()
                .filter(client -> client.getConnectionId() == connection)
                .findFirst().get();
    }

}
