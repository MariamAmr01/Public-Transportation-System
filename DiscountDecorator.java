public class DiscountDecorator implements IDiscount {

    protected IDiscount discount;

    public DiscountDecorator(IDiscount discount) {
        this.discount = discount;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return discount.applyDiscount(originalPrice);    //return the plain price
    }
    
}