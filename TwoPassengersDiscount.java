public class TwoPassengersDiscount extends DiscountDecorator{
    private static double ratio = 5/100;

    public TwoPassengersDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        double plainPrice = super.applyDiscount(originalPrice, ride);

        if(ride.getClients().size() >= 2)    //two passengers 
        {
            return calculateDiscount(ratio, plainPrice);
        }

        return plainPrice;
    }
}
