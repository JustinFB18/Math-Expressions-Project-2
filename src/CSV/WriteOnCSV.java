package CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class WriteOnCSV {

    public static void saveRecord(String Expression, String Result, String filepath) {
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(fw);

            String Date = String.valueOf(LocalDate.now());
            Date = Date.substring(2);
            pw.println(Expression+","+Result+","+Date);
            pw.flush();
            pw.close();

        }
        catch(Exception E){
            System.out.println("Error writing on file");
        }

    }
}
