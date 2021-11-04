package ClientServerArchitecture;

import CSV.WriteOnCSV;
import CSV.csvReaderPrinter;
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
        while (true) {
            Socket client = serverSystem.accept();
            System.out.println("Cliente conectado ");
            receiveMessages(client);
        }
    }

    public void receiveMessages(Socket client){
        new Thread(()-> {
            try {
                while (true) {
                    input = new DataInputStream(client.getInputStream());
                    String text = input.readUTF();
                    output = new DataOutputStream(client.getOutputStream());
                    if (text.startsWith("request")){
                        String username = text.substring(7);
                        requestingInfo(output,username);
                    }else{
                        String[] textDivided= text.split(",");
                        text = textDivided[0];
                        String username = textDivided[1];
                        System.out.println(text);
                        Tree miArbol = new Tree();
                        InfixToPostFix o = new InfixToPostFix();
                        o.inFix = text;
                        String postfix = o.transforming();
                        String[] postArray = postfix.split(",");
                        TreeNode root = miArbol.constructTree(postArray);
                        Double answer = miArbol.evaluateTree(root);
                        //output = new DataOutputStream(client.getOutputStream());
                        sendAnswer(output,username,answer,text);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void requestingInfo(DataOutputStream output, String username) throws IOException {
        csvReaderPrinter m = new csvReaderPrinter();
        String historial = m.historyUser(username);
        sendAnswer(output,username,0.0,"request"+historial);
    }


    public void sendAnswer(DataOutputStream output, String username,Double answer,String expression){
        new Thread(()-> {
            try {
                if(expression.startsWith("request")){
                    String yourHistory= expression.substring(7);
                    output.writeUTF(yourHistory);
                }else{
                    output.writeUTF(""+answer);
                    registerInfo(expression,String.valueOf(answer),username+".csv");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void registerInfo(String expression, String Result, String filepath){
        WriteOnCSV writer = new WriteOnCSV();
        writer.saveRecord(expression,Result,filepath);
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
