package CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
     *  Class in charge of writing on the csv document of each user
     */

public class WriteOnCSV {

    // the function allows to save the data required: expression, result and date
    public static void saveRecord(String Expression, String Result, String filepath) {
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(fw);  // parameters required to write on the document

            String Date = String.valueOf(LocalDate.now()); // writes today's date
            Date = Date.substring(2);  // cuts the date so the hours don't appear
            pw.println(Expression+","+Result+","+Date);  // it writes the data on the csv document
            pw.flush();
            pw.close();

        }
        catch(Exception E){
            System.out.println("Error writing on file");  // if there's any issue it shows and advertising
        }

    }
}
