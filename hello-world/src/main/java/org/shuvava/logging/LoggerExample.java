package org.shuvava.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerExample {
  //created a logger
  private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
  public static void main(String[] args) throws SecurityException, IOException {

    LOGGER.info("Logger Name: "+LOGGER.getName());

    LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");

    Handler consoleHandler = null;
    Handler fileHandler  = null;
    Handler fileHandlerFormatted  = null;
    Formatter simpleFormatter = null;
    try{
      //Creating consoleHandler and fileHandler
      consoleHandler = new ConsoleHandler();
      // Creating FileHandler
      fileHandler = new FileHandler("./javacodegeeks.log");
      // Creating SimpleFormatter
      simpleFormatter = new SimpleFormatter();
      fileHandlerFormatted = new FileHandler("./javacodegeeks.formatted.log");

      //Assigning handlers to LOGGER object
      LOGGER.addHandler(consoleHandler);
      LOGGER.addHandler(fileHandler);
      LOGGER.addHandler(fileHandlerFormatted);

      // Logging message of Level info (this should be publish in the default format i.e. XMLFormat)
      LOGGER.info("Finnest message: Logger with DEFAULT FORMATTER");

      // Setting formatter to the handler
      fileHandlerFormatted.setFormatter(simpleFormatter);

      //Setting levels to handlers and LOGGER
      consoleHandler.setLevel(Level.ALL);
      fileHandler.setLevel(Level.ALL);
      fileHandlerFormatted.setLevel(Level.ALL);
      LOGGER.setLevel(Level.ALL);

      LOGGER.config("Configuration done.");
      // Logging message of Level finest (this should be publish in the simple format)
      LOGGER.finest("Finnest message: Logger with SIMPLE FORMATTER");

      //Console handler removed
      LOGGER.removeHandler(consoleHandler);
      read("test.txt", "^*$");

      LOGGER.log(Level.FINE, "Finer logged");
    }catch(IOException exception){
      LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
    }

    LOGGER.finer("Finest example on LOGGER handler completed.");

  }

  public static int read(String file, String pattern) {
    LOGGER.entering("com.mycompany.mylib.Reader", "read",
      new Object[] { file, pattern });
    LOGGER.exiting("com.mycompany.mylib.Reader", "read", 1);
    return 1;
  }
}
