import java.lang.Math;

public class SimpleCircle {
    public static void main (String args[]) {

        double r = 2.5;
        
        // replace the following two lines
        double area = Math.pow(Math.PI*r, 2);
        double circumference = Math.pow(Math.PI*r*2, 2);
  
        System.out.println(r);
        System.out.println(area);
        System.out.println(circumference);
     }
}