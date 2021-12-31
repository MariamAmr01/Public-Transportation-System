public class DiscountDecorator implements IDiscount {

    protected IDiscount discount;

    public DiscountDecorator(IDiscount discount) {
        this.discount = discount;
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {

        return discount.applyDiscount(originalPrice, ride);    //return the plain price
    }

    public double calculateDiscount(double ratio, double originalPrice){
// Updated
       System.out.println("Ratio: " + ratio );
       System.out.println("originalPrice: " + originalPrice );

       System.out.println("Dis :  " + (originalPrice - (ratio * originalPrice)));

       return (originalPrice - (ratio * originalPrice));
    }
    
}