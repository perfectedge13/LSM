package requester;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requester {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public Response request(String url) {
		
		Response response = null;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer responseText = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				responseText.append(inputLine);
			}
			in.close();

			response = new Response(responseCode, responseText.toString());
		}catch(Exception ex) {}
		
		return response;
	}

}
