package com.example.sprint2.events;

import com.example.sprint2.user.Driver;

import java.time.LocalDateTime;

public class CaptianOfferEvent extends IEvent {
  private Driver driver;
  private int price;

  public CaptianOfferEvent(Driver driver, int price, String name, LocalDateTime time){
    this.driver = driver;
    this.price = price;
    this.name = name;
    this.time = time;
  }
  @Override
  public String toString(){
    return "Event Name: " + name + " Event Time: " + time + " Driver Name: " + driver.getUserName() + " Price: " + price + " \n";
  }
 public String getEventInfo(){
  return toString();
 }
}
