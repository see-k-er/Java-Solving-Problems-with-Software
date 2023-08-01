
/**
 * Write a description of weatherCSV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherCSV {
    
    //1
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour = null;
        for (CSVRecord currentRow: parser){
            if (currentRow.get("TemperatureF").equals("-9999")){
                continue;
            }
            else if (coldestHour == null){
                coldestHour = currentRow;
            }
            else {
                String coldTemp = currentRow.get("TemperatureF");
                double numcoldTemp = Double.parseDouble(coldTemp);
                
                if (numcoldTemp < Double.parseDouble(coldestHour.get("TemperatureF"))){
                    coldestHour = currentRow;
                }
            }
        }
        return coldestHour;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord coldestRow = coldestHourInFile(parser);
        String res = "Coldest Temperature is " + coldestRow.get("TemperatureF") + "degree F @ Time - " + coldestRow.get("TimeEST");
        System.out.println(res);
        
    }
    
    //2
    public File fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String fileName = "";
        CSVRecord coldestRow = null;
        File coldestFile = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord coldestRowInFile = coldestHourInFile(fr.getCSVParser());
            
            double coldestTempInFile = Double.parseDouble(coldestRowInFile.get("TemperatureF"));
            if (coldestRow == null){
                coldestRow = coldestRowInFile;
                coldestFile = f;
            } else {
                double overallColdestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
                
                if (coldestTempInFile < overallColdestTemp){
                    coldestRow = coldestRowInFile;
                    coldestFile = f;
                } else {
                    continue;
                }
                
            }            
        }
        System.out.println("#####" + coldestRow.get("TemperatureF"));
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature(){
        File filePath = fileWithColdestTemperature();
        
        FileResource fr = new FileResource(filePath);
        CSVRecord coldestRowInFile = coldestHourInFile(fr.getCSVParser());
        String coldestTemp = coldestRowInFile.get("TemperatureF");
        
        System.out.println("Coldest day was in file " + filePath.getName());
        System.out.println("Coldest temperature on that day was " + coldestTemp);
        System.out.println("All the temperatures on the coldest day were: ");
        
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rows: parser){
            System.out.println(rows.get("DateUTC") + ": " + rows.get("TemperatureF"));
        }
    }
    
    //3
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumid = null;
        for (CSVRecord values: parser){
            String humidVal = values.get("Humidity");
            if (humidVal.equals("N/A")){
                continue;
            } else if (lowestHumid == null){
                lowestHumid = values;
            } else {
                int humidNum = Integer.parseInt(humidVal);
                if (humidNum < Integer.parseInt(lowestHumid.get("Humidity"))){
                    lowestHumid = values;
                }
            }
        }
        return lowestHumid;
    }
        
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        String humidity = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + time);
    }
    
    //4
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        File lowHumidFile = null;
        CSVRecord lowHumidRow = null;
        
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowHumidInFile = lowestHumidityInFile(parser);
                        
            if (lowHumidRow == null){
                lowHumidRow = lowHumidInFile;
                lowHumidFile = f;
            } else {
                int currentHumidInFile = Integer.parseInt(lowHumidInFile.get("Humidity"));
                int existHumid = Integer.parseInt(lowHumidRow.get("Humidity"));
                
                if (currentHumidInFile < existHumid){
                    lowHumidRow = lowHumidInFile;
                    lowHumidFile = f;
                }
                
            }
        }
        return lowHumidRow;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowHumidManyFiles = lowestHumidityInManyFiles();
        
        String lowestHumidVal = lowHumidManyFiles.get("Humidity");
        String time = lowHumidManyFiles.get("DateUTC");
        
        System.out.println("Lowest Humidity was " + lowestHumidVal + " at " + time);
    }

    
    //5
    public double averageTemperatureInFile(CSVParser parser){
        double totTemp = 0;
        int count = 0;
        for (CSVRecord values: parser){
            double currentTemp = Double.parseDouble(values.get("TemperatureF"));
            totTemp = totTemp + currentTemp;
            count ++;
        }
        
        double avgTemp = totTemp / count;
        return avgTemp;

    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        
        System.out.println("Average temperature in file is " + avgTemp);
    }

    //6
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totTemp = 0;
        int count = 0;
        
        for (CSVRecord temps: parser){
            int getHumidValue = Integer.parseInt(temps.get("Humidity"));
            if (getHumidValue >= value){
                totTemp = totTemp + Double.parseDouble(temps.get("TemperatureF"));
                count ++;
            }
        }
        
        if (totTemp == 0 && count == 0){
            return 0;
            
        }
        
        return totTemp/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if (avgTemp == 0){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }
    }


