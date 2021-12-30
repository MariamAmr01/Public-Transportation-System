public class Offer {
  private int price;
  private Driver driver;

  public Offer(int p, Driver d){
    price = p;
    driver = d;
  }

  public void setPrice(int p){
    price = p;
  }

  public void setDriver(Driver d){
    driver = d;
  }

  public Driver getDriver(){
    return driver;
  }
  
  public int getPrice(){
    return price;
  }

  @Override
  public String toString() {
    return "Offer{" + "price=" + price + ", driver=" + driver + '}'+"\n";
  }
}
