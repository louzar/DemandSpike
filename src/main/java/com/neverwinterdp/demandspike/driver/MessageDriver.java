package com.neverwinterdp.demandspike.driver;

import java.util.List;
import java.util.Map;

import com.neverwinterdp.demandspike.http.Message;


public interface MessageDriver {
  public void init(Map<String, String> props, List<String> connect, String topic) ;
  public void send(Message message) throws Exception ;
  public void close() ;
}