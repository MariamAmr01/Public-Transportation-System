import java.util.ArrayList;

public class SystemApp {
  private static SystemApp obj;

  private Database database;

  private SystemApp() {
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

    for (Driver driver : database.getDrivers()) {
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
    database.removeUser(account);
  }

  public Database getDataBase(){
    return database;
  }
}
