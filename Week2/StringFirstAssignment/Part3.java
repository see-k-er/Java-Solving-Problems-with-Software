
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    boolean twoOccurrences(String a, String b) {
        int first = b.indexOf(a);
        if ( first == -1){
            return false;
        }
        
        int sec = b.indexOf(a,first + a.length());
        if (sec == -1){
            return false;
        } else {
            return true;
        }
    }

    
    String lastPart(String a, String b){
        int firstOcc = b.indexOf(a);
        if (firstOcc == -1){
            return b;
        } else {
            int start = firstOcc + a.length();
            String rest = b.substring(start);
            return rest;
        }
    }    
    
    
    
    
    void testing() {

        System.out.println("*******************");

        boolean ex1 = twoOccurrences("by", "A story by Abby Long");
        System.out.println("A story by Abby Long - " + ex1);
        
        boolean ex2 = twoOccurrences("a", "banana");
        System.out.println("banana - " + ex2);
        
        boolean ex3 = twoOccurrences("atg", "ctgtatgta");
        System.out.println("ctgtatgta - " + ex3);
        
        System.out.println("#######################");
        
        String ex4 = lastPart("zoo", "forest");
        System.out.println(ex4);
        
        String ex5 = lastPart("an", "banana");
        System.out.println(ex5);       
        
        
    }

    
    
        

}

