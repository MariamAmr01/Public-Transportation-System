package com.example.sprint2.api;

import com.example.sprint2.system.SystemApp;
import com.example.sprint2.user.Account;
import com.example.sprint2.user.Admin;
import com.example.sprint2.user.Driver;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class AdminController {

    private Admin a;
    AdminController()
    {
        a = new Admin();
    }

    @PostMapping("/adminLogin")
    public String login(@RequestBody Admin admin)
    {
        SystemApp.getObj();
        a = (Admin)admin.logIn(admin.getUserName(),admin.getPassword());
        if(a != null)
            return "logged in ";
        else
            return "userName or password is incorrect";
    }

    @GetMapping("/adminList")
    public String listPending()
    {
        return a.listPendingDrivers();
    }


    @PostMapping ("/adminApproveDriver")
    @ResponseBody
    public boolean approve(@RequestParam Map<String,String> allParams){
        int index=Integer.parseInt(allParams.get("0"))-1;
        Driver dr= SystemApp.getObj().getDataBase().getPendingDriverList().get(index);
        a.approveDriver(dr);
        return true;
    }

    @PostMapping ("/adminSuspendAccount")
    public boolean suspendAccount(@RequestBody Account account)
    {
        return a.suspendAccount(account);
    }
}

