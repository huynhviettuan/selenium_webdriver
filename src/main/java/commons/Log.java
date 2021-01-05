package commons;

import com.relevantcodes.extentreports.LogStatus;
import commons.report.ExtentTestManager;
import org.apache.log4j.Logger;

public class Log {

    static Logger logger = Logger.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
