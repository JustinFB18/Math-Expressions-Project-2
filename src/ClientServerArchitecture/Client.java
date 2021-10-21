package ClientServerArchitecture;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The Client Class that allows to connect to a server
 *
 * @author Justin Fern&aacute;ndez y Abraham Venegas
 * @version 1
 */
public class Client {
    private static final String HOST ="127.0.0.1";
    private static final int PORT = 8080;
    private static DataInputStream incomingMessage;
    private static DataOutputStream outgoingMessage;
    private static Socket client;
    public static boolean state = false;

    /**
     * This is the constructor method of the class.
     */
    public Client() {
    }


    public static void createClient(int n) throws IOException {
        client = new Socket(HOST,PORT);
        System.out.println("Me conecté al servidor, soy"+n);
        incomingMessage = new DataInputStream(client.getInputStream());
        outgoingMessage = new DataOutputStream(client.getOutputStream());
    }

    public void closeConnections() throws IOException {
        client.close();
        incomingMessage.close();
        outgoingMessage.close();
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
        c.createClient(1);
        Client carro = new Client();
        carro.createClient(2);
    }
}
