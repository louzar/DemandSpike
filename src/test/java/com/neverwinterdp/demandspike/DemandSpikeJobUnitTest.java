package com.neverwinterdp.demandspike;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neverwinterdp.demandspike.driver.MessageDriverConfig;
import com.neverwinterdp.demandspike.job.DemandSpikeJob;
import com.neverwinterdp.demandspike.job.JobConfig;
import com.neverwinterdp.util.monitor.ApplicationMonitor;

public class DemandSpikeJobUnitTest {
	@Test
	public void testNormalTask() throws Exception {
		ApplicationMonitor appMonitor = new ApplicationMonitor();
		List<String> connect = new ArrayList<String>();
		connect.add("127.0.0.1:80");
		JobConfig config = new JobConfig(
				new MessageDriverConfig("sparkngin", connect, ""), 1, 1024,
				30000, 1000000, 0);

		DemandSpikeJob job = new DemandSpikeJob("",appMonitor, config);
		job.run();

	}

	@Test
	public void testPeriodicTask() {
		ApplicationMonitor appMonitor = new ApplicationMonitor();
		List<String> connect = new ArrayList<String>();
		connect.add("127.0.0.1:80");
		JobConfig config = new JobConfig(
				new MessageDriverConfig("sparkngin", connect, ""), 1, 1024,
				30000, 1000000, 0);

		DemandSpikeJob job = new DemandSpikeJob("",appMonitor, config);
		job.run();
	}
}
