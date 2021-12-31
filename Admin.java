import java.util.ArrayList;

public class Admin extends User {

  public Admin() {

  }

  public Admin(String name, String pass) {
    userName = name;
    password = pass;
  }

  public User logIn(String name, String pass) {
    if (SystemApp.getObj().getDataBase().getAdmin().userName.equals(name)
        && SystemApp.getObj().getDataBase().getAdmin().password.equals(pass)) {
      return SystemApp.getObj().getDataBase().getAdmin();
    }
    return null;
  }

  public void approveDriver(Driver driver) {
    System.out.println("APPROVED");
    SystemApp.getObj().getDataBase().getPendingDriverList().remove(driver);
    SystemApp.getObj().getDataBase().addUser(driver);
  }

  public void listPendingDrivers() {
    for (int i = 0; i < SystemApp.getObj().getDataBase().getPendingDriverList().size(); i++) {
      System.out.println((i + 1) + ") " + SystemApp.getObj().getDataBase().getPendingDriverList().get(i));
    }
  }

  public void suspendAccount(Account account) {
    SystemApp.getObj().deleteUser(account);
  }

  public ArrayList<Ride> listRids(){
    return SystemApp.getObj().getDataBase().getRides();
  }

  public ArrayList<IEvent> listRideEvents(int rideIndex){
    ArrayList<Ride> rides = listRids();
    if(rideIndex > 0 && rides.size() > 0) return rides.get(rideIndex-1).getEvents();

    return null;
  }

  //================new======================
  public void addArea(ArrayList<String> area) {
    SystemApp.getObj().getDataBase().addAreas(area);
  }
}
