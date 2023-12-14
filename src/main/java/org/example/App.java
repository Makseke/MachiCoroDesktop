package org.example;

import com.esotericsoftware.kryonet.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.to.domain.TextTO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class App
{
    public static void main( String[] args )
    {
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
            System.out.println("START ON " + localhost.getHostAddress());
            startServer();
        }
        else if (type.equals("2")){
            System.out.println("YOUR IP IS " + localhost.getHostAddress());
            startClient();
        }
        else {
            System.out.println("ERROR");
        }
    }

    private static void startServer() {
        Server server = new Server();
        server.getKryo().register(TextTO.class);


        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                TextTO welcomeMessage = new TextTO("Welcome to the server!");
                connection.sendTCP(welcomeMessage);
                connection.setTimeout(0);
                connection.setKeepAliveTCP(5000);
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO) {
                    TextTO request = (TextTO) object;

                    TextTO response = new TextTO();
                    response.setText("Received message: " + request.getText());
                    connection.sendTCP(response);

                    System.out.println(response.getText());
                    System.out.println(connection.getRemoteAddressTCP());
                }
            }
        });

        try {
            server.start();
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() {
        Client client = new Client();
        client.getKryo().register(TextTO.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("SELECT IP ADDRESS");
        String ip = scanner.nextLine();

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof TextTO) {
                    TextTO response = (TextTO) object;
                    System.out.println("Server response: " + response.getText());
                } else {
                    //System.out.println("Received unexpected object: " + object.toString());
                }
            }

            @Override
            public void connected(com.esotericsoftware.kryonet.Connection connection) {
                connection.setTimeout(0);
                connection.setKeepAliveTCP(5000);
            }
        });

        try {
            client.start();
            client.connect(5000, ip, 54555, 54777);
            TextTO request = new TextTO();
            request.setText("Hello, server!");
            client.sendTCP(request);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            while (true){

            }
        }
    }
}
