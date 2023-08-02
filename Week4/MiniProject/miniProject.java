
/**
 * Write a description of miniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class miniProject {
    //1
    public void totalBirths(FileResource fr){
        CSVParser parser = fr.getCSVParser(false);//false here indicates that there is no header in the CSV
        int totBirths = 0;
        int totBoys = 0;
        int totGirls = 0;
        int girlsCount = 0;
        int boysCount = 0;
        
        for (CSVRecord values: parser){
            //accessing the CSVRecord using index as there is no header
            int birthNum = Integer.parseInt(values.get(2));
            totBirths += birthNum;
            
            String gender = values.get(1);
            if (gender.equals("M")){
                //incremenet the totBoys;
                totBoys += birthNum;
                boysCount += 1;
            } else {
                totGirls += birthNum;
                girlsCount += 1;
            }
        }
        
        System.out.println("Total Births - " + totBirths);
        System.out.println("Total Girls - " + totGirls);
        System.out.println("Total Boys - " + totBoys);
        System.out.println("Total Girls Count- " + girlsCount);
        System.out.println("Total Boys Count - " + boysCount);

    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        
        totalBirths(fr);
    }

    //2
    public int getRank(int year, String name, String gender){
        String filePath = "yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord values: parser){
            String Name = values.get(0);
            String Gender = values.get(1);
            String Num = values.get(2);
            
            if (Gender.equals(gender)){
                rank += 1;
                if (Name.equals(name)){
                    return rank;  
                } else {
                    continue;
                }
            }
        }
        
        return -1;
    }
    
    public void testGetRank(){
         int ex1 = getRank(2012, "Ava", "F");
         System.out.println("Rank for Ava, F is - " + ex1);
         int ex2 = getRank(2012, "Noah", "M");
         System.out.println("Rank for Noah, M is - " + ex2);
         int ex3 = getRank(1971, "Frank", "M");
         System.out.println("Rank for Frank, M is - " + ex3);
         int ex4 = getRank(1960, "Emily", "F");
         System.out.println("Rank for Emily, F is - " + ex4);
    }
    
    //3
    public String getName(int year, int rank, String gender){
        String filePath = "yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        int Rank = 0;
        
        for (CSVRecord values: parser){
            String Gender = values.get(1);
            String Name = values.get(0);
            
            if (Gender.equals(gender)){
                Rank += 1;
                if (Rank == rank){
                    return Name;
                } else {
                    continue;
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        String ex1 = getName(2012, 5, "F");
        System.out.println("Rank 5, F is - " + ex1);
        String ex2 = getName(1982, 450, "M");
        System.out.println("Rank 450, M is - " + ex2);
        String ex3 = getName(1980, 350, "F");
        System.out.println("Rank for 350, F is - " + ex3);

    }
    
    //4
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String currentYearFile = "yob" + Integer.toString(year) + ".csv";
        String newYearFile = "yob" + Integer.toString(newYear) + ".csv";
        
        int nameRank = getRank(year,name,gender);
        String newName = getName(newYear,nameRank,gender);
        
        String gen = "";
        if (gender.equals("M")){
            gen = "he";
        } else {
            gen = "she";
        }
        
        System.out.println(name + " born in " + year + " would be " + newName + " if " + gen + " was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
        whatIsNameInYear("Noah", 2014, 2012, "F");
    }
    
    //5
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestYear = Integer.MIN_VALUE;
        int highestRank = Integer.MAX_VALUE;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            
            int currentRank = getRank(year,name,gender);
            if (currentRank != -1 && currentRank < highestRank){
                highestRank = currentRank;
                highestYear = year;
            }
        }
        
        if (highestYear == Integer.MIN_VALUE){
            return -1;
        } else {
        return highestYear;
        }
    }
    
    public void testYearOfHighestRank(){
        String name = "Genevieve";
        String gender = "F";
        int yearHighestRank = yearOfHighestRank(name,gender);
        
        System.out.println("The year in which the name - " + name + " (" + gender + ") was very popular is " + yearHighestRank);
        
        String name1 = "Mich";
        String gender1 = "M";
        int yearHighestRank1 = yearOfHighestRank(name1,gender1);
        
        System.out.println("The year in which the name - " + name1 + " (" + gender1 + ") was very popular is " + yearHighestRank1);
    }
    
    public int yearOfHighestRank1(String name, String gender) {
        int year = Integer.MIN_VALUE;
        int rank = Integer.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            
            if (currentRank != -1 && currentRank < rank) {
                rank = currentRank;
                year = currentYear;
            }
            
        } 
        
        if (year == Integer.MIN_VALUE) {
            return -1;
        } else {
            return year;
        }
    }
    
    
    //6
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double totRank = 0.0;
        double count = 0.0;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            
            int currentRank = getRank(year,name,gender);
            totRank += currentRank;
            count += 1;
        }
        
        if (totRank < 0){
            return -1.0;
        } else {
            double avgRank = totRank/count;
            return avgRank;
        }
        
    }
    
    public void testGetAverageRank(){
        String name1 = "Robert";
        String gender1 = "M";
        double avgRank1 = getAverageRank(name1, gender1);
        System.out.println("The average rank for - " + name1 + " (" + gender1 + ") is " + avgRank1);
        
        String name2 = "Susan";
        String gender2 = "F";
        double avgRank2 = getAverageRank(name2, gender2);
        System.out.println("The average rank for - " + name2 + " (" + gender2 + ") is " + avgRank2);
    }
    
    
    //7
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String filePath = "yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser(false);
        
        int givenRank = getRank(year,name,gender);
        int totBirthsHigherThanGivenRank = 0;
        int rank = 0;
        
        for (CSVRecord values: parser){
            String currentGender = values.get(1);
            if (currentGender.equals(gender)){
                rank += 1;
                if (rank < givenRank){
                    totBirthsHigherThanGivenRank += Integer.parseInt(values.get(2));
                }
                
            }

        }
        return totBirthsHigherThanGivenRank;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        String Name = "Emily";
        String Gender = "F";
        int Year = 1990;
        int totBirthsRankedHigher = getTotalBirthsRankedHigher(Year, Name, Gender);
        System.out.println("Total births ranked higher than " + Name + " ("+ Gender + ") in " + Year + " is " + totBirthsRankedHigher);
    
        String Name1 = "Drew";
        String Gender1 = "M";
        int Year1 = 1990;
        int totBirthsRankedHigher1 = getTotalBirthsRankedHigher(Year1, Name1, Gender1);
        System.out.println("Total births ranked higher than " + Name1 + " ("+ Gender1 + ") in " + Year1 + " is " + totBirthsRankedHigher1);
    }
}
