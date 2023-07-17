import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;       
        for (Point p: s.getPoints()){
            count = count + 1;   
        }       
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        int cnt = getNumPoints(s);
        
        double avgLen = length/cnt;  
        
        return avgLen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Follow a similar code to that of finding perimeter, but we will do a comparison to check
        // the max side
        Point prevPt = s.getLastPoint();
        double largest = 0.0;
        
        for (Point curPt: s.getPoints()){
            double dis = prevPt.distance(curPt);
            if (dis >= largest){
                largest = dis; 
            }
            prevPt = curPt; 
        }  
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largX = 0.0;
        
        for (Point p: s.getPoints()){
            int pointX = p.getX();
            if (pointX > largX){
                largX = pointX;
            }
            
        }
        return largX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largPeri = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePeri = getPerimeter(s);
            if (shapePeri > largPeri){
                largPeri = shapePeri;
            }
        }
        return largPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largPeri = 0.0;  
        File temp = null;    // replace this code
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePeri = getPerimeter(s);
            if (shapePeri > largPeri){
                largPeri = shapePeri;
                temp = f;            
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int cnt = getNumPoints(s);
        System.out.println("Number of points = " + cnt);
        
        double avgLen = getAverageLength(s);
        System.out.println("Average Length = " + avgLen);
        
        double largSide = getLargestSide(s);
        System.out.println("Largest Side = " + largSide);
        
        double largX = getLargestX(s);
        System.out.println("Largest X = " + largX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        String largPeri = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter is: " + largPeri);
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        double largPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter Across Files: " + largPeri);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}

