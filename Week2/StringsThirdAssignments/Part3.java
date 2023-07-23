
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.* ;

public class Part3 {
    
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
    
    public float cgRatio(String dna){
        int count = 0;
        for (int i=0; i < dna.length(); i++){
            char c = dna.charAt(i);
            if (c == 'C' || c == 'G'){
                count ++;
            }
        }   
        float cgr = (float)count/dna.length();   
        return cgr;
    }
    
    
    public void processGenes(StorageResource sr){
        int longNine = 0;
        int cgrCount = 0;
        int maxLength = 0;
        for (String s: sr.data()){
            if(s.length() > 60){
                System.out.println("Longer than 60 characters: " + s);
                longNine ++;
            }
            
            if (cgRatio(s) > 0.35){
                System.out.println("C-G ratio greater than 0.35: " + s);
                cgrCount ++;
            }
            //to find longest gene
            maxLength = Math.max(maxLength, s.length());
        }
        
        System.out.println("The total number of genes: " + sr.size());
        System.out.println("Number of genes in sr longer than 60 characters - " + longNine);
        System.out.println("Number of genes in sr with cgr higher than 0.35 - " + cgrCount);
        System.out.println("Longest gene length - " + maxLength);
        
    }
    
    public StorageResource findAllGenes(String dna){
        StorageResource sampleSR = new StorageResource();
        int startIndex = 0;
        while (true){
            String gene = findGene(dna.toUpperCase(), startIndex);
            System.out.println(gene);
            
            if (gene.isEmpty()){
                break;
            }
            sampleSR.add(gene);
            startIndex = dna.indexOf(gene,startIndex) + gene.length();
        }
        
        return sampleSR;
    }

    public void testProcessGenes(){
        
        FileResource fr = new FileResource("brca1line.fa.txt");
        //FileResource fr = new FileResource("Q3.txt.rtf");
        String dna = fr.asString();
        
        StorageResource sampleSR = findAllGenes(dna);
        processGenes(sampleSR);
    }

}
