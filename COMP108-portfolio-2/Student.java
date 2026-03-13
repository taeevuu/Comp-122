public class Student {
    // all attributes made private to prevent direct modification
    private boolean hasSubmitted = false;
    private String name = "";
    private String email = ""; 
    private int yearOfBirth = 0; 
    private int enrolmentYear = 0; 
    private int studentId = 0; 
    private int grade = 0;
    
    public Student() {
    }

    public Student(String name, String email, int yearOfBirth, int enrolmentYear, int studentId) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.enrolmentYear = enrolmentYear;
        this.studentId = studentId;
    }

    public Student(String name, String email, int yearOfBirth) {
        this(name, email, yearOfBirth, 0, 0);
    }

    public Student(String name, String email, String dob) {
        String[] parts = dob.split("/");
        int year = 0;
        if (parts.length == 3) {
            try {
                year = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                // leave as 0 if format is bad
            }
        }
        this.name = name;
        this.email = email;
        this.yearOfBirth = year;
    }

    public void updateGrade(int newGrade) {
        // check if grade is within valid bounds
        if (newGrade < 0 || newGrade > 100) {
            System.out.println("Enter a grade from 0-100.");
            return;
        }
        
        // set grade and mark as submitted
        this.grade = newGrade;
        this.hasSubmitted = true;
    }

    // --- Getters ---

    public boolean getHasSubmitted() {
        return hasSubmitted;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getEnrolmentYear() {
        return enrolmentYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", enrolmentYear=" + enrolmentYear +
                ", studentId=" + studentId +
                ", grade=" + grade +
                ", hasSubmitted=" + hasSubmitted +
                '}';
    }

    public static void main(String[] args) {
        Student alice = new Student("Alice", "aliceXtreme@aol.com", 1984, 2021, 1234567);
        // testing the new updateGrade method
        alice.updateGrade(85);
        System.out.println(alice.getName() + " got a " + alice.getGrade());
        
        alice.updateGrade(150); // should trigger the error message
    }
}