import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public class Client extends User implements Account {
  private Ride ride;
  //============= New ==========
  private Date birthday;

  public User logIn(String name, String pass){
    Client c;
    for(int i = 0 ; i < SystemApp.getObj().getDataBase().getClients().size(); i++){
      c = SystemApp.getObj().getDataBase().getClients().get(i);
      if(c.getUserName().equals(name) && c.getPassword().equals(pass)){
        return c;
      }  
    }
    return null;
  }

  //1) userName    2)password  3) mobile  4) Date
  public Boolean register(ArrayList<String> info){
    this.userName = info.get(0);
    this.password = info.get(1);
    this.mobilePhone = info.get(2);

    // BirthDay
    try {
      this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(info.get(3));
    } catch (ParseException e) {
      e.printStackTrace();
    }  


    for(int i = 0 ; i < SystemApp.getObj().getDataBase().getClients().size(); i++){
      if(SystemApp.getObj().getDataBase().getClients().get(i).getUserName().equals(this.userName)){
        System.out.println("This userName is already exits. Registration failed.");
        return false;
      }
    }
    System.out.println("Registration succeeded.");
    SystemApp.getObj().getDataBase().getClients().add(this);
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
      //

      SystemApp.getObj().getDataBase().addRide(r);

      // notify and wait the offers from drivers (if the driver is available)
      for (Driver driver : drivers) {
        if(driver.getAvailable()){
          SystemApp.getObj().notifyDriver(driver);
        }        
      }
      // edit
      //System.out.println(drivers);
     }

  }
/// ================== New ============================
  // Ride begin 
  public void acceptOffer(int offerIndex){

    Driver d = this.ride.getOffers().get(offerIndex-1).getDriver();
    LocalDateTime time =  LocalDateTime.now();
    IEvent event = new UserEvent(this, "User accepts the captain price", time);
    ride.addEvent(event);
    
    d.setAvailable(false);
    d.setRide(this.ride);
    this.ride.setDriver(d);

    d.arrive(time);
  }

  public String getNotification(){
//    if(ride!= null && ride.getPrice()>0)
//      return "There is an offer for a ride with price " + ride.getPrice() + " has been added\n"+ ride.toString();
//    return "There is no offer for a ride yet\n";
    if(ride!= null ) {
      if (this.ride.getOffers() != null)
        return this.ride.getOffers().toString();
    }
   return "no offers";
  }
  public Ride getRide(){

    return ride;
  }
  @Override
  public String toString(){

    return "Client: " + userName + "\nMobile Phone: " + mobilePhone+"\n";
  }

  public Date getBirthday(){
    return birthday;
  }
}
