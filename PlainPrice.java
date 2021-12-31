public class PlainPrice implements IDiscount {

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        return originalPrice;
    }
    
}
