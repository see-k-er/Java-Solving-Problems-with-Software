
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
    String findSimpleGene (String dna){
        
        // Check for start codon
        int startCodonInd = dna.indexOf("ATG");
        if (startCodonInd == -1){
            return "";
        }
        
        int stopCodonInd = dna.indexOf("TAA");
        if (stopCodonInd == -1){
            return "";
        }
       
        String gene = dna.substring(startCodonInd, stopCodonInd + 3);
        int geneLen = gene.length();
        if (geneLen % 3 == 0){
            return gene;
        } else {
            return "";
        }
        
    }
    
    
    
    void testSimpleGene(){
        
        System.out.println("\n *********************** \n");

        // DNA with ATG and TAA and multiple not 3
        String dna1 = "AAATGCTGATTGCCCTAACACTGAATTG";
        System.out.println(dna1);
        String gene1 = findSimpleGene(dna1);
        System.out.println("Gene 1 is - " + gene1);
       
        //DNA with no ATG
        String dna2 = "AACTGCCCTAACACTGAATTG";
        System.out.println(dna2);
        String gene2 = findSimpleGene(dna2);
        System.out.println("Gene 2 is - " + gene2);
        
        //DNA with no TAA
        String dna3 = "AAATGCTGATTGCCCCACTGAATTG";
        System.out.println(dna3);
        String gene3 = findSimpleGene(dna3);
        System.out.println("Gene 3 is - " + gene3);
        
        //DNA with ATG and TAA and multiple is 3 
        String dna4 = "AAATGCTGATTGCCTAACACTGAATTG";
        System.out.println(dna4);
        String gene4 = findSimpleGene(dna4);
        System.out.println("Gene 4 is - " + gene4);
        
        //DNA with no ATG or TAA
        String dna5 = "AACTGCCCCACTGAATTG";
        System.out.println(dna5);
        String gene5 = findSimpleGene(dna5);
        System.out.println("Gene 5 is - " + gene5);
        
    }

}

