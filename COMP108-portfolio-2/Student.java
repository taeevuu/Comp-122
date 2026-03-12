public class Student {
    public boolean hasSubmitted = false;
    public String name  = "";
    public String email = ""; 
    public int yearOfBirth = 0; 
    public int enrolmentYear = 0; 
    public int studentId = 0; 
    public int grade = 0;
    
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

    /**
     * Constructs a student from name, email and a date-of-birth string in the
     * format "dd/mm/yyyy". Only the year is stored; the rest of the fields are
     * left at their default values.
     */
    public Student(String name, String email, String dob) {
        // split on slash and extract year portion
        String[] parts = dob.split("/");
        int year = 0;
        if (parts.length == 3) {
            try {
                year = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                // malformed year, leave as 0
            }
        }
        this.name = name;
        this.email = email;
        this.yearOfBirth = year;
        // enrolmentYear and studentId default to 0
    }
    public void submitCoursework() {
        hasSubmitted = true;
    }
    public void updateGrade(int mark) {
        grade += mark;
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
        Student bob = new Student("Bob", "bob@school.edu", 2000, 2022, 7654321);
        // using overloaded constructor with minimal info
        Student carol = new Student("Carol", "carol@example.com", 2003);
        // historic record using string DOB
        Student dave = new Student("Dave", "dave@oldschool.edu", "01/06/1984");
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(carol);
        System.out.println(dave);
    }

}
