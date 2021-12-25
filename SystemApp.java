import java.util.ArrayList;

public class SystemApp {
  public ArrayList<Client> clientList;
  public ArrayList<Driver> driverList;
  public Admin admin;
  private static SystemApp obj;

  private Database database;

  private SystemApp() {
    // Edit
    clientList = new ArrayList<Client>();
    driverList = new ArrayList<Driver>();
    admin = new Admin("admin", "1234");
    // Lists 
    database = new Lists();

  }

  public static SystemApp getObj() {
    if (obj == null) {
      obj = new SystemApp();
    }
    return obj;
  }

  public void setAverageRate(Driver driver) {
    double sum = 0.0;
    int size = driver.rate.size();
    for(int i=0; i<size; i++)
      sum += driver.rate.get(i);
    driver.averageRate = sum / size;
  }

  /// ============= New ==================
  public ArrayList<Driver> findDriver(String source) {
    ArrayList<Driver> drivers = new ArrayList<>();

    for (Driver driver : driverList) {
      if (driver.getFavoriteAreas().contains(source)) {
        //return driver;
        drivers.add(driver);
      }

    }
    return drivers;
  }

  public void notifyDriver(Driver driver) {
    driver.getNotification();

  }

  public void deleteUser(Account account) {
    if(account instanceof Client){
      clientList.remove(account);
      System.out.println("The client account has been suspended");
    }
    if(account instanceof Driver){
      driverList.remove(account);
      System.out.println("The driver account has been suspended");
    }
  }

}
