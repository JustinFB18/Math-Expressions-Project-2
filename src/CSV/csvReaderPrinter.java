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

    public String historyUser(String username) {
        String filepath = username+".csv";
        BufferedReader reader = null;
        String line = "";
        String operations = "";
        String tempOperations = "";
        try{
            reader = new BufferedReader(new FileReader(filepath));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                for (String index : row){
                    tempOperations += index+",";
                }
                //System.out.println(tempOperations);
                String[] temp = tempOperations.split(",");
                operations += "Your expression: "+temp[0]+"  Result: "+ temp[1]+" Date: "+temp[2]+"\n";
                tempOperations = "";
            }
            System.out.println(operations);
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return operations;
        } catch (FileNotFoundException f){
            return "No tiene historial";
        } catch(Exception e){
            e.printStackTrace();
        }
        return "No hay historial";
    }

    public static void main(String[] args) throws IOException {
        csvReaderPrinter m = new csvReaderPrinter();
        //m.csvReader();
        m.historyUser("datos");
    }
}