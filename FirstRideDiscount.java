public class FirstRideDiscount extends DiscountDecorator{

    //protected IDiscount discount;

    public FirstRideDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice) {
        double plainPrice = super.applyDiscount(originalPrice);
        //------------------------edit-------------------------    
        if(true)  //first ride
        {
            return plainPrice = plainPrice - ((1/10) * plainPrice);
        }

        return plainPrice;
    }
    
}
