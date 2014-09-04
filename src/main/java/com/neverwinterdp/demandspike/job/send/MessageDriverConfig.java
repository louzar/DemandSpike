package com.neverwinterdp.demandspike.job.send;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.neverwinterdp.util.monitor.ApplicationMonitor;

public class MessageDriverConfig implements Serializable {

	String driver = "sparkngin";

	Map<String, String> driverProperties = new HashMap<String, String>();

	List<String> connect = new ArrayList<String>();

	String topic;

	public MessageDriverConfig(String driver, List<String> connect,
			String topic, Map<String, String> driverProperties) {
		this(driver, connect, topic);
		this.driverProperties = driverProperties;

	}

	public MessageDriverConfig(String driver, List<String> connect, String topic) {
		this.driver = driver;
		this.connect = connect;
		this.topic = topic;
	}

	public String getDriver() {
		return this.driver;
	}

	public MessageDriver createDriver(ApplicationMonitor appMonitor) {
		MessageDriver mdriver = null;
		if ("kafka".equals(driver)) {
			mdriver = new KafkaMessageDriver(appMonitor);
		} else if ("sparkngin".equals(driver)) {
			if (connect.size() == 0) {
				connect.add("127.0.0.1:80");
			}
			mdriver = new HttpSparknginMessageDriver(appMonitor);
		} else {
			mdriver = new DummyMessageDriver(appMonitor);
		}
		mdriver.init(driverProperties, connect, topic);
		return mdriver;
	}
}