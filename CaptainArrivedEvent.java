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
  public String getEventInfo(){

    String names = "";
    for (Client c : clients) {
      names += " Client Name: " + c.getUserName() + "\n";
    }

    return "Event Name: " + name + " Event Time: " + time + " Driver Name: " + driver.getUserName() + names;
  }
}
