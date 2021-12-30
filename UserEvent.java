import java.time.LocalDateTime;

public class UserEvent extends IEvent{
  private Client user;

  public UserEvent(Client user,String name, LocalDateTime time){
    this.user = user;
    this.name = name;
    this.time = time;
  }

  public String getEventInfo(){
    return "Event Name: " + name + " Event Time: " + time + " Client Name: " + user.getUserName() + "\n";
  }
}
