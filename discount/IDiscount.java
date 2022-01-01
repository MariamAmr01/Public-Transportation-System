package com.example.sprint2.discount;

import com.example.sprint2.user.Ride;

public interface IDiscount {
    public double applyDiscount(double originalPrice, Ride ride);
}
