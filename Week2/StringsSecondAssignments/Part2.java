/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    
    int howMany(String stringa, String stringb){
        int startIndex = 0;
        int count = 0;
        
        while (true){
            startIndex = stringb.indexOf(stringa, startIndex);
            if (startIndex == -1){
                break;
            } else {
                count = count + 1;
            }
            startIndex = startIndex + stringa.length();
        }
        return count;
    }
    
    public void testHowMany(){
        int ex1 = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("GAA, ATGAACGAATTGAATC - " + ex1);
        int ex2 = howMany("AA", "ATAAAA");
        System.out.println("AA,ATAAAA - " + ex2);
        int ex3 = howMany("GAC", "ATGAACGAATTGAATC");
        System.out.println("GAC, ATGAACGAATTGAATC - " + ex3);
    }
        
        

}
