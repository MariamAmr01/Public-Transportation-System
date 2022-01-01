package com.example.sprint2.events;

import com.example.sprint2.user.Client;

import java.time.LocalDateTime;

public class UserEvent extends IEvent{
  private Client user;

  public UserEvent(Client user, String name, LocalDateTime time){
    this.user = user;
    this.name = name;
    this.time = time;
  }
  @Override
  public String toString(){
    return "Event Name: " + name + " Event Time: " + time + " Client Name: " + user.getUserName() + "\n";
  }
  public String getEventInfo(){
    return toString();
  }
}
