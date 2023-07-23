
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        //returns index of first stopCodon after startIndex and multiple of 3.
        // no stopCodon, return length of dna
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        while (stopIndex != -1){
            int diff = stopIndex - startIndex;
            if (diff % 3 == 0){
                return stopIndex;
            } else {
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }

        //return dna.length();
        return -1;
           
    }
    
    
    void testFindStopCodon (){
        //Testing different cases
        
        System.out.println("**************************");
        //                        ^                ^  
        int ex1 = findStopCodon("AATGTCATTGCACCGGTACTAA",1,"TAA");
        System.out.println("AATGTCATTGCACCGGTACTAA");
        System.out.println(ex1);
        
        int ex2 = findStopCodon("AATGTCATTGCACCGGTACTAG",1,"TAG");
        System.out.println("AATGTCATTGCACCGGTACTAA");
        System.out.println(ex2);
        
        int ex3 = findStopCodon("AATGTCATTGCACCGGTACTGA",1,"TGA");
        System.out.println("AATGTCATTGCACCGGTACTGA");
        System.out.println(ex3);
        
        int ex4 = findStopCodon("AATGTCATTGCACCGGTAACTGA",1,"TGA");
        System.out.println("AATGTCATTGCACCGGTAACTGA");
        System.out.println(ex4);
        
        int ex5 = findStopCodon("AATGTCATTGCACCGGTCAAC",1,"TGA");
        System.out.println("AATGTCATTGCACCGGTCAAC");
        System.out.println(ex5);
        
        
    }
    
    public String findGene(String dna, int where){
        //Find index of start codon "ATG", return empty otherwise
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        
        //Find index of stop codon "TAA" after "ATG"
        int taaStopIndex = findStopCodon(dna, startIndex,"TAA");

        //Find index of stop codon "TAG" after "ATG"
        int tagStopIndex = findStopCodon(dna, startIndex,"TAG");

        //Find index of stop codon "TGA" after "ATG"
        int tgaStopIndex = findStopCodon(dna, startIndex,"TGA");

        //Find the nearest stopCodon index
        //compare between "TAA" and "TAG"
  
        int temp = 0;
        int minIndex =0;
        //temp = Math.min(taaStopIndex, tagStopIndex);
        //minIndex = Math.min(temp, tgaStopIndex);
        //if (minIndex == dna.length()){
            //return "";  
        //}
        
        //making comparison between taa and tga
        if (taaStopIndex == -1 || (tgaStopIndex != -1 && tgaStopIndex < taaStopIndex)){
            minIndex = tgaStopIndex;
        } else {
            minIndex = taaStopIndex;
        }
        
        //comparing between minIndex and tag
        if (minIndex == -1 || (tagStopIndex != -1 && tagStopIndex < minIndex)){
            minIndex = tagStopIndex;
        } 
        
        if (minIndex == -1){
            return "";  
        }

        return dna.substring(startIndex, minIndex+3);
        }
        
        
    void testFindGene(){
        
        System.out.println("**************************");
        //dna with start codon and one valid stop codon - TAA
        String ex1 = findGene("AATGTCATTGCACCGGTACTAA",0);
        System.out.println("AATGTCATTGCACCGGTACTAA - " + ex1);
        //no valid stop codon
        String ex2 = findGene("AATGTCATTGCACCGGTCAAC",0);
        System.out.println("AATGTCATTGCACCGGTCAAC - " + ex2);
        //no start codon ATG
        String ex3 = findGene("ATCATTGCACCGGTCTAAC",0);
        System.out.println("ATCATTGCACCGGTCAAC - " + ex3);
        //dna with multiple valid stop codon with ATG
        String ex4 = findGene("AATGTCATTGCACCGGTACTAAACGTAGCCTAAGTGAAACG",0);
        System.out.println("AATGTCATTGCACCGGTACTAAACGTAGCCTAAGTGAAACG - " + ex4);

    }
    
    void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String gene = findGene(dna, startIndex);
            
            if (gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene,startIndex) + gene.length();
        }
        
        
    }
    
    StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true){
            String gene = findGene(dna, startIndex);
            // Adding the gene to the storageresource sr
            sr.add(gene);
            if (gene.isEmpty()){
                break;
            }
            startIndex = dna.indexOf(gene,startIndex) + gene.length();
        }

        return sr;
        
    }
    
    public void testAllGenes(){
        
        System.out.println("*************");
        StorageResource ex1 = getAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        
        for (String s : ex1.data()){
            System.out.println(s);
        };
        
        StorageResource ex2 = getAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        
        for (String s : ex2.data()){
            System.out.println(s);
        };

    }
    
    
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        
    }

}
