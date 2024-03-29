package com.example.sprint2.user;

import com.example.sprint2.discount.*;
import com.example.sprint2.events.*;
import com.example.sprint2.system.SystemApp;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Client extends User implements Account {
  private Ride ride;
  private String birthDate;
  private LocalDate birthday;
  private int requiredSeats;
  private int accAdditionalPass;
  private String accAdditional;
 private double discountedPrice;

 public Client ()
 {

 }

 public Client(String userName,String password, String mobilePhone, String birthDate)
 {
     this.userName=userName;
     this.password=password;
     this.mobilePhone=mobilePhone;
     this.birthDate=birthDate;
     String str[] = birthDate.split("/");
     int day, month, year;
     day = Integer.parseInt(str[0]);
     month = Integer.parseInt(str[1]);
     year = Integer.parseInt(str[2]);
     this.birthday = LocalDate.of(year, month, day);
 }

  public User logIn(String name, String pass){
    Client c;
    for(int i = 0; i < SystemApp.getObj().getDataBase().getClients().size(); i++){
      c = SystemApp.getObj().getDataBase().getClients().get(i);
      if(c.getUserName().equals(name) && c.getPassword().equals(pass)){
          return c;
      }  
    }
    return null;
  }
  //1) userName    2)password  3) mobile  4) Date
  public Boolean register( ArrayList<String> info){
    this.userName = info.get(0);
    this.password = info.get(1);
    this.mobilePhone = info.get(2);

    String str[] = info.get(3).split("/");
    int day, month, year;
    day = Integer.parseInt(str[0]);
    month = Integer.parseInt(str[1]);
    year = Integer.parseInt(str[2]);
    this.birthday = LocalDate.of(year, month, day);


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


  public String rateDriver(int rate){
    if(rate >= 1 && rate <= 5){
      this.ride.getDriver().rate.add(rate);
      this.ride.setRated(true);
      SystemApp.getObj().setAverageRate(this.ride.getDriver());
      return "Done ☺\n";
    }
    return "invalid input";
  }

  public String requestRide(String source, String destination, String passengers, String accAdditionalPass){
    boolean createRide = true;

    ArrayList<Driver> drivers = new ArrayList<Driver>();

    this.accAdditional=accAdditionalPass;
    this.accAdditionalPass=Integer.parseInt(accAdditional);
    String passenger= passengers;
    requiredSeats=Integer.parseInt(passenger)+this.accAdditionalPass;
    drivers=SystemApp.getObj().findDriver(source,requiredSeats);

     if(drivers.size()==0)
     {
       return "No Driver found in this area";
     }
     else
     {
       for (Driver driver : drivers) {
         if(driver.getRide()!=null && !driver.getRide().getCompleted())
         {
           if(driver.getRide().getSource().equals(source) && this.accAdditionalPass!=0) {
             createRide = false;
             this.ride = driver.getRide();
             this.ride.addClient(this);

             int remainingSeat = driver.getAvailableSeat() - Integer.parseInt(passenger);
             driver.setAvailableSeat(remainingSeat);

             SystemApp.getObj().notifyDriver(driver);
             try {
               IDiscount plainPrice = new AdminAreaDiscount(new BirthdayDiscount(new TwoPassengersDiscount(new PublicHolidayDiscount(new FirstRideDiscount(new PlainPrice())))));
               Offer of = null;
               for (int i=0; i<this.ride.getOffers().size();i++)
               {
                 if(this.ride.getOffers().get(i).getDriver().equals(driver))
                 {
                   of=this.ride.getOffers().get(i);
                 }
               }

               discountedPrice = plainPrice.applyDiscount(of.getPrice(), this.ride);

               if(discountedPrice != of.getPrice())
                 System.out.println(displayDiscount());
               else System.out.println(of.getPrice());

             } catch (ParseException e) {
               e.printStackTrace();
             }
               return "Drivers notified wait for offers";
           }
         }
       }
       if(createRide){
        Ride r= new Ride(this, null, source, destination);
        this.ride=r;
        SystemApp.getObj().getDataBase().addRide(this.ride);
       }
       for (Driver driver : drivers) {
         if(createRide && (driver.getRide()==null||driver.getRide().getCompleted())){
          SystemApp.getObj().notifyDriver(driver);
         }
       }
       return "Drivers notified wait for offers";


     }

  }

  // Ride begin 
  public void acceptOffer(int offerIndex) {
    ArrayList<Offer> offers = new ArrayList<>();
 
    for (Offer of : this.ride.getOffers()) {
      if (this.ride.getDriver() == null || this.ride.getDriver().getRide().getCompleted()){
        offers.add(of);
      }
    }
    Driver d = offers.get(offerIndex-1).getDriver();

    LocalDateTime time =  LocalDateTime.now();
    IEvent event = new UserEvent(this, "User accepts the captain price", time);
    ride.addEvent(event);

    if(accAdditionalPass==0)
    {
      d.setAvailableSeat(0);
    }

    else
    {
      d.setAvailableSeat(accAdditionalPass);
    }

    this.ride.setDriver(d);
    d.setRide(this.ride);
    d.arriveToLocation(time);

    
    try {
      IDiscount plainPrice = new AdminAreaDiscount(new BirthdayDiscount(new TwoPassengersDiscount(new PublicHolidayDiscount(new FirstRideDiscount(new PlainPrice())))));
      discountedPrice = plainPrice.applyDiscount(offers.get(offerIndex-1).getPrice(), this.ride);

      if(discountedPrice != offers.get(offerIndex-1).getPrice())
          System.out.println(displayDiscount());
    } 
    catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public String displayDiscount()
  {
    return "price after discount: " + discountedPrice;
  }
  
  public String getNotification() {

  ArrayList<Offer> offers = new ArrayList<>();

  if(ride!= null ) {
    if (this.ride.getOffers().size() > 0)
    {
      for (Offer of : this.ride.getOffers()) {
        if (this.ride.getDriver() == null &&(of.getDriver().getRide()==null||of.getDriver().getRide().getCompleted()) ){
          offers.add(of);
        }
      }
      return offers.toString();
    }

  }

   return "no offers";
  }
  public Ride getRide(){

    return ride;
  }
  public String getBirthDate()
  {
      return birthDate;
  }
  public LocalDate getBirthday(){
    return birthday;
  }

  public int getAccAdditionalSeats(){
    return accAdditionalPass;
  }

  @Override
  public String toString(){
    return "Client: " + userName + "\nMobile Phone: " + mobilePhone+"\n";
  }
}
