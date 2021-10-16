package ClientServerArchitecture;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * The Server Class that allows to receives clients in the server
 *
 * @author Justin Fern&aacute;ndez y Abraham Venegas
 * @version 1
 */
public class Server {
    private final int PORT = 8080;
    private ServerSocket serverSystem;

    public void createServer() throws IOException {
        serverSystem = new ServerSocket(PORT);
        System.out.println("Servidor abierto");
    }

    public void kill() throws IOException {
        serverSystem.close();
    }

    public void waitingClientThread(){
        new Thread(()-> {
            try {
                this.waitingClient();
                System.out.println("Cliente Conectado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void waitingClient() throws IOException{
        int i = 1;
        while (true) {
            Socket client = serverSystem.accept();
            System.out.println("Cliente conectado "+i);
            i++;
        }
    }
    public static void main(String[] args) {
        Server myServer = new Server();
        try{
            myServer.createServer();
            myServer.waitingClientThread();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
