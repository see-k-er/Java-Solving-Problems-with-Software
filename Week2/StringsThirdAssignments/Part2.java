
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.* ;
public class Part2 {
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
    
    public int countCTG(String dna){
        
        int count = 0;
        int startIndex = 0;
        
        while(true){
            int ctgIndex = dna.indexOf("CTG", startIndex);
            if (ctgIndex != -1){
                count = count + 1;
            }
            else {
                break;
            }
            startIndex = ctgIndex + 3;
        }
        return count;
    }
    
    public void testCGR(){
        System.out.println("************");
        float ex1 = cgRatio("ACGTAACCGGTTAACATGC");
        System.out.println("ACGTAACCGGTTAACATGC - " + ex1);
        
        float ex2 = cgRatio("AACCGGTTAACCGGTT");
        System.out.println("AACCGGTTAACCGGTT - " + ex2);
        
    }
    
    public void testCTG(){
        System.out.println("************");
        int ex1 = countCTG("ACTGAACCTGGTCTGAACATGC");
        System.out.println("ACTGAACCTGGTCTGAACATGC - " + ex1);
        
        int ex2 = countCTG("AACTGCGGTTAACCTGGTT");
        System.out.println("AACTGCGGTTAACCTGGTT - " + ex2);
        
    }
    
    public void testCTGQ6(){
        System.out.println("************");
        FileResource fr = new FileResource("Q3.txt.rtf");
        String dna = fr.asString();
        int ex1 = countCTG(dna);
        System.out.println(ex1);
        
    }
    
    

}
