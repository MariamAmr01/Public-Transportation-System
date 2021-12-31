public class PlainPrice implements IDiscount {

    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }
    
}
