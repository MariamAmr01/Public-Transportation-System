public class TwoPassengersDiscount extends DiscountDecorator{

    public TwoPassengersDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice) {
        double plainPrice = super.applyDiscount(originalPrice);
        //------------------------edit-------------------------    
        if(true)  //only two passengers 
        {
            return plainPrice = plainPrice - ((5/100) * plainPrice);
        }

        return plainPrice;
    }
}
