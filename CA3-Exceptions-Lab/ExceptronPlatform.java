import java.io.IOException;
import java.sql.SQLException;

/**
 * Runs an Exceptron through all its steps and collects any exception messages.
 */
public class ExceptronPlatform {

    /**
     * Runs the Exceptron through each step of its scenario.
     * Caught exceptions have their messages collected into a string.
     * If an IOException with message "FATAL ERROR" is encountered it is thrown out.
     * RuntimeExceptions are also thrown out immediately.
     * If no exceptions occur at all, returns "Everything's Fine".
     * The Exceptron is always shut down in a finally block when finished.
     *
     * @param exceptron the Exceptron instance to run
     * @return a string of collected exception messages, or "Everything's Fine\n"
     * @throws IOException if a fatal IO error is encountered
     */
    public String runExceptron(Exceptron exceptron) throws IOException {
        String messages = "";
        try {
            for (int i = 0; i < exceptron.getScenarioLength(); i++) {
                try {
                    exceptron.doSomeStuff();
                } catch (IOException ioe) {
                    if (ioe.getMessage().equals("FATAL ERROR")) {
                        throw ioe;
                    }
                    messages += ioe.toString() + "\n";
                } catch (SQLException e) {
                    messages += e.toString() + "\n";
                } catch (SuperCoolException e) {
                    messages += e.toString() + "\n";
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    messages += e.toString() + "\n";
                }
            }
        } finally {
            exceptron.goodBye();
        }

        if (messages.equals("")) {
            return "Everything's Fine\n";
        }
        return messages;
    }

    /**
     * Main method for testing scenarios directly.
     *
     * @param args command line arguments (not used)
     * @throws IOException if a fatal IO error occurs
     */
    public static void main(String[] args) throws IOException {
        int[] scenarios = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        /*
         * Scenarios by index:
         * 0 - Exception
         * 1 - NumberFormatException
         * 2 - SQLException
         * 3 - ArrayIndexOutOfBoundsException
         * 4 - SuperCoolException
         * 5 - Exception, IOException, SQLException, IOException, Exception, IOException
         * 6 - Exception, IOException, SQLException, IOException, Exception, IOException, ArrayIndexOutOfBoundsException, IOException
         * 7 - Exception, IOException, SQLException, IOException, Exception, IOException, SQLException, IOException, ArrayIndexOutOfBoundsException
         * 8 - Should return Everything's Fine\n
         */
        ExceptronPlatform ex = new ExceptronPlatform();
        //for (int i = 0; i < scenarios.length; i++) {
            System.out.println(ex.runExceptron(new Exceptron(0)));
        //}
    }
}
