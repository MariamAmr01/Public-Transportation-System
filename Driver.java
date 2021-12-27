import java.util.ArrayList;

import java.time.LocalDateTime;  

public class Driver extends User implements Account {
  private String drivingLicense;
  private String nationalId;
  public double averageRate;
  // ================ New ====================
  private boolean available;
  private Ride ride;

  private ArrayList<String> favoriteAreas;
  //////////////////////////////////////
  private ArrayList<Ride> rides;
  private ArrayList<Ride> newRides;
  ///////////////////////////////////
  public ArrayList<Integer> rate;

  public Driver() {
    available = true;
    favoriteAreas = new ArrayList<String>();
    rides = new ArrayList<Ride>();
    newRides = new ArrayList<Ride>();
    rate = new ArrayList<Integer>();
  }

  // 1) userName 2)password 3) moblie 4) drivingLicense 5) nationalId
  public Boolean register(ArrayList<String> info) {
    this.userName = info.get(0);
    this.password = info.get(1);
    this.mobilePhone = info.get(2);
    this.drivingLicense = info.get(3);
    this.nationalId = info.get(4);

    for (int i = 0; i < SystemApp.getObj().getDataBase().getDrivers().size(); i++) {
      if (SystemApp.getObj().getDataBase().getDrivers().get(i).getUserName().equals(this.userName)) {
        System.out.println("This userName is already exits. Registration failed.");
        return false;
      }
    }
    System.out.println("Registration succeeded.");
    SystemApp.getObj().getDataBase().getAdmin().pendingDriverList.add(this);
    return true;
  }

  public User logIn(String name, String pass) {
    Driver d;
    for (int i = 0; i < SystemApp.getObj().getDataBase().getDrivers().size(); i++) {
      d = SystemApp.getObj().getDataBase().getDrivers().get(i);
      if (d.getUserName().equals(name) && d.getPassword().equals(pass)) {
        return d;
      }
    }
    return null;
  }

  public void addAreas(ArrayList<String> areas) {
    this.favoriteAreas = areas;
  }

  public void listRating() {
    System.out.println("Users Ratings: ");
    for (int i = 0; i < rate.size(); i++) {
      System.out.println(i + 1 + ") " + rate.get(i));
    }
    System.out.println("============================");
  }

  public ArrayList<Ride> listRides() {
    if (rides.size() > 0) {
      System.out.println("All Rides: ");
      for (int i = 0; i < rides.size(); i++) {
        System.out.println(i + 1 + ") " + rides.get(i));
      }
      System.out.println("============================");
    } else
      System.out.println("You have no rides");
    return rides;
  }

  public void offerRidePrice(Ride ride, int price) {
    // ride.setPrice(price);
    Offer offer = new Offer(price, this);
    ride.setOffer(offer);
    // --> 
    IEvent event = new CaptainArrivedEvent(ride, "Captain put a price to the ride" , LocalDateTime.now());
    ride.addEvent(event);
  }

  //========== New ==============
  // Driver arrived to destination
  public void endRide(){
    available = true;
    //Captain arrived to user destination 
    IEvent event = new CaptainArrivedEvent(ride, "Captain arrived to user destination" ,  LocalDateTime.now());
    ride.addEvent(event); 
  }
// ============ New =====================
  public void arrive(LocalDateTime time){
    time = time.plusMinutes(10);
    IEvent event = new CaptainArrivedEvent(ride, "Captain arrived to user location" ,  time);
    ride.addEvent(event); 
  }

  public void notifyClient(Client client) {
    client.getNotification();
  }
  public void setRide(Ride r) {
    ride = r;
  }

  public void setAvailable(boolean av) {
    available = av;
  }

  public void setDrivingLicense(String drivingLicense) {
    this.drivingLicense = drivingLicense;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public boolean getAvailable() {
    return available;
  }

  public String getDrivingLicense() {
    return drivingLicense;
  }

  public String setNationalId() {
    return nationalId;
  }
  public Ride getRide(){
    return ride;
  }
  public ArrayList<String> getFavoriteAreas() {
    return favoriteAreas;
  }

  public ArrayList<Ride> getRides() {
    return rides;
  }

  public ArrayList<Ride> getNewRides() {
    return newRides;
  }

  public String getNotification() {

    String ride = "new rides has been added\n";
    if (newRides.size() > 0) {

      for (int i = 0; i < newRides.size(); i++) {

        ride += newRides.get(i) + "========================\n";
      }

      return ride + "Please offer price to these rides.\n";
    }
    return "No new notifications";
  }

  @Override
  public String toString() {
    return "Driver: " + userName + "\nMobile Phone: " + mobilePhone + "\nAverage Rating: " + averageRate + "\n";
  }

}
