public class AdminAreaDiscount extends DiscountDecorator {

    public AdminAreaDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice) {
        double plainPrice = super.applyDiscount(originalPrice);
        //------------------------edit-------------------------    
        if(true)  //distination area is added by admin
        {
            return plainPrice = plainPrice - ((1/10) * plainPrice);
        }

        return plainPrice;
    }
    
}
    

