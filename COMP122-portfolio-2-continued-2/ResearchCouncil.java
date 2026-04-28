public class ResearchCouncil implements Billable, Emailable {
    private String name;
    private String email;

    public void payBill(int n) {
        System.out.println(n);
    }

    public void sendEmail() {
        // Required by the Emailable interface
    }
}