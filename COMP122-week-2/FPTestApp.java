public class FPTestApp {
    public static void main (String args[]) {
        double x = 0.05;
        double y = x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x; 
        int value = (int) y;
        double doubley = (double) value;
        System.out.println(x); 
        System.out.println(doubley);
     } 
} 