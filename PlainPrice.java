public class PlainPrice implements IDiscount {

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        System.out.println("PlainPrice Class:  " + originalPrice);
        return originalPrice;
    }
    
}
