package com.example.sprint2.api;

import com.example.sprint2.user.Client;
import com.example.sprint2.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class ClientController {
    private Client c;
    ClientController()
    {
        c=new Client();
    }
    @PostMapping ("/clientRegister")
    public boolean register(@RequestBody Client c)
    {
        ArrayList<String> a= new ArrayList<>();
        a.add(c.getUserName());
        a.add(c.getPassword());
        a.add(c.getmobilePhone());
        a.add(c.getBirthDate());
        return c.register(a);
    }

    @PostMapping ("/clientLogin")
    public String login(@RequestBody Client client)
    {
        c= (Client)client.logIn(client.getUserName(),client.getPassword());
        if(c!=null)
            return c+c.getNotification();
        else return "UserName or password is incorrect";
    }

    @PostMapping ("/clientRequest")
    @ResponseBody
    public String requestRide(@RequestParam Map<String,String> allParams)

    {
        String source=allParams.get("0");
        String destination=allParams.get("1");
        String passengers= allParams.get("2");
        String accAdditionalPass=allParams.get("3");
        return c.requestRide(source,destination,passengers,accAdditionalPass);
    }


}
