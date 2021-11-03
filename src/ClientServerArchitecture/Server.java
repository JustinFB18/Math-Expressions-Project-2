package ClientServerArchitecture;

import ExpressionTree.InfixToPostFix;
import ExpressionTree.Tree;
import ExpressionTree.TreeNode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    private DataOutputStream output;
    private DataInputStream input;

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
                Socket a = new Socket();
                this.waitingClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Socket waitingClient() throws IOException{
        int i=1;
        while (true) {
            Socket client = serverSystem.accept();
            System.out.println("Cliente conectado "+ i);
            i++;
            receiveMessages(client,i);
        }
    }

    public void receiveMessages(Socket client,int id){
        new Thread(()-> {
            try {
                while (true) {
                    input = new DataInputStream(client.getInputStream());
                    String text = input.readUTF();
                    System.out.println(text);
                    Tree miArbol = new Tree();
                    InfixToPostFix o = new InfixToPostFix();
                    o.inFix = text;
                    String postfix = o.transforming();
                    String[] postArray = postfix.split(",");
                    TreeNode root = miArbol.constructTree(postArray);
                    Double answer = miArbol.evaluateTree(root);
                    output = new DataOutputStream(client.getOutputStream());
                    sendAnswer(output,id,answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendAnswer(DataOutputStream output, int id,Double answer){
        new Thread(()-> {
            try {
                output.writeUTF("Answer "+answer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
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
