package com.neverwinterdp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.neverwinterdp.demandspike.driver.MessageDriverConfig;
import com.neverwinterdp.demandspike.job.DemandSpikeJob;
import com.neverwinterdp.demandspike.job.JobConfig;
import com.neverwinterdp.demandspike.job.scheduler.DemandSpikeJobScheduler;
import com.neverwinterdp.util.monitor.ApplicationMonitor;

public class MainScheduler {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		ApplicationMonitor appMonitor = new ApplicationMonitor();
		DemandSpikeJobScheduler jobScheduler = new DemandSpikeJobScheduler();
		jobScheduler.start();
		List<String> connect = new ArrayList<String>();
		connect.add("127.0.0.1:80");
		JobConfig config = new JobConfig(
				new MessageDriverConfig("sparkngin", connect, ""), 1, 1, 1024,
				30000, 1000000, 0);

		DemandSpikeJob job = new DemandSpikeJob(appMonitor, config);
		jobScheduler.submit(job, 10000);
		jobScheduler.stop();

	}

}
