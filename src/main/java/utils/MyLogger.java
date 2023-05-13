package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.Scenario;

public class MyLogger {

    private static final String CALL_INFO_FORMAT = "%s:%s %s ==> ";
    private static final String EXECUTION_START_MESSAGE = "%s%n%n************** Execution Started : %s **************%n";
    private static final String EXECUTION_END_MESSAGE = "%s%n%n************** Execution End : %s **************%n";
    private static final String REGULAR_EXPRESSION = "[^a-zA-Z0-9]";
    private static Logger logger = LogManager.getLogger();

    private MyLogger() {
        // Private constructor to hide the implicit public one
    }

    public static synchronized void startTestCase(Scenario scenario) {
        if (logger.isInfoEnabled()) {
            String sTestCaseName = scenario.getName().replaceAll(REGULAR_EXPRESSION, "_").replaceAll("_+", "_");
            info(String.format(EXECUTION_START_MESSAGE, getCallInfo(), sTestCaseName));
        }
    }

    public static synchronized void startTestCase(String sTestCaseName) {
        if (logger.isInfoEnabled()) {
            sTestCaseName = sTestCaseName.replaceAll(REGULAR_EXPRESSION, "_").replaceAll("_+", "_");
            info(String.format(EXECUTION_START_MESSAGE, getCallInfo(), sTestCaseName));
        }
    }

    public static void endTestCase(Scenario scenario) {
        if (logger.isInfoEnabled()) {
            String sTestCaseName = scenario.getName().replaceAll(REGULAR_EXPRESSION, "_").replaceAll("_+", "_");
            info(String.format(EXECUTION_END_MESSAGE, getCallInfo(), sTestCaseName));
        }
    }

    public static Logger getCurrentLog() {
        return logger;
    }

    public static String getCallInfo() {

        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        return String.format(CALL_INFO_FORMAT, className, methodName, lineNumber);
    }

    public static void trace(String message) {
        if (logger.isTraceEnabled()) {
            getCurrentLog().trace(message);
        }
    }

    public static void trace(String message, Throwable t) {
        if (logger.isTraceEnabled()) {
            getCurrentLog().trace(message, t);
        }
    }

    public static void debug(String message) {
        if (logger.isDebugEnabled()) {
            getCurrentLog().debug(String.format("%s%s", getCallInfo(), message));
        }
    }

    public static void debug(String message, Throwable t) {
        if (logger.isDebugEnabled()) {
            getCurrentLog().debug(String.format("%s%s", getCallInfo(), message), t);
        }
    }

    public static void error(String message) {
        if (logger.isErrorEnabled()) {
            getCurrentLog().error(String.format("%s%s", getCallInfo(), message));
        }
    }

    public static void error(String message, Throwable t) {
        if (logger.isErrorEnabled()) {
            getCurrentLog().error(String.format("%s%s", getCallInfo(), message), t);
        }
    }

    public static void fatal(String message) {
        if (logger.isFatalEnabled()) {
            getCurrentLog().fatal(String.format("%s%s", getCallInfo(), message));
        }
    }

    public static void fatal(String message, Throwable t) {
        if (logger.isFatalEnabled()) {
            getCurrentLog().fatal(String.format("%s%s", getCallInfo(), message), t);
        }
    }

    public static void info(String message) {
        if (logger.isInfoEnabled()) {
            getCurrentLog().info(String.format("%s%s", getCallInfo(), message));
        }
    }

    public static void info(String message, Throwable t) {
        if (logger.isInfoEnabled()) {
            getCurrentLog().info(String.format("%s%s", getCallInfo(), message), t);
        }
    }

    public static void warn(String message) {
        if (logger.isWarnEnabled()) {
            getCurrentLog().warn(String.format("%s%s", getCallInfo(), message));
        }
    }

    public static void warn(String message, Throwable t) {
        if (logger.isWarnEnabled()) {
            getCurrentLog().warn(String.format("%s%s", getCallInfo(), message), t);
        }
    }
}