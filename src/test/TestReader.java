package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class TestReader {
	
	
	public TestManager getTests() {
		
		HashMap<String, Test> tests = new HashMap<String, Test>();
		
		JSONParser parser = new JSONParser();

		try {
			
	
			InputStream is = getClass().getResourceAsStream("/src/data/tests.json");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
	        JSONObject jsonObject = (JSONObject) parser.parse(br);
	        JSONObject jsontests = (JSONObject) jsonObject.get("tests");
	        
	        for(@SuppressWarnings("unchecked")
			Iterator<String> iterator = jsontests.keySet().iterator(); iterator.hasNext();) {
	            String key = (String) iterator.next();
	            tests.put(key, new Test(key, (JSONObject) jsontests.get(key)));
	        }
	        
	        
		}catch(Exception ex) {ex.printStackTrace();}
		
		return new TestManager(tests);

	}

}
