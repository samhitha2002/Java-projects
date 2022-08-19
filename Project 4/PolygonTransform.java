/*************************************************************************
 *  Compilation:  javac PolygonTransform.java
 *  Execution:    java PolygonTransform
 *
 *  @author: Samhitha Padmanabhini
 *
 *************************************************************************/

public class PolygonTransform {


    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated. 
    public static double[] copy(double[] array) {
        double[] array2 = new double[array.length];
        for(int i = 0; i < array2.length; i++){
            array2[i] = array[i];
        }
        return array2;
    }
    
    // Scales the given polygon by the factor alpha. 
    public static void scale(double[] x, double[] y, double alpha) {
        for (int i = 0; i < x.length ; i++){
            x[i] *= alpha;
        }
        for (int i = 0; i < y.length ; i++){
            y[i] *= alpha;
        }
    }
    
    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for(int i = 0; i < x.length; i++){
            x[i] += dx;
        }
        for(int i = 0; i < y.length; i++){
            y[i] += dy;
        }
    }
    
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
    public static void rotate(double[] x, double[] y, double theta) {
        double[] oldx = copy(x);
        double[] oldy = copy(y);
        double degree = Math.toRadians(theta);
        for (int i = 0; i < x.length; i++){
            x[i] = oldx[i] * Math.cos(degree) - oldy[i] * Math.sin(degree);
            y[i] = oldy[i] * Math.cos(degree) + oldx[i] * Math.sin(degree);
        }
        
    }

    // Tests each of the API methods by directly calling them. 
    public static void main(String[] args) {
        
        // This section is commented out. Include what you want the shape to do (For example: If you want the shape to rotate, then include that part
        // from the below code). The shape can rotate, translate and scales.
        
        //Rotates
        /*StdDraw.setScale(-5.0, +5.0); 
        double[] x = { 0, 1, 1, 0 }; 
        double[] y = { 0, 0, 2, 1 }; 
        double theta = 45.0; 
        StdDraw.setPenColor(StdDraw.RED); 
        StdDraw.polygon(x, y); 
        rotate(x, y, theta); 
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x, y);*/
        
        
        //Translate
        /*StdDraw.setScale(-5.0, +5.0); 
        double[] x = { 0, 1, 1, 0 }; 
        double[] y = { 0, 0, 2, 1 }; 
        double dx = 2.0, dy = 1.0; 
        StdDraw.setPenColor(StdDraw.RED); 
        StdDraw.polygon(x, y); 
        translate(x, y, dx, dy); 
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x, y);*/

        // Scales
        /*StdDraw.setScale(-5.0, +5.0); 
        double[] x = { 0, 1, 1, 0 }; 
        double[] y = { 0, 0, 2, 1 }; 
        double alpha = 2.0; 
        StdDraw.setPenColor(StdDraw.RED); 
        StdDraw.polygon(x, y); 
        scale(x, y, alpha); 
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(x, y);*/
    }
}
