public class Largest {
    public static void main(String[] args) {
        System.out.print("Length of Array:");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = scanner.nextInt();

        // your code here...
        int[] myArray = new int[n];

        for(int i = 0; i < n; i++) {
            System.out.print("Enter an integer:");
            myArray[i] = scanner.nextInt();
        }

        int largestValue = myArray[0];

        for (int q = 1; q < n; q++) {
            if (myArray[q] > largestValue) {
                largestValue = myArray[q];
            }
        }

        // end of your code...
        System.out.println(largestValue);
    }
}