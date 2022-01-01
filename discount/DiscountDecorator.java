package com.example.sprint2.discount;

import com.example.sprint2.user.Ride;

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

        return (originalPrice - (ratio * originalPrice));
    }

}