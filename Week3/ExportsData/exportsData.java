
/**
 * Write a description of exportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.StringJoiner;

public class exportData {
    
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String res = countryInfo(parser, "Nauru");
        System.out.println(res);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "fish", "nuts");
        
        parser = fr.getCSVParser();
        int countryCount = numberOfExporters(parser, "sugar");
        System.out.println(countryCount);
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        
        String result = "";
        
        for (CSVRecord values: parser){
            String nameCountry = values.get("Country");
            if (nameCountry.equalsIgnoreCase(country)){
                String nameExport = values.get("Exports");
                String nameValue = values.get("Value (dollars)");
                
                StringJoiner joiner = new StringJoiner(": ");
                joiner.add(nameCountry).add(nameExport).add(nameValue);
                result = joiner.toString();
                return result;
            } else {
                result = "NOT FOUND";          
            }
        }
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        
        for (CSVRecord values: parser){
            String items = values.get("Exports");
            
            if (items.contains(exportItem1) && items.contains(exportItem2)){
                String country = values.get("Country");
                System.out.println(country);
            }  
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord values: parser){
            String exports = values.get("Exports");
            if (exports.contains(exportItem)){
                count ++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord values: parser){
            String amtValue = values.get("Value (dollars)");
            
            if (amtValue.length() > amount.length()){
                String country = values.get("Country");
                String res = country + " " + amtValue;
                System.out.println(res);
            }
        }
    }
    
    
}



