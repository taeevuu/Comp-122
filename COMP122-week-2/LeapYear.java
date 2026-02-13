public class LeapYear {
  public static void main(String[] args) {

    /**
     * The following reads the first commandline argument.
     *
     * CAUTION! for this to succeed, you will have to run your program as "java LeapYear N",
     * where N is a valid integer literal!
     */
    int year = Integer.parseInt(args[0]);

    boolean leapYear = false; // Your code here
    if ((year%4 == 0 && year%100 != 0 ) || (year%4 == 0 || year %400 == 0)) {;
    leapYear = true;
    }
    // output
    System.out.println(leapYear);
  }
}
