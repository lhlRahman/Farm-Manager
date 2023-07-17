import java.io.*;
/*
Class name: FarmTrack
Benedict Waung, Brian Cai, Habib Rahman
Fields:
String name: the name of the farm
int dayPass: the day has passed
Livestock livestockManager: the managing system of Livestock
OrderManager orderManager: the managing system of order
StoreHouse storage: the storage system for crops
CropManager cropManager: the managing system for crops
*/
public class FarmTrack{
   //fields
   private String name;
   private int dayPass;
   private Livestock livestockManager;
   private OrderManager orderManager;
   private StoreHouse storage;
   private CropManager cropManager;
   
   //constructor
   /*
   String n: the name of the farm
   OrderManager om: the OrderManager of the farm
   StoreHouse: the StoreHouse of the farm
   Livestock: the livestockManager of the farm 
   description: create a new farm of all the element above
   */
   public FarmTrack(String n,  OrderManager om, StoreHouse sto, Livestock ls, CropManager cm){
      name = n;
      dayPass = 0;
      orderManager = om;
      storage = sto;
      livestockManager = ls;
      cropManager = cm;
      saveAll();
   }
   //toString
   public String toString(){
      return "Farm name: " + name +
             "\nDay: " + dayPass + "\n";
   }
   public String toStringOrderManager(){
      return "" + orderManager;
   }
   public String toStringStoreHouse(){
      return "" + storage;
   }
   //saving and loading
   
   /*
   boolean saveAll()
   return boolean: indicate if the saving is successful
   description: calling all the saving method
   */
   public boolean saveAll(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("Farm.txt"));
         out.write(name+"\n"+ dayPass);
         out.close();
         return saveOrderManager() &&
                saveStoreHouse() &&
                saveLivestock() &&
                cropManager.saveCropManager();
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean loadAll()
   return boolean: indicate if the loading is successful
   description: calling all the loading method
   */
   public boolean loadAll(){
      try{
         BufferedReader in = new BufferedReader(new FileReader("Farm.txt"));
         name = in.readLine();
         dayPass = Integer.parseInt(in.readLine());
         in.close();
         return loadOrderManager() &&
                loadStoreHouse() &&
                loadLivestock() &&
                cropManager.loadCropManager();
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   //OrderManager (Benedict)
   /*
   boolean registerOrder()
   double w: num of wheat
   double p: num of potato
   double cor: num of corn
   int cow: num of cow
   int chi: num of chicken
   int t: num of turkey
   int e: num of egg
   double milk: num of milk
   int customerid: unique customer id
   return boolean: indicate if the registration is successful
   description: register a new order
   */
   public boolean registerOrder(double w, double p, double cor, int cow, int chi, int t, int e, double m, int customerid){
      return orderManager.registerOrder(w, p, cor, cow, chi, t, e, m, customerid);
   }
   
   /*
   boolean registerCustomer()
   String fn: first name
   String ln: last name
   String ltd: company name
   int info: contact info
   return boolean: indicate if the registration is successful
   description: register a new customer
   */
   public boolean registerCustomer(String fn, String ln, String ltd, int info){
      return orderManager.registerCustomer(fn, ln, ltd, info);
   }
   
   /*
   boolean transact()
   double amount: the amount to be transacted(+ for cash in, - for cash out)
   return boolean: is the transaction successful
   description: running the transaction
   */
   public boolean transact(double amount){
      return orderManager.transact(amount);
   }
   
   /*
   boolean updateCowWeight()
   int id: the unique orderID
   double coww: the cow weight
   return boolean: indicate if the order id was found
   description: update the cows' weight
   */
   public boolean updateCowWeight(int id, double coww){
      return orderManager.updateCowWeight(id, coww);
   }
   
   /*
   boolean updateChickenWeight()
   int id: the unique orderID
   double chiw: the chicken weight
   return boolean: indicate if the order id was found
   description: update the chickens' weight
   */
   public boolean updateChickenWeight(int id, double chiw){
      return orderManager.updateChickenWeight(id, chiw);
   }
   
   /*
   boolean updateTurkeyWeight()
   int id: the unique orderID
   double turw: the turkey weight
   return boolean: indicate if the order id was found
   description: update the turkeys' weight
   */
   public boolean updateTurkeyWeight(int id, double turw){
      return orderManager.updateTurkeyWeight(id, turw);
   }
   
   /*
   boolean fulfill()
   int id: the unique order id
   return boolean: indicate if the order has been successfully closed
   description: complete the order match the order id if all the conditions fulfill
   */
   public boolean fulfill(int id){
      boolean w, p, c, e, m;
      Order target = searchOrderID(id);
      //all the conditions
      w = pullOutWheat(target.getWheat());
      p = pullOutPotato(target.getPotato());
      c = pullOutCorn(target.getCorn());
      e = pullOutEggs(target.getEgg());
      m = pullOutMilk(target.getMilk());
      //if the condition matches run then fulfill the order
      //if the order wasn't modified completely
      if(w && p && c && e && m && target.calculatePrice()){
         return orderManager.fulfill(id);
      }
      //if the condition doesn't match
      else{
         //store the stuff back if they were pull out
         if(w){
            storage.storeWheat(target.getWheat());
         }
         if(p){
            storage.storePotato(target.getPotato());
         }
         if(c){
            storage.storeCorn(target.getCorn());
         }
         if(e){
            livestockManager.storeEgg(target.getEgg());
         }
         if(m){
            livestockManager.storeMilk(target.getMilk());
         }
      }
      return false;
   }
   
   /*
   void printPendingOrder()
   int page: the page of the pending order
   description: print all the pending order
   */
   public void printPendingOrder(int page){
      orderManager.printPendingOrder(page);
   }
   
   /*
   void printClosedOrder()
   int page: the page of the closed order
   description: print all the closed order
   */
   public void printClosedOrder(int page){
      orderManager.printClosedOrder(page);
   }
   
   /*
   void printAllCustomer()
   int page: the page of the customer list
   description: print all the customer
   */
   public void printAllCustomer(int page){
      orderManager.printAllCustomer(page);
   }
   
   //saving and loading
   /*
   boolean loadOrderManager()
   return boolean: indicate if the loading is successful
   description: load the OrderManager.txt file
   */
   public boolean loadOrderManager(){
      double capital;
      int mon;
      int mcn;
      try{
         BufferedReader in = new BufferedReader(new FileReader("OrderManager.txt"));
         capital = Double.parseDouble(in.readLine());
         mon = Integer.parseInt(in.readLine());
         mcn = Integer.parseInt(in.readLine());
         orderManager = new OrderManager(capital, mon, mcn);
      }
      catch(IOException iox){
         System.out.println("Problem accessing the file");
      }
   }
   
   /*
   boolean saveOrderManager()
   return boolean: indicate if the saving is successful
   description: save the OrderManager
   */
   public boolean saveOrderManager(){
      return orderManager.saveManager();
   }
   
   //searching
   /*
   Customer searchCustomerID()
   int id: the unique customer id
   return customer: the customer that matches the id
   description: the wrapper method of searchCustomer(int id, int bot, int top)
   */
   public Customer searchCustomerID(int id){
      return orderManager.searchCustomerID(id);
   }
      
   /*
   Order searchOrder()
   int id: the unique order id
   return order: the order that matches the id
   description: return an order that matches the id from orderList by linear search
   */
   public Order searchOrderID(int id){
      return orderManager.searchOrderID(id);
   }
   
   //sorting
   /*
   void sortingOrderInPrice()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting order in price with insertion sort
   */
   public void sortingOrderInPrice(boolean ascending){
      orderManager.sortingOrderInPrice(ascending);
   }
   
   /*
   void sortingOrderInOrderID()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting order in id with selection sort
   */
   public void sortingOrderInOrderID(boolean ascending){
      orderManager.sortingOrderInOrderID(ascending);
   }
   
   /*
   void sortingCustomerInCustomerID()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting customer in id with bubble sort
   */
   public void sortingCustomerInCustomerID(boolean ascending){
      orderManager.sortingCustomerInCustomerID(ascending);
   }
   
   /*
   void sortingCustomerInName()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting customer in first name or last name with insertion sort
   */
   public void sortingCustomerInName(boolean ascending){
      orderManager.sortingCustomerInName(ascending);
   }
   
   //StoreHouse(Benedict)
   /*
   boolean storeWheat()
   double num: the amount of wheat to be stored
   return boolean: true if limit exceed, other false
   description: store the wheat into the storage
   */
   public boolean storeWheat(double num){
      return storage.storeWheat(num);
   }
   
   /*
   boolean storePotato()
   double num: the amount of potato to be stored
   return boolean: true if limit exceed, other false
   description: store the potato into the storage
   */
   public boolean storePotato(double num){
      return storage.storePotato(num);
   }
   
   /*
   boolean storeCorn()
   double num: the amount of corn to be stored
   return boolean: true if limit exceed, other false
   description: store the corn into the storage
   */
   public boolean storeCorn(double num){
      return storage.storeCorn(num);
   }
   
   /*
   boolean pullOutWheat()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out wheat from the storage
   */
   public boolean pullOutWheat(double num){
      return storage.pullOutWheat(num);
   }
   
   /*
   boolean pullOutPotato()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out potato from the storage
   */
   public boolean pullOutPotato(double num){
      return storage.pullOutPotato(num);
   }
   
   /*
   boolean pullOutCorn()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out corn from the storage
   */
   public boolean pullOutCorn(double num){
      return storage.pullOutCorn(num);
   }
   
   //saving and loading
   /*
   boolean saveStoreHouse
   return boolean: indicate if the saving is successful
   description: save the info in storehouse to a file
   */
   public boolean saveStoreHouse(){
      return storage.saveStoreHouse();
   }
   
   /*
   boolean loadStoreHouse
   return boolean: indicate if the loading is successful
   description: load the info of storehouse from a file
   */
   public boolean loadStoreHouse(){
      double mwc, mpc, mcc, w, p, c;
      try{
         BufferedReader in = new BufferedReader(new FileReader("StoreHouse.txt"));
         mwc = Double.parseDouble(in.readLine());
         mpc = Double.parseDouble(in.readLine());
         mcc = Double.parseDouble(in.readLine());
         w = Double.parseDouble(in.readLine());
         p = Double.parseDouble(in.readLine());
         c = Double.parseDouble(in.readLine());
         in.close();
         storage = new StoreHouse(mwc, mpc, mcc);
         storage.storeWheat(w);
         storage.storePotato(p);
         storage.storeCorn(c);
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
   }

  public double buyCowFeed(int amountFeed) {
    return livestockManager.buyCowFeed(amountFeed);
  }

  // returns and removed heaveist num amount of turkeys
  // same for every other animal
  public double pullOutCow(int num) {
livestockManager.pullOutCow(num);
  }

  public double pullOutChicken(int num) {
livestockManager.pullOutTurkey(num);
  }


  public double pullOutTurkey(int num) {
    return livestockManager.pullOutTurkey(num);
  }

 

  // checks if required milk can be deducted from total milk then returns booleans
  // based on this
  public boolean pullOutMilk(double requiredMilk) {
return livestockManager.pullOutMilk(requiredMilk);
  }

  // same with milk
  public boolean pullOutEggs(int requiredEggs) {
livestockManager.pullOutEggs(requiredEggs);
  }
  // Adds an animal to their respective array checks if array can fit any more,
  // returns false if cant fit anymore true if it can fit more, useful for the
  // interface

  public boolean addAnimal(Animal animal) {
 return livestockManager.addAnimal(animal);
  }

  // sorts cow from lightest to heavist for pullOutCow()
  // fills in null objects with the object after and repeats until all holes
  // filled
  // utilizes bubble sort
  public void sortCow() {
livestockManager.sortCow();
  }

  public void sortChicken() {
livestockManager.sortChicken();
  }

  public void sortTurkey() {
livestockManager.sortTurkey();
  }

  public boolean loadLivestock() {
    return livestockManager.loadLivestock();
  }

  // saves to "livestock.txt"
  // uses a method called .toStringForWriter() that formates everything for a
  // writer!!!
  public boolean saveLivestock() {
    return livestockManager.saveLivestock();
  }
  /*
   boolean loadCropManager
   return boolean: indicate if the loading is successful
   description: load the info of CropManager from a file
   */
   public boolean loadCropManager(){
       return CropManager.load();
   }
    /*
   boolean saveCropManager
   return boolean: indicate if the saving is successful
   description: save the info of saveCropManager from a file
   */
   public boolean saveCropManager(){
       return CropManager.save();
   }
    /*
   double buyFertilizer
   return double: indicate the price of that amount of fertilizer
   description: save the info of buyFertilizer from a file
   */
   public double buyFertilizer(int amount) {
      return CropManager.buyFertilizer(amount);
   }
    /*
   boolean fertilizeAllCrops
   return boolean: indicate if the fertilizing is successful
   description: fertilizing all crops
   */
   public boolean fertilizeAllCrops() {
       return CropManager.fertilizeAllCrops();
   }
    /*
   boolean cropDiscard
   return boolean: indicate if the cropDiscard is successful
   description: discard a certiant crop by index
   */
   public boolean cropDiscard(int index) {
       return CropManager.discard(index);
   }
   /*
   Double harvestPotato
   return double: the amount of yield
   description: harvest certiant crop by index
   */
    public double harvestPotato() {
        return CropManager.harvestPotato();
    }
       /*
   Double harvestCorn
   return double: the amount of yield
   description: harvest certiant crop by index
   */
    public double harvestCorn() {
        return CropManager.harvestCorn();
    }
    /*
   Double harvestWheat
   return double: the amount of yield
   description: harvest certiant crop by index
   */
     public double harvestWheat() {
        return CropManager.harvestWheat();
    }
    /*
   void harvestWheat
   return void:
   description: prints all crop types
   */
    public void printAllCropTypes() {
        CropManager.printAllCropTypes();
    }
    /*
   boolean plantPotato
   return boolean: indicate if the plantPotato is successful
   description: plants the crop
   */
    public boolean plantPotato(int numCrops) {
        return CropManager.plantPotato(numCrops);
    }
    /*
   boolean plantCorn
   return boolean: indicate if the plantCorn is successful
   description: plants the crop
   */
    public boolean plantCorn(int numCrops) {
        return CropManager.plantCorn(numCrops);
    }
    /*
   boolean plantWheat
   return boolean: indicate if the plantWheat is successful
   description: plants the crop
   */
    public boolean plantWheat(int numCrops) {
        return CropManager.plantWheat(numCrops);
    }
    /*
   void printCropStatus
   return void:
   description: prints a status of all crops
   */
    public void printCropStatus() {
        CropManager.printCropStatus();
    }
    /*
   Potato oldestPotato
   return Potato: Returns the oldest potato
   description: prints the oldest of a certiant crop
   */
    public Potato oldestPotato() {
       return CropManager.oldestPotato();
    }
        /*
   Wheat oldestWheat
   return Wheat: Returns the oldest Wheat
   description: prints the oldest of a certiant crop
   */
    public Wheat oldestWheat() {
       return CropManager.oldestWheat();
    }
            /*
   Wheat oldestCorn
   return Corn: Returns the oldest Corn
   description: prints the oldest of a certiant crop
   */
    public Corn oldestCorn() {
       return CropManager.oldestCorn();
    }
                /*
   Crop oldestCrop
   return Crop: Returns the oldest Crop
   description: prints the oldest of a certiant crop
   */
    public Crop oldestCrop() {
       return CropManager.oldestCrop();
    }

}