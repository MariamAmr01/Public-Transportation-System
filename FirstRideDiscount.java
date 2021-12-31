import java.util.ArrayList;

public class FirstRideDiscount extends DiscountDecorator {
    private static double ratio = 10/100;

    public FirstRideDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        double plainPrice = super.applyDiscount(originalPrice, ride);
        ArrayList<Ride> rides = SystemApp.getObj().getDataBase().getRides();
        int found = 0;
        int lastIndex = ride.getClients().size()-1;
        for(Ride r : rides) {
            for(int i = 0; i < r.getClients().size(); i++) {
                if(ride.getClients().get(lastIndex).equals(r.getClients().get(i))){
                    found++;
                }
            }
        }

        if(found == 1)  //first ride
        {
            return calculateDiscount(ratio, plainPrice);
        }

        return plainPrice;
    }
    
}
