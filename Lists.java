import java.util.ArrayList;

public class Lists implements Database {
  private final ArrayList<Client> clientList=new ArrayList<>();
  private final ArrayList<Driver> driverList=new ArrayList<>();
  private final ArrayList<Ride> ridesList=new ArrayList<>();

  //========== New ============
  public Admin admin;

  public Lists(){
    //clientList = new ArrayList<Client>();
    //driverList = new ArrayList<Driver>();
    admin = new Admin("admin", "1234");
  }

  public void addUser(Account account){
    if(account instanceof Client){
      clientList.add((Client)account);
    }
    else
    {
      driverList.add((Driver) account);
    }
  }

  public void addRide(IRide ride){
    ridesList.add((Ride)ride);
  }

  public void removeUser(Account account){
    if(account instanceof Client){
      clientList.remove((Client)account);
    }
    else
    {
      driverList.remove((Driver) account);
    }
  }

  public void removeRide(IRide ride){
    ridesList.remove((Ride)ride);
  }
  public ArrayList<Driver> getDrivers(){
    return driverList;
  }
  public ArrayList<Client> getClients(){
    return clientList;
  }
  public ArrayList<Ride> getRides(){
    return ridesList;
  }

  public Admin getAdmin(){
    return admin;
  }
}
