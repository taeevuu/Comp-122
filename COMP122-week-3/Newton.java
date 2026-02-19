public class Newton {
  public static void main(String[] args) {
    // Read in values (assume valid positive inputs)
    if (args.length != 2 && args.length != 3) {
      System.out.println("Usage: java Newton <n> <guess> [tolerance]");
      return;
    }

    double n = Double.parseDouble(args[0]);
    double guess = Double.parseDouble(args[1]);
    double tolerance = 0.0000001; // default original tolerance
    if (args.length == 3) {
      tolerance = Double.parseDouble(args[2]);
    }

    double result = sqRoot(n, guess, tolerance);
    System.out.println(result);
  }

  public static double sqRoot(double n, double guess, double tolerance) {
    if (n == 0) return 0.0;
    double next;
    int maxIter = 1000;
    int iter = 0;
    // Use Newton's method with a while loop until successive guesses converge
    while (true) {
      next = (guess + n / guess) / 2.0;
      if (Math.abs(next - guess) < tolerance || iter++ >= maxIter) {
        return next;
      }
      guess = next;
    }
  }
}
