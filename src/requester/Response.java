package requester;

public class Response {
	
	private int responseCode = 0;
	private String responseText = "";
	
	public Response(int responseCode, String responseText) {
		super();
		this.responseCode = responseCode;
		this.responseText = responseText;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
	

}
