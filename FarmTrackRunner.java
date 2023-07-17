import java.util.*;
public class FarmTrackRunner{
   public static void main(String[] args){
      //variables
      FarmTrack farm;
      boolean load = false;
      boolean quit = false;
      boolean loop1 = false;
      boolean loop2 = false;
      int choice = 0;
      int input = 0;
      Scanner sc = new Scanner(System.in);
      
      //customer
      int info, customerID;
      String fn, ln, ltd;
      
      //order
      double w, p, cor, m, coww, chiw, turw;
      int cow, chi, t, e, orderID;
      //outer loop
      while(!quit){
         System.out.println("Welcome to FarmTrack system.");
         System.out.println("1. New farm");
         System.out.println("2. Load farm");
         System.out.println("3. Quit");
         System.out.print("Please enter the number of the choice: ");
         //nfx handle
         try{
            choice = sc.nextInt();
            sc.nextLine();
         }
         //error message will be printed in the switch block
         catch (NumberFormatException nfx){
            choice = 0;
         }
         
         switch (choice) {
            //create new farm
            case 1:
               System.out.println("Warning: It would cover the old data that exist");
               newFarm();
               break;
            //load farm
            case 2:
               load = farm.loadAll();
               if(load){
                  System.out.println("File successfully loaded");
               }
               //if no FarmTrack.txt detected create new farm
               else{
                  System.out.println("No file was detected.");
                  farm = newFarm();
               }
               break;
            //quit
            case 3:
               quit = true;
               break;
            //error message
            default:
               System.out.println("Please input number that is corresponding to a choice");
         }
         
         //inner loop
         while(load){
            System.out.println("Welcome back to FarmTrack System");
            System.out.println(farm);
            System.out.println("1. Livestock management");
            System.out.println("2. Crop management");
            System.out.println("3. Order management");
            System.out.println("4. Save and quit");
            System.out.println("Please enter the number of the choice");
            //nfx handle
            try{
               choice = sc.nextInt();
               sc.nextLine();
            }
            //error message will be printed in the switch block
            catch (NumberFormatException nfx){
               choice = 0;
            }
         
            switch (choice) {
               //livestock
               case 1:
                  
                  break;
               //cropmanager
               case 2:
                  
                  break;
               //orderManager
               case 3:
                  loop1 = true;
                  while(loop1){
                     //info of the orderManager and the inventory
                     System.out.println(farm.toStringOrderManager());
                     System.out.println(farm.toStringStoreHouse());
                     System.out.println("1. Register new customer");
                     System.out.println("2. Register new order");
                     System.out.println("3. Print all pending orders");
                     System.out.println("4. Print all closed orders");
                     System.out.println("5. Print all customers");
                     System.out.println("6. Update the animal weight for an order");
                     System.out.println("7. Fulfill an order");
                     System.out.println("8. Search customer");
                     System.out.println("9. Search order");
                     System.out.println("Enter the option number or anything else to go back.");
                     try{
                        choice = sc.nextInt();
                        sc.nextLine();
                     }
                     //handle nfx
                     catch(NumberFormatException nfx){
                     choice = 0;
                     }
                     switch(choice){
                        //registration for customer
                        case 1:
                           System.out.print("First name:");
                           fn = sc.nextLine();
                           System.out.print("Last name:");
                           ln = sc.nextLine();
                           System.out.print("Company name:");
                           ltd = sc.nextLine();
                           loop2 = true;
                           while(loop2){
                              try{
                                 System.out.print("Contact info:");
                                 info = sc.nextInt();
                                 sc.nextLine();
                                 //end the loop
                                 loop2 = false;
                              }
                              //handle nfx
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number");
                              }
                           }
                           //registration message
                           if(farm.registerCustomer(fn, ln, ltd, info)){
                              System.out.println("Registered successfully");
                           }
                           else{
                              System.out.println("Registration failed");
                           }
                           break;
                        //registration for order
                        case 2:
                           loop2 = true;
                           while(loop2){
                              try{
                                 System.out.println("Enter the amount of wheat:");
                                 w = sc.nextDouble();
                                 System.out.println("Enter the amount of potato:");
                                 p = sc.nextDouble();
                                 System.out.println("Enter the amount of corn:");
                                 cor = sc.nextDouble();
                                 System.out.println("Enter the amount of cow(Integer):");
                                 cow = sc.nextInt();
                                 System.out.println("Enter the amount of chicken(Integer):");
                                 chi = sc.nextInt();
                                 System.out.println("Enter the amount of turkey(Integer):");
                                 t = sc.nextInt();
                                 System.out.println("Enter the amount of egg(Integer):");
                                 e = sc.nextInt();
                                 System.out.println("Enter the amount of milk:");
                                 m = sc.nextDouble();
                                 System.out.println("Enter the unique customer id:");
                                 customerID = sc.nextInt();
                                 sc.nextLine();
                                 //quit loop
                                 loop2 = false;
                              }
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the approprivate number");
                              }
                           }
                           //registration message
                           if(farm.registerOrder(w, p, cor, cow, chi, t, e, m, customerID)){
                              System.out.println("Registered successfully");
                           }
                           else{
                              System.out.println("Registration failed");
                           }
                           break;
                        //print pending orders
                        case 3:
                           loop2 = true;
                           farm.printPendingOrder(1);
                           while(loop2){
                              System.out.println("Enter the number of the page or -1 to go back:");
                              try{
                                 input = sc.nextInt();
                                 //test if user want to terminate
                                 if(input != -1){
                                    farm.printPendingOrder(input);
                                    sc.nextLine();
                                 }
                                 else{
                                    //quit loop
                                    loop2 = false;
                                 }
                              }
                              //handle nfx
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number.");
                              }
                           }
                           break;
                        //print closed order
                        case 4:
                           loop2 = true;
                           //print base case
                           farm.printClosedOrder(1);
                           while(loop2){
                              System.out.println("Enter the number of the page or -1 to go back:");
                              try{
                                 input = sc.nextInt();
                                 //test if user want to terminate
                                 if(input != -1){
                                    farm.printClosedOrder(input);
                                    sc.nextLine();
                                 }
                                 else{
                                    //quit loop
                                    loop2 = false;
                                 }
                              }
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number.");
                              }
                           }
                           break;
                        //print customer list
                        case 5:
                           loop2 = true;
                           //print base case
                           farm.printAllCustomer(1);
                           while(loop2){
                              System.out.println("Enter the number of the page or -1 to go back:");
                              try{
                                 input = sc.nextInt();
                                 //test if user want to terminate
                                 if(input != -1){
                                    farm.printAllCustomer(input);
                                    sc.nextLine();
                                 }
                                 else{
                                    //quit loop
                                    loop2 = false;
                                 }
                              }
                              //handle nfx
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number.");
                              }
                           }
                           break;
                        //update animal weight
                        case 6:
                           loop2 = true;
                           while(loop2){
                              try{
                                 System.out.println("Enter the order ID:");
                                 orderID = sc.nextInt();
                                 System.out.println("Enter the weight of the cow(negative if not necessary):");
                                 coww = sc.nextDouble();
                                 System.out.println("Enter the weight of the chicken(negative if not necessary):");
                                 chiw = sc.nextDouble();
                                 System.out.println("Enter the weight of the turkey(negative if not necessary):");
                                 turw = sc.nextDouble();
                                 //quit loop
                                 loop2 = false;
                              }
                              //handle nfx
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number.");
                              }
                              //update weight
                              if(farm.updateCowWeight(orderID, coww) && farm.updateChickenWeight(orderID, chiw) && farm.updateTurkeyWeight(orderID, turw)){
                                 System.out.println("Updated successfully");
                              }
                              else{
                                 System.out.println("Order does not exist");
                              }
                           }
                           break;
                        //fulfill order
                        case 7:
                           loop2 = true;
                           while(loop2){
                              try{
                                 System.out.println("Enter the order ID:");
                                 orderID = sc.nextInt();
                                 sc.nextLine();
                                 //quit loop
                                 loop2 = false;
                              }
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number");
                              }
                           }
                           if(farm.fulfill(orderID)){
                              System.out.println("Order finished");
                           }
                           else{
                              System.out.println("Order cannot be fulfilled. Please check your inventory or the modification of the order.");
                           }
                           break;
                        //search customer
                        case 8:
                           loop2 = true;
                           while(loop2){
                              try{
                              System.out.println("Enter the customer id:");
                              System.out.println(farm.searchCustomerID(sc.nextInt()));
                              sc.nextLine();
                              //quit loop
                              loop2 = false;
                              }
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number");
                              }
                           }
                           break;
                        //search order
                        case 9:
                           loop2 = true;
                           while(loop2){
                              try{
                              System.out.println("Enter the order id:");
                              System.out.println(farm.searchOrderID(sc.nextInt()));
                              sc.nextLine();
                              //quit loop
                              loop2 = false;
                              }
                              catch(NumberFormatException nfx){
                                 System.out.println("Please enter the appropriate number");
                              }
                           }
                           break;
                        //go back to the previous stage
                        default:
                           loop1 = false;
                           break;
                     }
                  }
                  break;
               //save and quit
               case 4:
                  if(farm.saveAll()){
                     System.out.println("Saved successfully");
                     load = false;
                     quit = false;
                  }
                  else{
                     System.out.println("Saving failed");
                  }
                  break;
               //error message
               default:
                  System.out.println("Please input number that is corresponding to a choice");
            }
         }   
      }
   }
   /*
   void newFarm()
   drescription: create new Farm.txt and the file it needs
   */
   public static FarmTrack newFarm(){
      Scanner sc = new Scanner(System.in);
      boolean pass = false;
      double capital, mwc, mpc, mcc;
      int mon, mcn;
      //set up FarmTrack
      System.out.println("Please fill in some infomation for your farm to get started.");
      System.out.print("The name of the farm: ");
      String name = sc.nextLine();
      //set up order system
      System.out.println("Great! Let's set up for the order system.");
      while(!pass){
         try{
            //all the required field for the OrderManager
            System.out.print("Your starting budget: ");
            capital = sc.nextDouble();
            System.out.print("The maximum number of orders that your farm can hold: ");
            mon = sc.nextInt();
            System.out.print("The maximum number of orders that your farm can hold: ");
            mcn = sc.nextInt();
            pass = true;
         }
         catch(NumberFormatException nfx){
            System.out.println("Please enter an appropriate number.");
         }
      }
      pass = false;
      //set up for storage
      System.out.println("Great! Let's set up for the storage system.");
      while(!pass){
         try{
            //all the required field for the StoreHouse
            System.out.print("The maximum capacity of wheat: "); 
            mwc = sc.nextDouble();
            System.out.print("The maximum capacity of potato: "); 
            mpc = sc.nextDouble();
            System.out.print("The maximum capacity of corn: "); 
            mcc = sc.nextDouble();
            pass = true;
         }
         catch(NumberFormatException nfx){
            System.out.println("Please enter an appropriate number.");
         }
      }
      pass = false;
      //set up for livestock
      System.out.println("Great! Let's set up for the livestock system.");
      while(!pass){
         try{
            //all the required field for the Livestock
            
            pass = true;
         }
         catch(NumberFormatException nfx){
            System.out.println("Please enter an appropriate number.");
         }
      }
      pass = false;
      //set up for CropManager
      System.out.println("Great! Let's set up for the Crop system.");
      while(!pass){
         try{
            //all the required field for the CropManager
            
            pass = true;
         }
         catch(NumberFormatException nfx){
            System.out.println("Please enter an appropriate number.");
         }
      }
      return (new FarmTrack(name, new OrderManager(capital, mon, mcn), new StoreHouse(mwc, mpc, mcc), new Livestock(), new CropManager()));
   }
}