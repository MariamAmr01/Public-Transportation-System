import java.util.ArrayList;

public class Admin extends User{

  public ArrayList<Driver> pendingDriverList;

  public Admin(){

  }

  public Admin(String name, String pass){
    userName = name;
    password = pass;
    pendingDriverList = new ArrayList<Driver>();
  }
  public User logIn(String name, String pass){
    if(SystemApp.getObj().admin.userName.equals(name)&&SystemApp.getObj().admin.password.equals(pass))
    {
      return SystemApp.getObj().admin;
    }
    return null;
  }

  public void approveDriver(Driver driver){
    System.out.println("APPROVED");
    pendingDriverList.remove(driver);
    SystemApp.getObj().driverList.add(driver);
  }

  public void listPendingDrivers(){
    for (int i = 0; i < pendingDriverList.size(); i++) {
      System.out.println((i+1)+") "+pendingDriverList.get(i));
    }
  }

  public void suspendAccount(Account account){
    SystemApp.getObj().deleteUser(account);
  }
}
