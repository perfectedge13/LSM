package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import logger.Log;
import util.Time;

public class TestManager {
	
	private HashMap<String, Test> tests;

	public TestManager(HashMap<String, Test> tests) {
		super();
		this.tests = tests;
	}

	public HashMap<String, Test> getTests() {
		return tests;
	}

	public void setTests(HashMap<String, Test> tests) {
		this.tests = tests;
	}
	
	public void scheduleTests() {
		
		try {

			//Launch Test Jobs
			for (Map.Entry<String,Test> entry : tests.entrySet()) {
				
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				
				JobDetail job = JobBuilder.newJob(TestJob.class).withIdentity(entry.getKey() , "group1").usingJobData("test", entry.getKey()).build();
				
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + entry.getKey(), "group1").startNow()
	    	            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
	    						.withIntervalInSeconds(entry.getValue().getFrequency()).repeatForever()).build();
				
	            scheduler.getContext().put("tests", tests);
	            scheduler.getContext().put("manager", this);
	    		scheduler.start();
	    		scheduler.scheduleJob(job, trigger);
			}
			
			//Results Tracking Job
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			JobDetail resultsjob = JobBuilder.newJob(StatusJob.class).withIdentity("results", "group2").build();
			
			Trigger resulttrigger = TriggerBuilder.newTrigger().withIdentity("triggerresults", "group2").startNow()
		            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(15).repeatForever()).build();
			scheduler.start();
			scheduler.scheduleJob(resultsjob, resulttrigger);
			
		}catch(Exception ex) {}

	}
	
	@SuppressWarnings("unchecked")
	public void writeResults() {
		
		JSONObject testRoot = new JSONObject();
		JSONObject testData = new JSONObject();
		
		for (Entry<String, Test> entry : this.tests.entrySet()) {
			
			JSONObject resultVals = new JSONObject();
			resultVals.put("status", entry.getValue().lastResult());
			resultVals.put("time", Time.getFormattedTime(entry.getValue().getLastRun()));

			testData.put(entry.getKey(), resultVals);
			
		}
		testRoot.put("tests", testData);		
		Log.writeJson(testRoot.toJSONString());


	}

}
