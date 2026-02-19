public class Newton {
  public static void main(String[] args) {
    // Read in values (assume valid positive inputs)
    if (args.length != 2) {
      System.out.println("Usage: java Newton <n> <guess>");
      return;
    }

    double n = Double.parseDouble(args[0]);
    double guess = Double.parseDouble(args[1]);

    double result = sqRoot(n, guess);
    System.out.println(result);
  }

  public static double sqRoot(double n, double guess) {
    if (n == 0) return 0.0;
    double tolerance = 1e-15;
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
