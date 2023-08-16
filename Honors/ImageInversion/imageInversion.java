
/**
 * Write a description of imageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class imageInversion {
    
    //creating a method to convert image into its negative
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        
        //iterating over each pixel
        for (Pixel p: outImage.pixels()){
            //get the corresponding pixels of inImage
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            
            //Find the negative of the pixel
            int redInverse = 255 - inPixel.getRed();
            int greenInverse = 255 - inPixel.getGreen();
            int blueInverse = 255 - inPixel.getBlue();
            
            //Setting the pixels for outImage
            p.setRed(redInverse);
            p.setGreen(greenInverse);
            p.setBlue(blueInverse);
        }
        
        return outImage;
        
    }

    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        
        for (File f: dr.selectedFiles()){
            ImageResource selectImage = new ImageResource(f);
            
            //converting to negative
            ImageResource negImage = makeInversion(selectImage);
            
            //saving image with a new name
            String oldFile = selectImage.getFileName();
            String newFile = "inverted-" + oldFile;
            
            negImage.setFileName(newFile);
            negImage.draw();
            negImage.save();
            
        }
    }
}
