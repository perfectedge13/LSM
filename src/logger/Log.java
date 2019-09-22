package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {
	
	private static Logger logger = Logger.getLogger(Log.class.getName());
	private static FileHandler fh;

	public Log() {
		
	}
	
	public static void initialize() {
		
		try {
			
		    ConsoleHandler handler = new ConsoleHandler();
		    handler.setFormatter(new LogFormat());
		    
			fh = new FileHandler("logs/mainlog.log", 100000, 1, true);
			fh.setFormatter(new LogFormat());
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
		    logger.addHandler(handler);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void logInfo(String value) {
		logger.info(value); 
	}
	
	public static void writeJson(String json) {
		
		try (FileWriter file = new FileWriter("logs/status.json")) {
			 
            file.write(json);
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
