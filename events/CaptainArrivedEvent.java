package com.example.sprint2.events;

import com.example.sprint2.user.Client;
import com.example.sprint2.user.Driver;
import com.example.sprint2.user.Ride;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CaptainArrivedEvent extends IEvent{
  private ArrayList<Client> clients = new ArrayList<>();
  private Driver driver;

  public CaptainArrivedEvent(Ride ride, String name, LocalDateTime time){
    clients = ride.getClients();
    this.driver = ride.getDriver();
    this.name = name;
    this.time = time;
  }
  
  @Override
  public String toString(){
    String names = "";
    for (Client c : clients) {
      names += " Client Name: " + c.getUserName() + "\n";
    }

    return "Event Name: " + name + " Event Time: " + time + " Driver Name: " + driver.getUserName() + names;
  }

  public String getEventInfo(){
    return toString();
  }
}
