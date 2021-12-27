import java.time.LocalDateTime;
import java.util.ArrayList;

public class Client extends User implements Account {
  private Ride ride;

  public User logIn(String name, String pass){
    Client c;
    for(int i = 0 ; i < SystemApp.getObj().clientList.size(); i++){
      c = SystemApp.getObj().clientList.get(i);
      if(c.getUserName().equals(name) && c.getPassword().equals(pass)){
        return c;
      }  
    }
    return null;
  }

  //1) userName    2)password  3) mobile
  public Boolean register(ArrayList<String> info){
    this.userName = info.get(0);
    this.password = info.get(1);
    this.mobilePhone = info.get(2);

    for(int i = 0 ; i < SystemApp.getObj().clientList.size(); i++){
      if(SystemApp.getObj().clientList.get(i).getUserName().equals(this.userName)){
        System.out.println("This userName is already exits. Registration failed.");
        return false;
      }
    }
    System.out.println("Registration succeeded.");
    SystemApp.getObj().clientList.add(this);
    return true;
  }


  public void rateDriver(int rate){
    this.ride.getDriver().rate.add(rate);
    this.ride.setRated(true);
    SystemApp.getObj().setAverageRate(this.ride.getDriver());

  }

  public void requestRide(String source, String destination){

    ArrayList<Driver> drivers = new ArrayList<Driver>();
    drivers=SystemApp.getObj().findDriver(source);
     if(drivers==null)
     {
       //edit
       System.out.println("No Driver found in this area");
     }
     else
     {
       Ride r= new Ride(this, null, source, destination);
       this.ride=r;
      //  d.getRides().add(r);
      //  d.getNewRides().add(r);

      // notify and wait the offers from drivers
      for (Driver driver : drivers) {
        SystemApp.getObj().notifyDriver(driver);
        
      }
      // edit
      System.out.println(drivers);


     }

  }
/// ================== New ============================
  // Ride begin 
  public void acceptOffer(int offerIndex){
    Driver d = this.ride.getOffers().get(offerIndex).getDriver();

    IEvent event = new UserEvent(this, "User accepts the captain price", LocalDateTime.now());
    ride.addEvent(event);
    
    d.setAvailable(false);
    d.setRide(ride);
    this.ride.setDriver(d);
  }

  public String getNotification(){
    if(ride!= null && ride.getPrice()>0)
      return "There is an offer for a ride with price " + ride.getPrice() + " has been added\n"+ ride.toString();
    return "There is no offer for a ride yet\n";
  }
  public Ride getRide(){

    return ride;
  }
  @Override
  public String toString(){

    return "Client: " + userName + "\nMobile Phone: " + mobilePhone+"\n";
  }

}
