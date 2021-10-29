package CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteOnCSV {


    public static void main (String[] args) {

        String Expression = "1+2";
        String Result = "3";
        String Date = "28/10/21";
        String filepath = "datos.csv";

        saveRecord(Expression, Result, Date, filepath);
    }

    public static void saveRecord(String Expression,String Result,String Date,String filepath) {
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(Expression+","+Result+","+Date);
            pw.flush();
            pw.close();

        }
        catch(Exception E){
            System.out.println("Error writing on file");
        }

    }
}
