public class RunTests {
  public static void main(String[] args) {
    System.out.println("pow tests:");
    System.out.println(StringApp.pow("abc",3).equals("abcabcabc"));
    System.out.println(StringApp.pow("x",0).equals(""));
    System.out.println(StringApp.pow("foo",1).equals("foo"));

    System.out.println("factorCount tests:");
    System.out.println(StringApp.factorCount("helloworld hellomoon hellosun hello lamp post","hello") == 4);
    System.out.println(StringApp.factorCount("ababa","aba") == 1);
    System.out.println(StringApp.factorCount("Hellohello","hello", false) == 2);
    System.out.println(StringApp.factorCount("Hellohello","hello", true) == 1);

    System.out.println("main letter count test (input='abcabc'):");
    String[] arr = {"abcabc"};
    StringApp.main(arr);
  }
}
