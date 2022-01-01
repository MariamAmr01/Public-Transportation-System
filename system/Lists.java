package com.example.sprint2.system;

import com.example.sprint2.user.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class Lists implements Database {
    private final ArrayList<Client> clientList = new ArrayList<>();
    private final ArrayList<Driver> driverList = new ArrayList<>();
    private final ArrayList<Ride> ridesList = new ArrayList<>();
    //=============New==============
    private final ArrayList<Driver> pendingDriverList=new ArrayList<>();
    private final ArrayList<String> areas = new ArrayList<>();

    //========== New ============
    public Admin admin;

    public Lists(){
        //clientList = new ArrayList<Client>();
        //driverList = new ArrayList<Driver>();
        admin = new Admin("admin", "1234");
    }

    public void addUser(Account account){
        if(account instanceof Client){
            clientList.add((Client)account);
        }
        else
        {
            driverList.add((Driver) account);
        }
    }

    public void addRide(IRide ride){
        ridesList.add((Ride)ride);
    }

    public boolean removeUser(Account account){
        if(account instanceof Client){
            return clientList.remove((Client)account);
        }
        else
        {
            return driverList.remove((Driver) account);
        }
    }

    public void removeRide(IRide ride){
        ridesList.remove((Ride)ride);
    }
    public ArrayList<Driver> getDrivers(){
        return driverList;
    }
    @GetMapping ("/getClients")
    public ArrayList<Client> getClients() {
        return clientList;
    }
    public ArrayList<Ride> getRides() {
        return ridesList;
    }

    public ArrayList<Driver> getPendingDriverList() {
        return pendingDriverList;
    }

    public Admin getAdmin() {
        return admin;
    }

    //===============new===================
    @Override
    public void addAreas(ArrayList<String> area) {
        areas.addAll(area);
    }

    @Override
    public ArrayList<String> getAreas() {
        return areas;
    }

}
