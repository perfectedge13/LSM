package test;

import org.json.simple.JSONObject;

import logger.Log;
import requester.Requester;
import requester.Response;
import util.Email;
import util.Time;

public class Test{
	
	private String testname = "";
	private String url = "";
	private String expected = "";
	private int frequency = -1;
	private long lastRun = 0;
	private boolean lastResult = true;
	private Response lastResponse = null;
	
	public Test(String testname, JSONObject values) {
		
		this.testname = testname;
		this.url = (String) values.get("url");
		this.expected = (String) values.get("expected");
		this.frequency = Integer.parseInt((String) values.get("frequency"));
		
	}
	
	
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExpected() {
		return expected;
	}
	public void setExpected(String expected) {
		this.expected = expected;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Response getLastResponse() {
		return lastResponse;
	}
	public void setLastResponse(Response lastResponse) {
		this.lastResponse = lastResponse;
	}
	public long getLastRun() {
		return lastRun;
	}
	public void setLastRun(long lastRun) {
		this.lastRun = lastRun;
	}
	public boolean lastResult() {
		return lastResult;
	}
	public void setLastResult(boolean lastResult) {
		this.lastResult = lastResult;
	}
	public void test() {
		
		Log.logInfo("Testing: " + this.getTestname());
		Log.logInfo("Connecting to: " + this.getUrl());
		Requester requester = new Requester();
		Response response = requester.request(this.getUrl());
		Log.logInfo(this.getTestname() + " Response code: " + response.getResponseCode());
		this.setLastResponse(response);
		this.setLastRun(Time.currentEpoch());
		
		if(response.getResponseCode() == 200 && response.getResponseText().indexOf(this.getExpected()) != -1) {
			this.setLastResult(true);
		}else {
			this.setLastResult(false);
		}
		
		//Email.sendAlert();
		
	}



}
