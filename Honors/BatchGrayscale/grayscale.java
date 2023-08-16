
/**
 * Write a description of grayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.* ;
import java.io.* ;

public class grayscale {
    //Given an image, convert it to grayscale and return it
    public ImageResource convertToGrayscale(ImageResource inImage){
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        ImageResource outImage = new ImageResource(width, height);
        
        //accessing each pixel in outImage and updating its value
        for (Pixel p: outImage.pixels()){
            //getting the pixels in inImage
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            
            //averaging the RGB pixels
            int avgPixel = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            
            //setting the pixel value in outImage to avgPixel
            p.setRed(avgPixel);
            p.setGreen(avgPixel);
            p.setBlue(avgPixel);
        }
        
        return outImage;
        
    }
    
    public void selectandConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource inFile = new ImageResource(f);
            //convert to grayscale
            ImageResource grayImage = convertToGrayscale(inFile);
            String currFileName = inFile.getFileName();
            String newFileName = "gray-"+currFileName;
            
            //store the converted image in a new file
            grayImage.setFileName(newFileName);
            grayImage.draw();
            grayImage.save();
        }
        
    }

}
