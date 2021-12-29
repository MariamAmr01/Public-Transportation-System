import java.time.LocalDateTime;

public class CaptianOfferEvent extends IEvent {
  private Driver driver;
  private int price;

  public CaptianOfferEvent(Ride ride,int price,String name, LocalDateTime time){
    this.driver = ride.getDriver();
    this.price = price;
    this.name = name;
    this.time = time;
  }

 public String getEventInfo(){
   return "Event Name: " + name + " Event Time: " + time + " Driver Name: " + driver.getUserName() + " Price: " + price + " \n";

 }
}
