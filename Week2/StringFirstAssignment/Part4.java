import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    
    
    void checkURL(){
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : url.words()){
            //System.out.println(word); 
            String lowerCase = word.toLowerCase();
            int yt = lowerCase.indexOf("youtube.com");
            if (yt != -1){
                int startQuote = word.lastIndexOf("\"",yt);
                int endQuote = word.indexOf("\"",yt+11);
                System.out.println(word.substring(startQuote+1, endQuote));   
            }
        }
    }
    
   

}

