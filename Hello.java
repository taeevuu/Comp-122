public class Hello {
    public static void main(String[] args) {
        System.out.println("Please enter your name:");                 // print some instructions
        java.util.Scanner scanner = new java.util.Scanner(System.in);  // connect to the keyboard
        String name = scanner.nextLine();                              // get input
        System.out.println("Hello " + name + "!");
    }
}
