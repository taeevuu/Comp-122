import java.util.Scanner;

public class VGather {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How Many Students in Class?");
        int numStudents = scanner.nextInt();
        
        int[] grades = new int[numStudents];
        
        for (int i = 0; i < numStudents; i++) {
            // prompt only the text, then read the grade on the next line
            System.out.println("Enter a grade:");
            grades[i] = scanner.nextInt();
        }
        
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        
        double average = sum / numStudents;
        System.out.println(average);
        
        scanner.close();
    }
}
