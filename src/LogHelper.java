import java.io.InputStream;
import java.util.logging.*;

/**
 * Provides utility methods for configuring and using Java's built-in logging system. This class simplifies logging
 * setup by automatically loading configuration from a logging.properties file, and provides convenience methods to log
 * messages at different levels.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class LogHelper
{
    private final Logger logger;

    /**
     * Constructs a LogHelper instance, initializing the logger and loading logging configuration from
     * logging.properties.
     */
    public LogHelper(Class<?> clazz)
    {
        getLoggingProperties();
        logger = Logger.getLogger("LogHelper");
    }

    /**
     * Loads the logging configuration from a logging.properties file located in the classpath. Configures the root
     * LogManager with these settings.
     */
    private void getLoggingProperties()
    {
        // Read logging properties from the file
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("logging.properties"))
        {
            LogManager.getLogManager().readConfiguration(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Logs an informational message using the INFO level.
     *
     * @param message The message to log.
     */
    public void logInfoMessage(String message)
    {
        logger.log(Level.INFO, message);
    }

    /**
     * Logs a warning message using the WARNING level.
     *
     * @param message The message to log.
     */
    public void logWarningMessage(String message)
    {
        logger.log(Level.WARNING, message);
    }

    /**
     *
     * @param message
     */
    public void logSevereMessage(String message)
    {
        logger.log(Level.SEVERE, message);
    }
}
