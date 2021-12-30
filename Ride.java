import java.util.ArrayList;

public class Ride implements IRide {
  private String source;
  private String destination;
  //////
  // private Client client;
  /// =============== New ================
  private ArrayList<Client> clients = new ArrayList<>();
  private ArrayList<Offer> offers = new ArrayList<>();
  private ArrayList<IEvent> events = new ArrayList<>();
  private boolean completed;

  private Driver driver;
  private Boolean rated;

  public Ride(Client client, Driver driver, String source, String destination) {

    clients.add(client);
    this.driver = driver;
    this.source = source;
    this.destination = destination;
    rated = false;
    completed = false;
  }

  // ============= NEW =================
  public void addEvent(IEvent e) {
    events.add(e);
  }
  // ============= NEW =================
  public void addClient(Client c) {
    clients.add(c);
  }

  public void setOffer(Offer offer, Driver driver) {
    offers.add(offer);
    for (Client client : clients) {
      driver.notifyClient(client, this);
    }
  }

  public Driver getDriver() {
    return this.driver;
  }

  public ArrayList<Client> getClients() {
    return clients;
  }

  public void setRated(Boolean r) {
    rated = r;
  }

  public void setDriver(Driver d) {
    driver = d;
  }

  public Boolean getRated() {
    return rated;
  }

  public String getSource() {
    return source;
  }

  public ArrayList<Offer> getOffers() {
    return offers;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public boolean getCompleted() {
    return completed;
  }
  
  @Override
  public String toString() {

    return "Source: " + source + "\nDestination: " + destination + "\n" + driver + clients;
  }

}
