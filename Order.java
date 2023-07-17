import java.util.*;
import java.io.*;
import java.time.*;
/*
Class name: Order
Benedict Waung
Fields:
double price: the price of the order
double wheat: required wheat in kg
double potato: required potato in kg
double corn: required corn in kg
int cow: required cow
double cowWeight: the weight of the cows in kg
int chicken: required chicken
double chickenWeight: the weight of chicken in kg
int turkey: required turkey
double turkeyWeight: the weight of the turkey in kg
int egg: required egg
double milk: required milk in L
int orderID: unique order id
int orderTime: time in form of yyyy-mm-dd
int customerID: the customer who register this order
boolean closeOrder: indicate if the order is closed

static fields:
Price price: the list of price
*/
public class Order{
   //fields
   private double price;
   private double wheat;
   private double potato;
   private double corn;
   private int cow;
   private double cowWeight;
   private int chicken;
   private double chickenWeight;
   private int turkey;
   private double turkeyWeight;
   private int egg;
   private double milk;
   private int orderID;
   private int orderTime;
   private int customerID;
   private boolean closeOrder;
   
   //static fields
   private static Price priceList;
   
   //constructor
   //for register
   public Order(double whe, double pot, double cor, int cows, int chi, int tur, int e, double m, int orderid, int customerid){
      wheat = whe;
      potato = pot;
      corn = cor;
      cow = cows;
      cowWeight = -1;
      chicken = chi;
      chickenWeight = -1;
      turkey = tur;
      turkeyWeight = -1;
      egg = e;
      milk = m;
      orderID = orderid;
      customerID = customerid;
      closeOrder = false;
      orderTime = transferTime("" + LocalDate.now());
      calculatePrice();
      //storing the orderID in a big file
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("OrderList.txt", true));
         out.write(orderID +"\n");
         out.close();
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      //storing the rest in a separate file
      this.saveOrder();
   }
   
   //for loading orders
   public Order(double whe, double pot, double cor, int cows, double coww, int chi, double chiw, int tur, double turw, int e, double m, int orderid, int time, int customerid, boolean status){
      wheat = whe;
      potato = pot;
      corn = cor;
      cow = cows;
      cowWeight = coww;
      chicken = chi;
      chickenWeight = chiw;
      turkey = tur;
      turkeyWeight = turw;
      egg = e;
      milk = m;
      orderID = orderid;
      orderTime = time;
      customerID = customerid;
      closeOrder = status;
      calculatePrice();
   }
   
   //accessor
   public double getWheat(){
      return wheat;
   }
   public double getPotato(){
      return potato;
   }
   public double getCorn(){
      return corn;
   }
   public int getCow(){
      return cow;
   }
   public int getChicken(){
      return chicken;
   }
   public int getTurkey(){
      return turkey;
   }
   public int getEgg(){
      return egg;
   }
   public double getMilk(){
      return milk;
   }
   public int getOrderID(){
      return orderID;
   }
   public boolean isClosed(){
      return closeOrder;
   }
   //mutator
   //Nothing is allowed to change if an order is closed
   public void setCowWeight(double num){
      if(!this.isClosed()&& cow > 0 ){
         cowWeight = num;
      }
   }
   public void setChickenWeight(double num){
      if(!this.isClosed() && chicken > 0){
         chickenWeight = num;
      }
   }
   public void setTurkeyWeight(double num){
      if(!this.isClosed() && turkey > 0){
         turkeyWeight = num;
      }
   }
   public void setOrderTime(int num){
      orderTime = num;
   }
   
   //toString
   public String toString(){
      String coww = "" + cowWeight;
      String chiw = "" + chickenWeight;
      String turw = "" + turkeyWeight;
      if(cowWeight < 0){
         coww = "To be weighed";
      }
      if(chickenWeight < 0){
         chiw = "To be weighed";
      }
      if(turkeyWeight < 0){
         turw = "To be weighted";
      }
   
      return "Order ID: " + orderID +
             "\nCustomer ID: " + customerID +
             "\nWheat: " + wheat + 
             "kg\nPotato: " + potato +
             "kg\nCorn: " + corn +
             "kg\nCow: " + cow + 
             "\nWeight: " + coww+
             "kg\nChicken: " + chicken +
             "\nWeight" + chiw + 
             "kg\nTurkey: " + turkey + 
             "\nWeight: " + turw + 
             "kg\nEgg: " + egg + 
             "\nMilk: " + milk +
             "L\nSubtotal: $" + price +
             "\nOrder time: " + orderTime + "\n";
   }
   
   //other methods
   /*
   boolean calculatedPrice()
   return boolean: indicate if the order has been completely modified
   description: calculate the price of the order
   */
   public boolean calculatePrice(){
      boolean pass = true;
      price = wheat * priceList.getWheatPrice() +
              potato * priceList.getPotatoPrice() +
              corn * priceList.getCornPrice() +
              egg * priceList.getEggPrice() +
              milk * priceList.getMilkPrice();
      
      //check if the weight of cow has been modified
      if (cow <= 0){
      }
      else if(cowWeight > 0){
         price += cowWeight * priceList.getCowPrice();
      }
      else{
         pass = false;
      }
      //check if the weight of chicken has been modified
      if (chicken <= 0){
      }
      else if(chickenWeight > 0){
         price += chickenWeight * priceList.getChickenPrice();
      }
      else{
         pass = false;
      }
      //check if the weight of the turkey has been modified
      if (turkey <= 0){
      }
      else if(turkeyWeight > 0){
         price += turkeyWeight * priceList.getTurkeyPrice();
      }
      else{
         pass = false;
      }
      return pass;
   }
   
   /*
   double close()
   return double: the price of the order
   description: to close the order if it is completely modified and return the price of the order(transaction)
   */
   public double close(){
      if(this.calculatePrice() && !closeOrder){
         closeOrder = true;
         return price;
      }
      return -1;
   }
   
   /*
   void printPriceList()
   description: Print the price out
   */
   public static void printPriceList(){
      System.out.println(priceList);
   }
   
   /*
   boolean saveOrder()
   return boolean: indicate if the saving is successful
   description: to save the order in a separate file since it will be modified frequently
   */
   public boolean saveOrder(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter((orderID + ".txt"), false));
         out.write(wheat + "\n");
         out.write(potato + "\n");
         out.write(corn + "\n");
         out.write(cow + "\n");
         out.write(cowWeight + "\n");
         out.write(chicken + "\n");
         out.write(chickenWeight + "\n");
         out.write(turkey + "\n");
         out.write(turkeyWeight + "\n");
         out.write(egg + "\n");
         out.write(milk + "\n");
         out.write(orderID + "\n");
         out.write(orderTime + "\n");
         out.write(customerID + "\n");
         out.write(closeOrder + "\n");
         out.close();
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean loadPrice()
   return boolean: indicate if the loading is successful
   description: loading the price from a list of price
   */
   public static boolean loadPrice(){
      double wheatPrice;
      double potatoPrice;
      double cornPrice;
      double cowPrice;
      double chickenPrice;
      double turkeyPrice;
      double eggPrice;
      double milkPrice;
      try{
         BufferedReader in = new BufferedReader(new FileReader("priceList.txt"));
         wheatPrice = Double.parseDouble(in.readLine());
         potatoPrice = Double.parseDouble(in.readLine());
         cornPrice = Double.parseDouble(in.readLine());
         cowPrice = Double.parseDouble(in.readLine());
         chickenPrice = Double.parseDouble(in.readLine());
         turkeyPrice = Double.parseDouble(in.readLine());
         milkPrice = Double.parseDouble(in.readLine());
         eggPrice = Double.parseDouble(in.readLine());
         in.close();
         priceList = new Price(wheatPrice, potatoPrice, cornPrice, cowPrice, chickenPrice, turkeyPrice, eggPrice, milkPrice);
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean savePrice()
   return boolean: indicate if the saving is successful
   description: saving the priceList
   */
   public static boolean savePrice(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("priceList.txt", false));
         out.write(priceList.getWheatPrice()+"\n");
         out.write(priceList.getPotatoPrice()+"\n");
         out.write(priceList.getCornPrice()+"\n");
         out.write(priceList.getCowPrice()+"\n");
         out.write(priceList.getChickenPrice()+"\n");
         out.write(priceList.getTurkeyPrice()+"\n");
         out.write(priceList.getEggPrice()+"\n");
         out.write(priceList.getMilkPrice()+"\n");
         out.close();
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   void adjustPrice()
   double wp: wheat price
   double pp: potato price
   double cornp: corn price
   double cowp: cow price
   double chickenp: chicken price
   double tp: turkey price
   double ep: egg price
   double mp: milk price
   description: change the price list
   */
   public static void adjustPrice(double wp, double pp, double cornp, double cowp, double chickenp, double tp, double ep, double mp){
      if(priceList.setWheatPrice(wp)){
         System.out.println("Wheat price has been adjusted");
      }
      if(priceList.setPotatoPrice(pp)){
         System.out.println("Potato price has been adjusted");
      }
      if(priceList.setCornPrice(cornp)){
         System.out.println("Corn price has been adjusted");
      }
      if(priceList.setCowPrice(cowp)){
         System.out.println("Cow price has been adjusted");
      }
      if(priceList.setChickenPrice(chickenp)){
         System.out.println("Chicken price has been adjusted");
      }
      if(priceList.setTurkeyPrice(tp)){
         System.out.println("Turkey price has been adjusted");
      }
      if(priceList.setEggPrice(ep)){
         System.out.println("Egg price has been adjusted");
      }
      if(priceList.setMilkPrice(mp)){
         System.out.println("Milk price has been adjusted");
      }
   }
   
   
   //compareTo
   public int compareToOrderID(Order other){
      return this.orderID - other.orderID;
   }
   public int compareToOrderTime(Order other){
      return this.orderTime - other.orderTime;
   }
   public double compareToPrice(Order other){
      this.calculatePrice();
      other.calculatePrice();
      return this.price - other.price;
   }
   
   //supp method
   /*
   int transferTime(String time)
   String time: the time in form of "yyyy-mm-dd"
   return int: the time in form of "yyyymmdd" for comparing
   */
   private int transferTime(String time){
      return Integer.parseInt(time.replace("-",""));
   }
}