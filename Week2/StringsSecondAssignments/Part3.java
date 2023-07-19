/**
 * Write a description of Part3 here.
 * 
 * @author 
 * @version (a version number or a date)
 */
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
        
    
    int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true){
            String gene = findGene(dna, startIndex);
            
            if (gene.isEmpty()){
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(gene,startIndex) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes(){
        System.out.println("**Counting Genes**");
        int ex1 = countGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        System.out.println("ATGATCTAATTTATGCTGCAACGGTGAAGA - " + ex1);
        int ex2 = countGenes("ATGATCTTTATGCTGCAACGGAGA");
        System.out.println("ATGATCTTTATGCTGCAACGGAGA - " + ex2);
        int ex3 = countGenes("");
        System.out.println(" - " + ex3);
        int ex4 = countGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        System.out.println("ATGATCATAAGAAGATAATAGAGGGCCATGTAA - " + ex4);
        int ex5 = countGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAACCGATGATCTAA");
        System.out.println("ATGATCATAAGAAGATAATAGAGGGCCATGTAACCGATGATCTAA - " + ex5);
    }
    
}
