package GUI_Calculator;

import ClientServerArchitecture.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Control the functions and the interactivity between the user and the app.
 *
 * @author Justin Fern&aacute;ndez y Abraham Venegas
 * @version 1
 */
public class Controller {
    public int port = 8080;
    @FXML
    public Button Result;
    public TextField EnterOperation;
    public TextArea Results;
    public TextArea myOperations;
    public TextField userName;
    public Button showOperations;

    public String Operation ="";
    public Client c;

    /**
     * This method occurs when the window is opened and connects the client with a server.
     *
     * @throws IOException if it is not possible to connect with the server.
     */
    @FXML
    public void initialize() throws IOException {
        c = new Client();
        c.createClient();  // creates the new client
    }

    /**
     * When the button with the text: "Result" is clicked this event occurs and sends the information
     * to the server.
     *
     * @param e Receives a event of action connected to the button of result
     * @throws IOException if something wrong happens asking the result or if is not username.
     */
    public void returnResult(ActionEvent e) throws IOException, InterruptedException {
        if (userName.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.NONE);
            // set alert type
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please write your username.");
            a.setHeaderText(null);
            // show the dialog
            a.show();
        }else{
            Operation = EnterOperation.getText();
            c.sendMessage(Operation,userName.getText());
            timer.start();
        }
    }

    /**
     * When the button with the text: "See my Operations" is clicked this event occurs and requests the
     * all the expression that the client has sent to the server.
     *
     * @param e Receives a event of action connected to the button of See my Operations.
     * @throws IOException if something wrong happens asking the history or if is not username.
     */
    public void showOperations(ActionEvent e) throws IOException {
        if(userName.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.NONE);
            // set alert type
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please write your username.");
            a.setHeaderText(null);
            // show the dialog
            a.show();
        }else{
            c.sendRequest(userName.getText());
            timerHistory.start();
        }
    }

    // It's a timer to update the GUI with the result of the input expression.
    Timer timer = new Timer (500, new ActionListener()
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Results.setText(c.answer);
            if (!c.answer.equals("")){
                timer.stop(); 
            }
        }
    });

    // It's a timer to update the GUI with all expressions to a specific client according to their username.
    Timer timerHistory = new Timer (500, new ActionListener()
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            myOperations.setText(c.history);
            if (!c.history.equals("")){
                timerHistory.stop(); // it stops when it's done with reading the csv document
            }
        }
    });
}
