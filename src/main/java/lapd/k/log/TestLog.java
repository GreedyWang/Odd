package lapd.k.log;

//import com.foo.Bar;

// Import log4j classes.

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {

    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger logger = LogManager.getLogger(TestLog.class);

    public static void main(final String... args) {

        // Set up a simple configuration that logs on the console.

        logger.info("Entering application.");

        logger.debug("Exiting application.");
    }
}