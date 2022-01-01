import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PublicHolidayDiscount extends DiscountDecorator {
    private static final double ratio = 0.05;
    
    ArrayList<LocalDate> specialDays = new ArrayList<LocalDate>();
    
    public PublicHolidayDiscount(IDiscount discount) throws ParseException {   //constructor
        super(discount);
        specialDays.add(java.time.LocalDate.of(2022, 10, 06));
        specialDays.add(java.time.LocalDate.of(2022, 04, 25));
        specialDays.add(java.time.LocalDate.of(2022, 01, 25));
        specialDays.add(java.time.LocalDate.of(2022, 01, 04));
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        double plainPrice = super.applyDiscount(originalPrice, ride);

        LocalDate today = java.time.LocalDate.now();
        
        if(specialDays.contains(today))    //ride date is a public holiday
        {
            return calculateDiscount(ratio, plainPrice);
        }

        return plainPrice;
    }
}
