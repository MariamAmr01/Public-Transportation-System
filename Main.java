import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void clientMenu(Client c, Scanner input){
    System.out.println("Logged in as Client: ");
    System.out.println(c.getNotification());
    if(!c.getNotification().equals("no offers")) //&& !c.getRide().getRated()
    {
      if(c.getRide()!=null&&c.getRide().getOffers().size()!=0) {
        System.out.println("Choose offer: ");
        int response = input.nextInt();
        c.acceptOffer(response);
      }
//      System.out.println("Do you want to rate the driver ☺ \n1- Rate. \n2- Remind me later.");
//      int response = input.nextInt();
//      if (response == 1) {
//        System.out.println("Choose a rate: \n1 ★ \n2 ★★ \n3 ★★★ \n4 ★★★★ \n5 ★★★★★");
//        int rate = input.nextInt();
//        if(rate>=1&&rate<=5){
//          c.rateDriver(rate);
//        }
//        else System.out.println("invalid input");
//      }
//      else System.out.println("OK, thank you..");
    }

    String in;
    do{
      Boolean flag=false;
      System.out.println("Choose one of the following: ");
      System.out.println("1- Request Ride"+"\n2- Logout");
      if(c.getRide()!=null && !c.getRide().getRated())
      {
        System.out.println("3- Rate current ride driver");
        flag=true;
      }
      if(c.getRide()!=null && c.getRide().getDriver()!=null)
      {
        //System.out.println("4- View Discounts");
        //flag=true;
        c.displayDiscount();
      }
      in = input.next();
      if(in.equals("1")){
        System.out.print("Enter source : ");
        String source = input.next();
        System.out.print("Enter destination : ");
        String destination = input.next();
        
        System.out.print("Enter Number of passengers : ");
        int numOfPassenger = input.nextInt();       
        
        System.out.print("Enter accAdditionalPass : ");
        int accAdditionalPass = input.nextInt();
////////////////////------------->
        c.requestRide(source, destination, numOfPassenger, accAdditionalPass);
      }
      if(flag && in.equals("3") && !c.getRide().getRated())
      {
        System.out.println("Choose a rate: \n1 ★ \n2 ★★ \n3 ★★★ \n4 ★★★★ \n5 ★★★★★");
        int rate = input.nextInt();
        // if(rate>=1&&rate<=5)
        // {
          System.out.println(c.rateDriver(rate));
        // }
        //else System.out.println("invalid input");
      }

    } while(!in.equals("2"));
  }

  public static void driverMenu(Driver d, Scanner input){
    System.out.println("Logged in as Driver: ");
    System.out.println(d.getNotification());
    //System.out.println("Requested rides: "+d.listRides());

    //ArrayList<Ride> newRides = d.listRides();

    String in;
    do{
      ArrayList<Ride> newRides = d.listRides();

      System.out.println("Choose one of the following: ");
      System.out.println("1- Add favorite Areas\n2- List Ratings\n3- List Rides\n4- Offer ride price\n5-Logout");
      in = input.next();

      switch (in)
      {
        case "1":
        System.out.print("Enter the number of areas you want to add : ");
        int num = input.nextInt();
        ArrayList<String> areas = new ArrayList<String>();
        for(int i = 0 ; i < num ; i++){
          System.out.print("Enter area: ");
          String area = input.next();
          areas.add(area);
        }
        d.addAreas(areas);
        break;

        case "2":
        d.listRating();
        break;

        case "3":
          d.listRides();
        System.out.println(d.listRides());
        break;
        case "4":
            if (newRides.size() > 0) {
            System.out.println("Do you want to choose to offer a ride price?\nEnter Y/n");
            String y = input.next();

            if (y.equals("y")) {
              for (int i = 0; i < newRides.size(); i++) {
                System.out.println(i + 1 + ") " + newRides.get(i));
              }
              System.out.println("============================");

              System.out.print("Enter the number of ride you want to offer price to : ");
              int number = input.nextInt();
              if (number <= newRides.size() && number > 0)
              {
                System.out.print("Enter price: ");
                int price = input.nextInt();
                d.offerRidePrice(newRides.get(number - 1), price);
                //d.getNewRides().remove(number - 1);
              }
              else System.out.println("Invalid input");
            } else if (!y.equals("n")) {
              System.out.println("Wrong input .Enter Y/n");
            }
          }
          else{ 
            System.out.println("There is no new rides");
          }
        break;

      }

    } while(!in.equals("5"));
  }

  public static void adminMenu(Admin a, Scanner input){
    System.out.println("Logged in as Admin: ");
    String in;
    do{
      // To list the events of the first ride (except EndRide (Arrive to destination))
      System.out.println(a.listRideEvents(1));
      ArrayList<String> ar = new ArrayList<>();
      ar.add("a");
      ar.add("m");
      ar.add("n");
      a.addAreas(ar);
      ///////////////////////////////////////////////
      System.out.println("Choose one of the following: ");
      System.out.println("1- List Pending Drivers"+"\n2- Suspend Account"+"\n3- Logout");
      in = input.next();
      if(in.equals("1")) {
        a.listPendingDrivers();
        Driver pDriver;
        if (SystemApp.getObj().getDataBase().getPendingDriverList().size() > 0) {
          do {
            System.out.println("Do you want to approve?\nEnter Y/n");
            in = input.next();
            int c;
            if (in.equalsIgnoreCase("y")) {
              System.out.println("Enter number of driver: ");
              c = input.nextInt();
              if(c<= SystemApp.getObj().getDataBase().getPendingDriverList().size()&&c>0) {
                pDriver =   SystemApp.getObj().getDataBase().getPendingDriverList().get((c - 1));
                a.approveDriver(pDriver);
              }
              else System.out.println("Wrong input");
            }
          }
          while (!in.equalsIgnoreCase("n"));
        }
        else System.out.println("No pending accounts");
      }
      if(in.equals("2")) {
        System.out.println("Do you want to Suspend client account or driver account\n1- Client\n2- Driver");
        in = input.next();
        int size;
        if(in.equals("1"))
        {
          size = SystemApp.getObj().getDataBase().getClients().size();

          if(size>0){
            for (int i = 0; i < size; i++) {
              System.out.println((i+1)+") "+SystemApp.getObj().getDataBase().getClients().get(i));
            }
            System.out.println("Choose number of account you want to suspend");
            System.out.print("Enter Number: ");
            int choice= input.nextInt();
            if(choice<=size&&choice>0) {
              a.suspendAccount(SystemApp.getObj().getDataBase().getClients().get(choice - 1));
            }
            else System.out.println("Wrong input");
          }
          else System.out.println("There is no clients has been added yet");
        }
        else if(in.equals("2"))
        {
          size = SystemApp.getObj().getDataBase().getDrivers().size();
          if(size>0){
            for (int i = 0; i < size; i++) {
              System.out.println((i+1)+") "+SystemApp.getObj().getDataBase().getDrivers().get(i));
            }
            System.out.println("Choose number of account you want to suspend");
            System.out.print("Enter Number: ");
            int choice= input.nextInt();
            if(choice<=size&&choice>0) {
              a.suspendAccount(SystemApp.getObj().getDataBase().getDrivers().get(choice - 1));
            }
            else System.out.println("Wrong input");
          }
          else System.out.println("There is no drivers has been added yet");

        }
        else  System.out.println("Please choose 1 or 2");

      }
    } while(!in.equals("3"));
  }

  public static void main(String args[]){

    String choice="", choice2="",choice3="";
    Scanner input= new Scanner(System.in);
    String infoString;

    do{
      User user;
      ArrayList<String> info = new ArrayList<String>();
      Account account = null;
      System.out.println("Choose one of the following: ");
      System.out.println("1- Register"+"\n2- Login"+"\n3- exit");
      choice=input.next();

      switch (choice) {
        case "1":
        {
          System.out.println("Choose to register as: \n1- Client\n2- Driver");
          choice2 = input.next();
          if (choice2.equals("1") || choice2.equals("2")) {
            System.out.print("Enter your user name: ");
            infoString = input.next();
            info.add(infoString);

            System.out.print("Enter your password: ");
            infoString = input.next();
            info.add(infoString);

            System.out.print("Enter your mobile phone: ");
            infoString = input.next();
            info.add(infoString);



            switch (choice2) {
              case "1": {

                System.out.print("Enter your birthday with format dd/MM/yyyy: ");
                infoString = input.next();
                info.add(infoString);

                account = new Client();
                if (account.register(info)) {
                  //Client Menu (client, scan)
                  clientMenu((Client) account, input);
                }
                break;
              } //case1 of switch choice2

              case "2": {
                System.out.print("Enter your driving license: ");
                infoString = input.next();
                info.add(infoString);

                System.out.print("Enter your nationalId: ");
                infoString = input.next();
                info.add(infoString);

                System.out.print("Enter your available number seats: ");
                infoString = input.next();
                info.add(infoString);

                account = new Driver();
                if (account.register(info)) {
                  System.out.println("Waiting for approval of admin");
                }
                break;
              }//case2 of switch choice2
            }//end of Switch choice2
          }//end of if
          break;
        }//case1 of switch choice

        case "2":
        {
          System.out.println("Choose to login as: \n1- Client\n2- Driver\n3- Admin");
          choice3 = input.next();
          if (choice3.equals("1") || choice3.equals("2") || choice3.equals("3")) {
            System.out.print("Enter your user name: ");
            String userName = input.next();
            System.out.print("Enter your password: ");
            String pass = input.next();
            switch (choice3) {
              case "1":
              {
                user = new Client();
                Client c;
                c = (Client) user.logIn(userName, pass);
                if (c != null)
                  clientMenu(c, input);
                else
                  System.out.println("Username or password is incorrect ");
                break;
              }

              case "2":
              {
                user = new Driver();
                Driver d;
                d = (Driver) user.logIn(userName, pass);
                if (d != null)
                  driverMenu(d, input);
                else
                  System.out.println("Username or password is incorrect or account not approved by admin, yet.");
                break;
              }

              case "3":
              {
                user = new Admin();
                Admin a;
                a = (Admin) user.logIn(userName, pass);
                if (a != null)
                  adminMenu(a, input);
                else
                  System.out.println("Username or password is incorrect.");
                break;
              }

            }//end of switch choice3
          }//end of if
          break;
        } //case2 of switch choice

      }//end of switch choice

    }// do
    while(!choice.equals("3"));





  }
}