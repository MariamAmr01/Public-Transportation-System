public class BirthdayDiscount extends DiscountDecorator{
    
    public BirthdayDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice) {
        double plainPrice = super.applyDiscount(originalPrice);
        //------------------------edit-------------------------    
        if(true)  //ride date matches user birthday
        {
            return plainPrice = plainPrice - ((1/10) * plainPrice);
        }

        return plainPrice;
    }
}
