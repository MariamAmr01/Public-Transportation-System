package com.example.sprint2.api;

import com.example.sprint2.user.Client;
import com.example.sprint2.user.Driver;
import com.example.sprint2.user.Ride;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class DriverController {
    private Driver d;
    DriverController()
    {
        d=new Driver();
    }
    @PostMapping("/driverRegister")
    public boolean register(@RequestBody Driver driver)
    {
        ArrayList<String> a= new ArrayList<>();
        a.add(driver.getUserName());
        a.add(driver.getPassword());
        a.add(driver.getmobilePhone());
        a.add(driver.getDrivingLicense());
        a.add(driver.getNationalId());
        a.add(Integer.toString(driver.getNumOfSeats()));
        return driver.register(a);
    }

    @PostMapping ("/driverLogin")
    public String login(@RequestBody Driver driver)
    {
        d= (Driver)driver.logIn(driver.getUserName(),driver.getPassword());
        if(d!=null)
            return d+d.getNotification();
        else return "UserName or password is incorrect or waiting for Admin Approval";
    }

    @PostMapping ("/driverAddArea")
    @ResponseBody
    public boolean addDriverArea(@RequestParam Map<String,String> allParams){
        ArrayList<String> areas = new ArrayList<>();
        for (int i = 0; i < allParams.size(); i++) {
            String indx= Integer.toString(i);
            areas.add(allParams.get(indx));
        }
        areas = (ArrayList<String>) allParams.values();
        d.addAreas(areas);
        return true;
    }

    @GetMapping ("/driverAllRide")
    public ArrayList<Ride> listAllRides(){
        return d.listRides();
    }

    @GetMapping ("/driverAllRating")
    public ArrayList<Integer> listAllRating(){
        return d.listRating();
    }

    @GetMapping ("/driverEndRide")
    public boolean endRide(){
        d.endRide();
        return true;
    }

    @PostMapping ("/driverOfferRidePrice")
    public boolean offerPrice(@RequestBody Ride ride, @RequestBody int price){
        d.offerRidePrice(ride, price);
        return true;
    }


}
