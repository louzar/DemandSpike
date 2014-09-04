package com.neverwinterdp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neverwinterdp.demandspike.driver.MessageDriverConfig;
import com.neverwinterdp.demandspike.job.DemandSpikeJob;
import com.neverwinterdp.demandspike.job.JobConfig;
import com.neverwinterdp.util.monitor.ApplicationMonitor;
import com.neverwinterdp.util.monitor.snapshot.ApplicationMonitorSnapshot;
import com.neverwinterdp.util.monitor.snapshot.MetricFormater;
import com.neverwinterdp.util.monitor.snapshot.TimerSnapshot;

public class Main {

	public static void main(String[] args) throws IOException {
		  List<String> connect = new ArrayList<String>();
		  connect.add("127.0.0.1:80");
		  JobConfig config = new JobConfig(new MessageDriverConfig("sparkngin", connect,""), 1, 1, 1024, 30000, 1000000, 0);
	      ApplicationMonitor appMonitor = new ApplicationMonitor() ;
	      DemandSpikeJob sender = new DemandSpikeJob(appMonitor, config) ;
	      sender.run();
	      ApplicationMonitorSnapshot snapshot = appMonitor.snapshot() ;
	      Map<String, TimerSnapshot> timers = snapshot.getRegistry().getTimers() ;
	      MetricFormater formater = new MetricFormater("  ") ;    
	      System.out.println(formater.format(timers));
	}

}
