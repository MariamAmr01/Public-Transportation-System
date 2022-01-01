import java.util.ArrayList;

public class AdminAreaDiscount extends DiscountDecorator {
    private static final double ratio = 0.1;

    public AdminAreaDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        double plainPrice = super.applyDiscount(originalPrice, ride);
        ArrayList<String> areas = SystemApp.getObj().getDataBase().getAreas();

        if(areas.contains(ride.getDestination()))       //distination area is added by admin
        {
            return calculateDiscount(ratio, plainPrice);
        }

        return plainPrice;
    }
    
}
    

