import java.util.ArrayList;

public class Lists implements Database {
  public ArrayList<Client> clientList;
  public ArrayList<Driver> driverList;
  public ArrayList<Ride> ridesList;

  //========== New ============
  public Admin admin;

  public Lists(){
    clientList = new ArrayList<Client>();
    driverList = new ArrayList<Driver>();
    admin = new Admin("admin", "1234");
  }

  public void addUser(Account account){

  }
  public void addRide(IRide ride){

  }
  public void removeUser(Account account){

  }
  public void removeRide(IRide ride){

  }

}
