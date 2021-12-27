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
    if(SystemApp.getObj().getDataBase().getAdmin().userName.equals(name)&&SystemApp.getObj().getDataBase().getAdmin().password.equals(pass))
    {
      return SystemApp.getObj().getDataBase().getAdmin();
    }
    return null;
  }

  public void approveDriver(Driver driver){
    System.out.println("APPROVED");
    pendingDriverList.remove(driver);
    SystemApp.getObj().getDataBase().addUser(driver);
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
