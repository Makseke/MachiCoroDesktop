package org.example;

import com.esotericsoftware.kryonet.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.repository.ClientsRepository;
import org.example.service.ClientService;
import org.example.service.ServerService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class App {
    public static ClientsRepository clientsRepository = new ClientsRepository();

    public static void main(String[] args) {

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECT START MODE \n 1 - SERVER \n 2 - CLIENT");
        String type = scanner.nextLine();

        if (type.equals("1")){
            System.out.println("SERVER START ON IP " + localhost.getHostAddress());
            ServerService.startServer();
        } else if (type.equals("2")) {
            System.out.println("YOUR IP IS " + localhost.getHostAddress());
            ClientService.startClient();
        }
        else {
            System.out.println("ERROR");
        }

    }


}
