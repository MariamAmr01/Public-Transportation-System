public class BirthdayDiscount extends DiscountDecorator {
    private static double ratio = 10/100;

    public BirthdayDiscount(IDiscount discount) {   //constructor
        super(discount);
    }

    @Override
    public double applyDiscount(double originalPrice, Ride ride) {
        double plainPrice = super.applyDiscount(originalPrice, ride);
        int lastIndex = ride.getClients().size()-1;
        int clientDay = ride.getClients().get(lastIndex).getBirthday().getDayOfMonth();
        int clientMonth = ride.getClients().get(lastIndex).getBirthday().getMonthValue();
        int  currentDay = java.time.LocalDate.now().getDayOfMonth();
        int currentMonth = java.time.LocalDate.now().getMonthValue();

        if(clientDay == currentDay && clientMonth == currentMonth)           //ride date matches user birthday
        {
            return calculateDiscount(ratio, plainPrice);
        }

        return plainPrice;
    }
}
