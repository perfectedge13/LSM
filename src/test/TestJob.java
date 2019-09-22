package test;

import java.util.HashMap;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;

public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			
			@SuppressWarnings("unchecked")
			HashMap<String, Test> tests = (HashMap<String, Test>) (schedulerContext.get("tests"));
			tests.get(dataMap.getString("test")).test();
			
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
		
	}

}
