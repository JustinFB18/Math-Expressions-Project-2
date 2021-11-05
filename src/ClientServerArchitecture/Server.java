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

    /**
     * Creates the Server.
     *
     * @throws IOException if something wrong happens opening the server in that port.
     */
    public void createServer() throws IOException {
        serverSystem = new ServerSocket(PORT);
        System.out.println("Servidor abierto");
    }

    /**
     * Closes or kills the created server system.
     * @throws IOException if the server is impossible to close.
     */
    public void kill() throws IOException {
        serverSystem.close();
    }

    /**
     * This method opens a Thread to wait for clients that connect with the server.
     * @throws IOException if the waiting client collapses.
     */
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



    /**
     * Waits for clients that connect to the server.
     * @throws IOException if the server can't accept the client connection or something happens wrong with
     * receive messages thread.
     */
    public Socket waitingClient() throws IOException{
        while (true) {
            Socket client = serverSystem.accept();
            System.out.println("Cliente conectado ");
            receiveMessages(client);
        }
    }

    /**
     * Receives information for the client, it could be a request of the history of expressions or the result
     * of one expression.
     *
     * @param client receives a socket with the client to receive messages of them.
     */
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

    /**
     * Analyze the csv with the information that was requested.
     *
     * @param output the DataOutputStream to a specific client.
     * @param username the username that request the history.
     * @throws IOException if the thread of sendAnswer fails.
     */
    private void requestingInfo(DataOutputStream output, String username) throws IOException {
        csvReaderPrinter m = new csvReaderPrinter();
        String historial = m.historyUser(username);
        sendAnswer(output,username,0.0,"request"+historial);
    }


    /**
     * Sends the answer to the client according to its request.
     *
     * @param output the DataOutputStream to a specific client.
     * @param username the username that request the history.
     * @param answer the result of the expression to calculate or 0.0 if is a request of history.
     * @param expression the expression which was evaluated or the history.
     */
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


    /**
     * Registering the expression and its result to the according csv file of the user.
     *
     * @param expression the expression which was evaluated or the history.
     * @param Result the result of the expression to calculate.
     * @param filepath the name of the username to concat with the .csv path.
     */
    public void registerInfo(String expression, String Result, String filepath){
        WriteOnCSV writer = new WriteOnCSV();
        writer.saveRecord(expression,Result,filepath);
    }

    /**
     * Main method of the Server Class that start running when the program is executed.
     * @param args stores the incomding command line arguments for the program
     */
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
