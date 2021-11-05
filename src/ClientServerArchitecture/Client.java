package ClientServerArchitecture;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The template to create clients that connect with the server in
 * a specific given port.
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

    /**
     * Open the key components to have a connected client with the server
     * and be able to send and get messages with the server.
     * @throws IOException if there is an error creating the socket due to the HOST or PORT
     *                      or creating the channels for send and get messages.
     */
    public static void createClient() throws IOException {
        client = new Socket(HOST,PORT);
        System.out.println("Me conecté al servidor");
        incomingMessage = new DataInputStream(client.getInputStream());
        outgoingMessage = new DataOutputStream(client.getOutputStream());
    }

    /**
     * This method start a thread to wait for a message from the server.
     * @param incomingMessage the DataInputStream of the client.
     */
    public void waitingAnswer(DataInputStream incomingMessage){
        new Thread(()-> {
            try {
                String answer = incomingMessage.readUTF();
                if  (!answer.equals("")){
                    this.answer = answer;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * This method start a thread to wait for a message with the history of a user from the server.
     * @param incomingMessage the DataInputStream of the client.
     */
    public void waitingRequest(DataInputStream incomingMessage){
        new Thread(()-> {
            try {
                String history = incomingMessage.readUTF();
                if  (!history.equals("")){
                    this.history = history;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * It kills or closes the channels that allow to the client
     * communicate with other users through the server.
     * @throws IOException if there is an error closing the client or
     *                     channel to send message, such as try to close them
     *                     when they are closed.
     */
    public void closeConnections() throws IOException {
        client.close();
        incomingMessage.close();
        outgoingMessage.close();
    }

    /**
     * This method sends a message to wait for a result of some expression.
     *
     * @throws IOException if there is an error sending the message.
     */
    public void sendMessage(String msg, String username) throws IOException {
        outgoingMessage.writeUTF(msg+","+username);
        waitingAnswer(incomingMessage);
    }


    /**
     * This method sends a message to wait for the history with all sent expressions by the user.
     *
     * @throws IOException if there is an error sending the message.
     */
    public void sendRequest(String username) throws IOException {
        outgoingMessage.writeUTF("request"+username);
        waitingRequest(incomingMessage);
    }
}
