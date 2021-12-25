public interface Database {
  public void addUser(Account account);
  public void addRide(IRide ride);
  public void removeUser(Account account);
  public void removeRide(IRide ride);
}
