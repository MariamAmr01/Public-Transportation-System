public class PublicHolidayDiscount extends DiscountDecorator {
    
    public PublicHolidayDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice) {
        double plainPrice = super.applyDiscount(originalPrice);
        //------------------------edit-------------------------    
        if(true)  //ride date is a public holiday
        {
            return plainPrice = plainPrice - ((5/100) * plainPrice);
        }

        return plainPrice;
    }
}
