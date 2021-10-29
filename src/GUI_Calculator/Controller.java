package GUI_Calculator;

import ClientServerArchitecture.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public String Operation ="";
    public Client c;

    @FXML
    public void initialize() throws IOException {
        c = new Client();
        c.createClient();
    }
    public void returnResult(ActionEvent e) throws IOException, InterruptedException {
        Operation = EnterOperation.getText();
        System.out.println("Operation = " + Operation);
        c.sendMessage(Operation);
        System.out.println("c.answer = " + c.answer);
        timer.start();
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
}
