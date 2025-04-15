import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHelper
{
    public static Logger getLogger(Class<?> clazz)
    {
        Logger logger = Logger.getLogger(clazz.getName());
        boolean hasFileHandler = false;

        for (Handler handler : logger.getHandlers())
        {
            if (handler instanceof FileHandler)
            {
                hasFileHandler = true;
                break;
            }
        }
        if (!hasFileHandler)
        {
            try
            {
                Handler fileHandler = new FileHandler("blackjack.log", 0, 1, true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
                logger.setUseParentHandlers(false);
            }
            catch (IOException e)
            {
                System.err.println("Could not create log file: " + e.getMessage());
            }
        }

        return logger;
    }
}