package test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;

public class StatusJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			
			TestManager manager = (TestManager) (schedulerContext.get("manager"));
			manager.writeResults();

			
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
		
	}

}
