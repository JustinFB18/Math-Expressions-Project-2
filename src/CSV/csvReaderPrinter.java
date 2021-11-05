package CSV;

import java.io.*;

public class csvReaderPrinter {

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
            return "There aren't expressions.";
        } catch(Exception e){
            e.printStackTrace();
        }
        return "There aren't expressions.";
    }
}