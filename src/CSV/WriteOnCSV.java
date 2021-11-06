package CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
     * Clase encargada de escribir los datos en el documento csv de cada usuario
     */

public class WriteOnCSV {

    // la función permite guardar los tres datos que se nos solicita: expresión, resultado y fecha
    public static void saveRecord(String Expression, String Result, String filepath) {
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(fw);  // parámetros que nos permiten escribir en el archivo

            String Date = String.valueOf(LocalDate.now()); // escribe la fecha del día en que se haga la operación
            Date = Date.substring(2);  // corta la fecha para que no aparezca lahora
            pw.println(Expression+","+Result+","+Date);  // aquí escribe los tres datos necesarios
            pw.flush();
            pw.close();

        }
        catch(Exception E){
            System.out.println("Error writing on file");  // en caso de haber un error nos da un aviso
        }

    }
}
