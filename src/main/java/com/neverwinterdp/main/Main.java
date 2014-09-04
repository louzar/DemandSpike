package com.neverwinterdp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neverwinterdp.demandspike.job.send.MessageDriverConfig;
import com.neverwinterdp.demandspike.job.send.MessageSender;
import com.neverwinterdp.demandspike.job.send.MessageSenderConfig;
import com.neverwinterdp.util.monitor.ApplicationMonitor;
import com.neverwinterdp.util.monitor.snapshot.ApplicationMonitorSnapshot;
import com.neverwinterdp.util.monitor.snapshot.MetricFormater;
import com.neverwinterdp.util.monitor.snapshot.TimerSnapshot;

public class Main {

	public static void main(String[] args) throws IOException {
		  List<String> connect = new ArrayList<String>();
		  connect.add("127.0.0.1:80");
		  MessageSenderConfig config = new MessageSenderConfig(new MessageDriverConfig("sparkngin", connect,""), 1, 1, 1024, 30000, 1000000, 0);
	      ApplicationMonitor appMonitor = new ApplicationMonitor() ;
	      MessageSender sender = new MessageSender(appMonitor, config) ;
	      sender.run();
	      ApplicationMonitorSnapshot snapshot = appMonitor.snapshot() ;
	      Map<String, TimerSnapshot> timers = snapshot.getRegistry().getTimers() ;
	      MetricFormater formater = new MetricFormater("  ") ;    
	      System.out.println(formater.format(timers));
	}

}
