public class Student {
    public boolean hasSubmitted = false;
    public String name  = "";
    public String email = ""; 
    public int yearOfBirth = 0; 
    public int enrolmentYear = 0; 
    public int studentId = 0; 
    public int grade = 0;
    public void submitCoursework() {
        hasSubmitted = true;
    }
    public void updateGrade(int mark) {
        grade += mark;
    }
}
