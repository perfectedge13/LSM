import logger.Log;
import test.TestManager;
import test.TestReader;

public class Runner {

	public static void main(String[] args) {
		TestReader reader = new TestReader();
		Log.initialize();
		TestManager testManager = reader.getTests();
		testManager.scheduleTests();

	}

}
