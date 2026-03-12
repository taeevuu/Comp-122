public import java.util.Scanner;

public class VGather {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("How many students in class?");
        int numStudents = scanner.nextInt();
        
        int[] grades = new int[numStudents];
        
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter a grade:" + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
        }
        
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        
        double average = sum / numStudents;
        System.out.printf("Class average: %.2f\n", average);
        
        scanner.close();
    }
}
 {
    
}
