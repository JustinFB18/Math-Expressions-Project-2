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
import java.util.Random;

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

    @FXML
    public void initialize() throws IOException {
        c = new Client();
        c.createClient();
    }
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
            System.out.println("Operation = " + Operation);
            c.sendMessage(Operation,userName.getText());
            System.out.println("c.answer = " + c.answer);
            timer.start();
        }
    }

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

    Timer timerHistory = new Timer (500, new ActionListener()
    {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            myOperations.setText(c.history);
            if (!c.history.equals("")){
                timerHistory.stop();
            }
        }
    });
}
