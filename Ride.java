import java.util.ArrayList;

public class Ride implements IRide {
  private String source;
  private String destination;
  private int price;
  //////
  //private Client client;
  /// =============== New ================
  private ArrayList<Client> clients;
  private ArrayList<Offer> offers;

  private Driver driver;
  private Boolean rated;


  public Ride(Client client, Driver driver, String source, String destination){
    clients = new ArrayList<>();
    offers = new ArrayList<>();

    //price = 0;
    clients.add(client);
    this.driver = driver;
    this.source = source;
    this.destination = destination;
    rated = false;
  }

  ///////////////////////////////////////////////////////////////////////
  // Update
  // public void setPrice(int price){
  //   this.price = price;
  //   //driver.notifyClient(client);
  // }
////////////////////////////////////////////////////////////////////////////
  public int getPrice(){
    return price;
  }

  public Driver getDriver(){
    return this.driver;
  }

  public ArrayList<Client> getClients(){
    return clients;
  }

  public void setRated(Boolean r){
    rated = r;
  }
  public void setDriver(Driver d){
    driver = d;
  }
  public Boolean getRated(){
    return rated;
  }

  public ArrayList<Offer> getOffers(){
    return offers;
  }
  @Override
  public String toString(){

    return "Source: " + source + "\nDestination: " + destination + "\nPrice: " + price +"\n"+driver+clients;
  }

  public void setOffer(Offer offer){
    offers.add(offer);
    for (Client client : clients) {
      driver.notifyClient(client);
    }
  }
}
