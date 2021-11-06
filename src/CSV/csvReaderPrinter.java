package CSV;

import java.io.*;

/**
     * Class in charge of writing on the csv document of each user
     */

public class csvReaderPrinter {
/**
     * the function saves the data for each user and not for all
     */
    public String historyUser(String username) {
        String filepath = username+".csv"; // it makes a new document with the username of the person that's using the calculator
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
                operations += "Your expression: "+temp[0]+"  Result: "+ temp[1]+" Date: "+temp[2]+"\n"; // writes the values for the operation
                tempOperations = "";
            }
            System.out.println(operations);
            if (reader != null){
                try {
                    reader.close(); // if there isn't any operations it stops the function
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return operations;
        } catch (FileNotFoundException f){
            return "There aren't expressions."; // it shows a warning
        } catch(Exception e){
            e.printStackTrace();
        }
        return "There aren't expressions."; // it shows a warning
    }
}
