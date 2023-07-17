
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    
    String findSimpleGene (String dna, int startCodonInd, int stopCodonInd){
        
        // convert all to uppercase for checking
        String dnaGene = dna.toUpperCase();
        
        // Check for start codon
        startCodonInd = dnaGene.indexOf("ATG");
        if (startCodonInd == -1){
            return "";
        }
        
        stopCodonInd = dnaGene.indexOf("TAA");
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
        String gene1 = findSimpleGene(dna1, 1, 1);
        System.out.println("Gene 1 is - " + gene1);
       
        //DNA with no ATG
        String dna2 = "AACTGCCCTAACACTGAATTG";
        System.out.println(dna2);
        String gene2 = findSimpleGene(dna2, 1, 1);
        System.out.println("Gene 2 is - " + gene2);
        
        //DNA with no TAA
        String dna3 = "AAATGCTGATTGCCCCACTGAATTG";
        System.out.println(dna3);
        String gene3 = findSimpleGene(dna3, 2, 1);
        System.out.println("Gene 3 is - " + gene3);
        
        //DNA with ATG and TAA and multiple is 3 
        String dna4 = "AAATGCTGATTGCCTAACACTGAATTG";
        System.out.println(dna4);
        String gene4 = findSimpleGene(dna4, 3, 4);
        System.out.println("Gene 4 is - " + gene4);
        
        //DNA with no ATG or TAA
        String dna5 = "AACTGCCCCACTGAATTG";
        System.out.println(dna5);
        String gene5 = findSimpleGene(dna5, 6, 7);
        System.out.println("Gene 5 is - " + gene5);
        
        String dna6 = "AAATGCTGATTGCCTAACACTGAATTG";
        //converting to all lowercase
        String lowdna6 = dna6.toLowerCase();
        System.out.println(lowdna6);
        String gene6 = findSimpleGene(lowdna6, 3, 4);
        System.out.println("Gene 6 is - " + gene6);
        
        
        String dna7 = "aaatgctgattgcctaacactgaattg";
        //converting to all lowercase
        String updna7 = dna7.toUpperCase();
        System.out.println(updna7);
        String gene7 = findSimpleGene(updna7, 3, 4);
        System.out.println("Gene 7 is - " + gene7);
       
        
    }

}

