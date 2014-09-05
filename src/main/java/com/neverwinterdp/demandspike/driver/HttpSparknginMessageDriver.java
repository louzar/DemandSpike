package com.neverwinterdp.demandspike.driver;

import java.util.List;
import java.util.Map;

import com.codahale.metrics.Timer;
import com.neverwinterdp.demandspike.http.HttpMessageClient;
import com.neverwinterdp.demandspike.http.Message;
import com.neverwinterdp.util.monitor.ApplicationMonitor;
import com.neverwinterdp.util.monitor.ComponentMonitor;

public class HttpSparknginMessageDriver implements MessageDriver {
  private ApplicationMonitor appMonitor ;
  private ComponentMonitor   driverMonitor ;
  private String topic ;
  private HttpMessageClient client ;
  
  public HttpSparknginMessageDriver(ApplicationMonitor appMonitor) {
    this.appMonitor = appMonitor ;
    driverMonitor   = this.appMonitor.createComponentMonitor(HttpSparknginMessageDriver.class) ;
  }
  
  public void init(Map<String, String> props, List<String> connect, String topic) {
    this.topic = topic ;
    try {
      for(String selConnect : connect) {
        int separatorIdx = selConnect.lastIndexOf(":") ;
        String host = selConnect.substring(0, separatorIdx) ;
        int port = Integer.parseInt(selConnect.substring(separatorIdx + 1));
        client = new HttpMessageClient (host, port, 300) ;
      }
    } catch(Exception ex) {
      throw new RuntimeException("Sparkngin Driver Error", ex) ;
    }
  }
  
  
  public void close() { 
    client.close();
  }

public void send(Message message) throws Exception {
	// TODO Auto-generated method stub
	Timer.Context ctx = driverMonitor.timer("send(Message)").time() ;
    message.getHeader().setTopic(topic);
    client.send(message, 15000);
    ctx.stop() ;
}


}