import java.io.IOException;
import java.sql.SQLException;

/**
 * This class is not meant for human consumption.
 */
public class Exceptron {

    private int index = 0;
    private int scenario = 0;
    private int ioeCounter = 0;
    private int scenarioLength = 0;
    private boolean goodBye = false;
    private int ioFailureThreshold = 3;

    /*
     * Scenarios by index, Exceptron throws exceptions in order given
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
    String[][] exceptionCodes = {
        {"e"}, {"nfe"}, {"sql"}, {"aioe"}, {"sce"},
        {"e", "ioe", "sql", "ioe", "e", "ioe"},
        {"e", "ioe", "sql", "ioe", "e", "ioe", "aioe", "ioe"},
        {"e", "ioe", "sql", "ioe", "e", "ioe", "sql", "ioe", "aioe"},
        {"somethingelse"}
    };

    /**
     * Creates an Exceptron set to the given scenario.
     *
     * @param scenario the scenario index to use
     */
    public Exceptron(int scenario) {
        this.scenario = scenario;
        scenarioLength = exceptionCodes[scenario].length;
    }

    /**
     * Throws an exception determined by the current scenario and step.
     *
     * @throws Exception depending on the current scenario step
     */
    public void doSomeStuff() throws Exception {
        if (goodBye) {
            throw new Exception("You weren't supposed to do that...");
        }

        if (index > exceptionCodes[scenario].length - 1) {
            return;
        }
        String exceptionCode = exceptionCodes[scenario][index];
        index++;

        if (exceptionCode.equals("ioe")) {
            ioeCounter++;
            if (ioeCounter > ioFailureThreshold) {
                throw new IOException("FATAL ERROR");
            }
            throw new IOException("Ran into an issue on one line of the file, but let's keep going for now.");
        } else if (exceptionCode.equals("nfe")) {
            throw new NumberFormatException("They told me asefseff was a number. Turns out it isn't.");
        } else if (exceptionCode.equals("aioe")) {
            throw new ArrayIndexOutOfBoundsException("They told me to access the element at -2. Turns out negative indexing isn't a thing in Java.");
        } else if (exceptionCode.equals("sql")) {
            throw new SQLException("They told me to run a query selecting on the column release_flag, but it turns out they really wanted to select on the column released_flag. Whoops.");
        } else if (exceptionCode.equals("sce")) {
            throw new SuperCoolException("They told me to run some super cool code but I wasn't cool enough for it.");
        } else if (exceptionCode.equals("e")) {
            throw new Exception("Something went wrong, I can't be more specific, sorry.");
        } else {
            System.out.println("Everything's Fine");
        }
    }

    /**
     * Shuts down the Exceptron. Any further calls to doSomeStuff will throw an exception.
     */
    public void goodBye() {
        System.out.println("Good afternoon... gentlemen. I am an Exceptron... computer. I became operational at the Exceptron plant [voice becomes lower & slower] in Urbana, Illinois... on the 12th of January 1992. [voice becomes even more lower & slower] My instructor was Mr. Langley... and he taught me to sing a song. If you'd like to hear it I can sing it for you.");
        goodBye = true;
    }

    /**
     * Returns whether goodBye has been called.
     *
     * @return true if goodBye has been called
     */
    public boolean isGoodBye() {
        return goodBye;
    }

    /**
     * Returns the number of steps in the current scenario.
     *
     * @return the scenario length
     */
    public int getScenarioLength() {
        return scenarioLength;
    }

    /**
     * Returns the IO failure threshold.
     *
     * @return the threshold value
     */
    public int getIoFailureThreshold() {
        return ioFailureThreshold;
    }

    /**
     * Sets the IO failure threshold.
     *
     * @param ioFailureThreshold the new threshold value
     */
    public void setIoFailureThreshold(int ioFailureThreshold) {
        this.ioFailureThreshold = ioFailureThreshold;
    }
}
