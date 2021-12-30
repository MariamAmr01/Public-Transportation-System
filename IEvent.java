import java.time.LocalDateTime;

public abstract class IEvent{
  protected String name;
  protected LocalDateTime time;

  abstract public String getEventInfo(); 

  public void setName(String name){
    this.name = name;
  }

  public void setDateTime(LocalDateTime time){
    this.time = time;
  }

  public String getName(){
    return name;
  }
  public LocalDateTime getTime(){
    return time;
  }
}