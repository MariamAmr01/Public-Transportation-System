package com.example.sprint2.user;

import com.example.sprint2.events.*;
import com.example.sprint2.system.SystemApp;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Driver extends User implements Account {
  private String drivingLicense;
  private String nationalId;
  public double averageRate;
  //private boolean available;
  private Ride ride;
  private int numOfSeats;
  private int availableSeat;
  private ArrayList<String> favoriteAreas;
  public ArrayList<Integer> rate;

  public Driver() {
    //available = true;
    favoriteAreas = new ArrayList<String>();
    rate = new ArrayList<Integer>();
  }
  public Driver(String userName, String password, String mobilePhone,String drivingLicense,String nationalId,String numOfSeats) {
    this.userName = userName;
    this.password = password;
    this.mobilePhone = mobilePhone;
    this.drivingLicense = drivingLicense;
    this.nationalId = nationalId;
    this.numOfSeats = Integer.parseInt(numOfSeats);

  }

  // 1) userName 2)password 3) moblie 4) drivingLicense 5) nationalId 6)
  // numOfSeats
  public Boolean register(ArrayList<String> info) {
    this.userName = info.get(0);
    this.password = info.get(1);
    this.mobilePhone = info.get(2);
    this.drivingLicense = info.get(3);
    this.nationalId = info.get(4);
    this.numOfSeats = Integer.parseInt(info.get(5));
    availableSeat = numOfSeats;

    for (int i = 0; i < SystemApp.getObj().getDataBase().getDrivers().size(); i++) {
      if (SystemApp.getObj().getDataBase().getDrivers().get(i).getUserName().equals(this.userName)) {
        System.out.println("This userName is already exits. Registration failed.");
        return false;
      }
    }

    System.out.println("Registration succeeded.");
    SystemApp.getObj().getDataBase().getPendingDriverList().add(this);
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
    this.favoriteAreas.addAll(areas);
  }

  public ArrayList<Integer>  listRating() {
    System.out.println("Users Ratings: ");
    for (int i = 0; i < rate.size(); i++) {
      System.out.println(i + 1 + ") " + rate.get(i));
    }
    System.out.println("============================");
    return rate;
  }

  public ArrayList<Ride> listRides() {
    ArrayList<Ride> rides = SystemApp.getObj().getDataBase().getRides();
    ArrayList<Ride> foundRide = new ArrayList<>();
    for (Ride r : rides) {
      if (r.getDriver() == null ||(r.getDriver()!=null&& !r.getCompleted())) {

        for (String favoriteArea : favoriteAreas) {

          if (favoriteArea.equals(r.getSource())) {
            if ((r.getClients().get(0).getAccAdditionalSeats() == 0 && this.ride == null) ||
                (r.getClients().get(0).getAccAdditionalSeats() != 0 && this.ride != null) ||   
                (r.getClients().get(0).getAccAdditionalSeats() != 0 && this.ride == null)  ) 
            {
              foundRide.add(r);
            }
          }

        }
      }
    }
    return foundRide;
  }

  public void offerRidePrice(Ride ride, int price) {
    Offer offer = new Offer(price, this);
    ride.setOffer(offer, this);
    IEvent event = new CaptianOfferEvent(this, price, "Captain put a price to the ride", LocalDateTime.now());
    ride.addEvent(event);
  }

  // Driver arrived to destination
  public void endRide() {
    //available = true;
    availableSeat = numOfSeats;
    // Captain arrived to user destination
    IEvent event = new CaptainArrivedEvent(ride, "Captain arrived to user destination", LocalDateTime.now());
    ride.addEvent(event);
    ride.setCompleted(true);
  }

  public void arriveToLocation(LocalDateTime time) {
    time = time.plusMinutes(10);
    IEvent event = new CaptainArrivedEvent(this.ride, "Captain arrived to user location", time);
    ride.addEvent(event);
  }

  public String getNotification() {

    String ride = "new rides has been added\n";
    ArrayList<Ride> requestedRide = listRides();

    if (requestedRide.size() > 0) {

      for (Ride rRide : requestedRide) {
        for (int j = 0; j < rRide.getOffers().size(); j++) {
          if (rRide.getOffers().get(j).getDriver().equals(this)) {
            ride = rRide + "\n========================\nNew Client is added !!!!\n";
            return ride;
          }
        }
        ride += rRide + "========================\n";
      }

      return ride + "Please offer price to these rides.\n";
    }
    return "No new notifications";
  }

  public void notifyClient(Client client, Ride ride) {
    client.getNotification();
  }

  public void setRide(Ride r) {
    ride = r;
  }

  // public void setAvailable(boolean av) {
  //   available = av;
  // }

  public void setDrivingLicense(String drivingLicense) {
    this.drivingLicense = drivingLicense;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public boolean getAvailable() {

    return availableSeat > 0;

  }

  public void setAvailableSeat(int availableSeat) {
    this.availableSeat = availableSeat;
  }

  public int getAvailableSeat() {
    return availableSeat;
  }

  public String getDrivingLicense() {
    return drivingLicense;
  }

  public String getNationalId() {
    return nationalId;
  }

  public Ride getRide() {
    return ride;
  }

  public ArrayList<String> getFavoriteAreas() {
    return favoriteAreas;
  }

  public void setNumOfSeats(int numOfSeats) {
    this.numOfSeats = numOfSeats;
  }

  public int getNumOfSeats() {
    return numOfSeats;
  }

  @Override
  public String toString() {
    return "Driver: " + userName + "\nMobile Phone: " + mobilePhone + "\nAverage Rating: " + averageRate + "\n";
  }

}
