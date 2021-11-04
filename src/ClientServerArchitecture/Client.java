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
    public String answer= "";
    public String history = "";

    /**
     * This is the constructor method of the class.
     */
    public Client() {
        this.answer =answer;
        this.history = history;
    }


    public static void createClient() throws IOException {
        client = new Socket(HOST,PORT);
        System.out.println("Me conecté al servidor");
        incomingMessage = new DataInputStream(client.getInputStream());
        outgoingMessage = new DataOutputStream(client.getOutputStream());
    }

    public void waitingAnswer(DataInputStream incomingMessage){
        new Thread(()-> {
            try {
                String answer = incomingMessage.readUTF();
                System.out.println("answer = " + answer);
                if  (!answer.equals("")){
                    this.answer = answer;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void waitingRequest(DataInputStream incomingMessage){
        new Thread(()-> {
            try {
                String history = incomingMessage.readUTF();
                System.out.println("history = " + history);
                if  (!history.equals("")){
                    this.history = history;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void closeConnections() throws IOException {
        client.close();
        incomingMessage.close();
        outgoingMessage.close();
    }

    public void sendMessage(String msg, String username) throws IOException {
        outgoingMessage.writeUTF(msg+","+username);
        waitingAnswer(incomingMessage);
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
        c.createClient();
        Client carro = new Client();
        carro.createClient();
    }

    public void sendRequest(String username) throws IOException {
        outgoingMessage.writeUTF("request"+username);
        waitingRequest(incomingMessage);
    }
}
