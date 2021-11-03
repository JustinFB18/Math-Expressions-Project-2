package CSV;

import java.io.*;

public class csvReaderPrinter {

    public void csvReader() throws IOException {
        String file = "datos.csv";
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                for (String index : row){
                    System.out.printf("%10s", index);
                }
                System.out.println();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            reader.close();
        }
    }

    public static void main(String[] args) throws IOException {
        csvReaderPrinter m = new csvReaderPrinter();
        m.csvReader();
    }
}