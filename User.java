public abstract class User {
  protected String userName;
  protected String mobilePhone;
  protected String password;

  abstract public User logIn(String name, String pass);

  public void setUserName(String name){
    userName = name;
  }
  public void setmobilePhone(String mobile){
    mobilePhone = mobile;
  }
  public void setPassword(String pass){
    password = pass;
  }
  public String getUserName(){
    return userName;
  }
  public String getmobilePhone(){
    return mobilePhone;
  }
  public String getPassword(){
    return password;
  }
  
}
