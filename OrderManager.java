import java.util.*;
import java.io.*;
/*
Class name: OrderManager
Benedict Waung
fields:
double capital: the capital that the farm owns
int maxOrderNum: the maximun order that the order manager can hold
int maxCustomerNum: the maximun ustomer that the order manager can hold
int numOrder: the num of order that stored in the order manager
int numCustomer: the num of customer that registered
Order[] orderList: the order list
Customer[] customerList: the customer list

constant:
int START_ORDER_ID: the first order ID that is given to the order
int START_CUSTOMER_ID: the first customer ID that is given to the customer
*/
public class OrderManager{
   //fields
   private double capital;
   private int maxOrderNum;
   private int maxCustomerNum;
   private int numOrder;
   private int numCustomer;
   private Order[] orderList;
   private Customer[] customerList;
   
   //constant
   private static final int START_ORDER_ID = 10000000;
   private static final int START_CUSTOMER_ID = 10000;
   
   //constructor
   public OrderManager(double money, int mon, int mcn){
      capital = money;
      maxOrderNum = mon;
      Customer.setMaxHistoryOrder(mon);
      maxCustomerNum = mcn;
      numOrder = 0;
      numOrder = 0;
      orderList = new Order[mon];
      customerList = new Customer[mcn];
      Order.loadPrice();
      loadOrderList();
      loadCustomerList();
   }
   
   //toString
   public String toString(){
      return "Capital: " + capital +
             "\nOrder stored: " + numOrder + "(max: " + maxOrderNum + 
             ")\nCustomer registered: " + numCustomer + "(max: " + maxCustomerNum + ")\n";
             
   }
   
   //other methods
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
      Customer targetCustomer = searchCustomerID(customerid);
      if(numOrder < maxOrderNum && targetCustomer != null){
         orderList[numOrder] = new Order(w, p, cor, cow, chi, t, e, m, (START_ORDER_ID + numOrder), customerid);
         targetCustomer.newOrder(orderList[numOrder]);
         numOrder++;
         return true;
      }
      return false;
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
      if(numCustomer > maxCustomerNum){
         customerList[numCustomer] = new Customer(fn, ln, (START_CUSTOMER_ID + numCustomer), ltd, info, true);
         numCustomer++;
         return true;
      }
      return false;
   }
   
   /*
   boolean updateCowWeight()
   int id: the unique orderID
   double coww: the cow weight
   return boolean: indicate if the order id was found
   description: update the cows' weight
   */
   public boolean updateCowWeight(int id, double coww){
      Order target = searchOrderID(id);
      if(target != null){
         target.setCowWeight(coww);
         return true;
      }
      return false;
   }
   
   /*
   boolean updateChickenWeight()
   int id: the unique orderID
   double chiw: the chicken weight
   return boolean: indicate if the order id was found
   description: update the chickens' weight
   */
   public boolean updateChickenWeight(int id, double chiw){
      Order target = searchOrderID(id);
      if(target != null){
         target.setChickenWeight(chiw);
         return true;
      }
      return false;
   }
   
   /*
   boolean updateTurkeyWeight()
   int id: the unique orderID
   double turw: the turkey weight
   return boolean: indicate if the order id was found
   description: update the turkeys' weight
   */
   public boolean updateTurkeyWeight(int id, double turw){
      Order target = searchOrderID(id);
      if(target != null){
         target.setTurkeyWeight(turw);
         return true;
      }
      return false;
   }
   
   /*
   boolean transact()
   double amount: the amount to be transacted(+ for cash in, - for cash out)
   return boolean: is the transaction successful
   description: running the transaction
   */
   public boolean transact(double amount){
      if(amount < 0 && capital < amount*-1){
         return false;
      }
      capital += amount;
      return true;
   }
   
   /*
   boolean fulfill()
   int id: the unique order id
   return boolean: indicate if the order has been successfully closed
   description: complete the order match the order id
   */
   public boolean fulfill(int id){
      Order target = searchOrderID(id);
      if(target != null){
         double money = target.close();
         if(money != -1){
            transact(money);
            return true;
         }
      }
      return false;
   }
   
   //printing
   /*
   void printPendingOrder()
   int page: the page of the pending order
   description: print all the pending order
   */
   public void printPendingOrder(int page){
      //filter the pending orders out
      int numPendingOrder = 0;
      Order[] pendingOrder = new Order[numOrder];
      for(int i = 0; i < numOrder;i++){
         if(!orderList[i].isClosed()){
            pendingOrder[numPendingOrder] = orderList[i];
            numPendingOrder++;
         }
      }
      //calculate total page
      int totalPage = numPendingOrder/10;
      if(numPendingOrder%10 > 0){
         totalPage++;
      }
      //test if the page is valid
      if(page <= totalPage && page > 0){
         System.out.println("Pending order list(Page: " +page+ "/"+totalPage+"):");
         for(int i = 10*(page-1);i < page*10 && pendingOrder[i] != null; i++){
            System.out.println(pendingOrder[i]+"\n");
         }
      }
      else{
         System.out.println("Invalid page number");
      }
   }
   
   /*
   void printClosedOrder()
   int page: the page of the closed order
   description: print all the closed order
   */
   public void printClosedOrder(int page){
      //filter the close orders out
      int numClosedOrder = 0;
      Order[] closedOrder = new Order[numOrder];
      for(int i = 0; i < numOrder;i++){
         if(orderList[i].isClosed()){
            closedOrder[numClosedOrder] = orderList[i];
            numClosedOrder++;
         }
      }
      //calculate total page
      int totalPage = numClosedOrder/10;
      if(numClosedOrder%10 > 0){
         totalPage++;
      }
      //test if the page is valid
      if(page <= totalPage && page > 0){
         System.out.println("Pending order list(Page: " +page+ "/"+totalPage+"):");
         for(int i = 10*(page-1);i < page*10 && closedOrder[i] != null; i++){
            System.out.println(closedOrder[i]+"\n");
         }
      }
      else{
         System.out.println("Invalid page number");
      }
   }
   
   /*
   void printAllcustomer()
   int page: the page of the customer list
   description: print all the customer
   */
   public void printAllCustomer(int page){
      //calculate total page
      int totalPage = numCustomer/10;
      if(numCustomer%10 > 0){
         totalPage++;
      }
      if(page <= totalPage&& page >0){
         System.out.println("Customer List(Page: " +page+ "/"+totalPage+"):");
         for(int i = 10*(page-1);i < page*10 && customerList[i] != null; i++){
            System.out.println(customerList[i]+"\n");
         }
      }
      else{
         System.out.println("Invalid page number");
      }
   }
   
   //saving and loading
   /*
   boolean loadOrderList()
   return boolean: indicate if the loading is successful
   description: reading the file storing all the orderID and calling loadOrder()
   */
   public boolean loadOrderList(){
      int numOfOrder;
      try{
         BufferedReader in = new BufferedReader(new FileReader("OrderList.txt"));
         numOfOrder = Integer.parseInt(in.readLine());
         //terminate the loop if loadOrder() failed
         for(int i = 0; i < numOfOrder && loadOrder(Integer.parseInt(in.readLine())); i++){
         }
         in.close();
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   /*
   boolean loadOrder()
   int orderID: the file name to be loaded
   return boolean: indicate if the loading is successful
   description: loading the order info from the file
   */
   public boolean loadOrder(int orderID){
      double wheat = 0;
      double potato = 0;
      double corn = 0;
      int cow = 0;
      double coww = 0;
      int chicken = 0;
      double chickenw = 0;
      int turkey = 0;
      double turkeyw = 0;
      int egg = 0;
      double milk = 0;
      int orderid = 0;
      int time = 0;
      int customerid = 0;
      boolean closed = false;
      try{
         BufferedReader in = new BufferedReader(new FileReader(orderID+".txt"));
         wheat = Double.parseDouble(in.readLine());
         potato = Double.parseDouble(in.readLine());
         corn = Double.parseDouble(in.readLine());
         cow = Integer.parseInt(in.readLine());
         coww = Double.parseDouble(in.readLine());
         chicken = Integer.parseInt(in.readLine());
         chickenw = Double.parseDouble(in.readLine());
         turkey = Integer.parseInt(in.readLine());
         turkeyw = Double.parseDouble(in.readLine());
         egg = Integer.parseInt(in.readLine());
         milk = Double.parseDouble(in.readLine());
         orderid = Integer.parseInt(in.readLine());
         time = Integer.parseInt(in.readLine());
         customerid = Integer.parseInt(in.readLine());
         closed = in.readLine().equals("true");
         in.close();
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
         return false;
      }
      if(numOrder < maxOrderNum){
         orderList[numOrder] = new Order(wheat, potato, corn, cow, coww, chicken, chickenw, turkey, turkeyw, egg, milk, orderid, time, customerid, closed);
         numOrder++;
         return true;
      }
      System.out.println("Order limit exceed");
      return false;
   }
   
   /*
   boolean saveOrder()
   return boolean: indicate if the saving is successful
   description: run the saveOrder() in Order class
   */
   public boolean saveOrder(){
      boolean result = true;
      for(int i = 0; i < numOrder; i++){
         result = result && orderList[i].saveOrder();
      }
      return result;
   }
   
   /*
   boolean loadCustomerList()
   return indicate if the loading is successful
   description:  reading the file storing all the customer id and calling loadCustomer()
   */
   public boolean loadCustomerList(){
      int numOfCustomer;
      try{
         BufferedReader in = new BufferedReader(new FileReader("CustomerList.txt"));
         numOfCustomer = Integer.parseInt(in.readLine());
         //terminate the loop if loadCustomer() failed
         for(int i = 0; i < numOfCustomer && this.loadCustomer(Integer.parseInt(in.readLine())); i++){
         }
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean loadCustomer()
   int customerid: the filename to be loaded
   return boolean: indicate if the loading is successful
   description: loading the customer info from the file
   */
   public boolean loadCustomer(int customerid){
      String fn;
      String ln;
      int id;
      String company;
      int info;
      int orderNum;
      try{
         BufferedReader in = new BufferedReader(new FileReader(customerid + ".txt"));
         fn = in.readLine();
         ln = in.readLine();
         id = Integer.parseInt(in.readLine());
         company = in.readLine();
         info = Integer.parseInt(in.readLine());
         if(numCustomer < maxCustomerNum){
            customerList[numCustomer] = new Customer(fn, ln, id, company, info, false);
            orderNum = Integer.parseInt(in.readLine());
            //inserting the orders
            for(int i = 0; i < orderNum; i++){
               customerList[numCustomer].newOrder(searchOrderID(Integer.parseInt(in.readLine())));
            }
            numCustomer++;
            in.close();
            return true;
         }
         else{
            System.out.println("Customer limit exceed");
         }
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean saveCustomer()
   return boolean: indicate if the saving is successful
   description: run the saveCustomer() in Customer class
   */
   public boolean saveCustomer(){
      boolean result = true;
      for(int i = 0; i < numCustomer; i++){
         result = result && customerList[i].saveCustomer();
      }
      return result;
   }
   
   /*
   boolean saveManager()
   return boolean: indicate if the saving is successful
   description: saving the class OrderManager
   */
   public boolean saveManager(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("OrderManager.txt"));
         out.write(capital+"\n");
         out.write(maxOrderNum+"\n");
         out.write(maxCustomerNum+"\n");
         out.close();
         return true && saveOrder() && saveCustomer();
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   //searching
   /*
   Customer searchCustomerID()
   int id: the unique customer id
   return customer: the customer that matches the id
   description: the wrapper method of searchCustomer(int id, int bot, int top)
   */
   public Customer searchCustomerID(int id){
      sortingCustomerInCustomerID(true);
      return searchCustomerID(id, 0, numCustomer);
   }
   
   /*
   Customer searchCustomer(int id, int bot, int top)
   int id: the unique customer id
   return customer: the customer that matches the id
   description: return a customer that matches the id from customerList by recursion and binary search
   */
   private Customer searchCustomerID(int id, int bot, int top){
      int mid = (bot + top)/2;
      if(bot > top){
         return null;
      }
      int compareID = customerList[mid].getCustomerID();
      if(id == compareID){
         return customerList[mid];
      }
      else if(id > compareID){
         return searchCustomerID(id, mid+1, top);
      }
      else{
         return searchCustomerID(id, bot, mid-1);
      }
   }
   
   /*
   Order searchOrder()
   int id: the unique order id
   return order: the order that matches the id
   description: return an order that matches the id from orderList by linear search
   */
   public Order searchOrderID(int id){
      for(int i = 0; i < numOrder; i++){
         if(id == orderList[i].getOrderID()){
            return orderList[i];
         }
      }
      return null;
   }
   
   //sorting
   /*
   void sortingOrderInPrice()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting order in price with insertion sort
   */
   public void sortingOrderInPrice(boolean ascending){
      Order target;
      int idx;
      for(int i = 1; i < numOrder; i++){
         target = orderList[i];
         idx = i;
         //ascending
         if(ascending){
            for(int j = i; j > 0 && target.compareToPrice(orderList[j-1])<0; j--){
               orderList[j] = orderList[j-1];
               idx = j-1;
            }
         }
         //descending
         else{
            for(int j = i; j > 0 && target.compareToPrice(orderList[j-1])>0; j--){
               orderList[j] = orderList[j-1];
               idx = j-1;
            }
         }
         orderList[idx] = target;
      }
   }
   
   /*
   void sortingOrderInOrderID()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting order in id with selection sort
   */
   public void sortingOrderInOrderID(boolean ascending){
      Order target;
      int idx;
      for(int i = 0; i < numOrder; i++){
         target = orderList[i];
         idx = i;
         //ascending
         if(ascending){
            for(int j = i; j < numOrder; j++){
               if(orderList[idx].compareToOrderID(orderList[j])>0){
                  idx = j;
               }
            }
            orderList[i] = orderList[idx];
            orderList[idx] = target;
         }
         //descending
         else{
            for(int j = i; j < numOrder; j++){
               if(orderList[idx].compareToOrderID(orderList[j])<0){
                  idx = j;
               }
            }
            orderList[i] = orderList[idx];
            orderList[idx] = target;
         }
      }
   }
   
   /*
   void sortingCustomerInCustomerID()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting customer in id with bubble sort
   */
   public void sortingCustomerInCustomerID(boolean ascending){
      int idx;
      Customer temp;
      boolean sorted = false;
      for(int i = 0; i< numCustomer && !sorted; i++){
         sorted = true;
         for(int j = numCustomer-1; j > i; j--){
            //ascending
            if(ascending){
               if(customerList[j].compareToCustomerID(customerList[j-1])<0){
                  temp = customerList[j];
                  customerList[j] = customerList[j-1];
                  customerList[j-1] = temp;
                  sorted = false;
               }
            }
            //descending
            else{
               if(customerList[j].compareToCustomerID(customerList[j-1])>0){
                  temp = customerList[j];
                  customerList[j] = customerList[j-1];
                  customerList[j-1] = temp;
                  sorted= false;
               }
            }
         }
      }
   }
   
   /*
   void sortingCustomerInName()
   boolean ascending: sort ascendingly if true, otherwise descendingly
   description: sorting customer in first name or last name with insertion sort
   */
   public void sortingCustomerInName(boolean ascending){
      Customer target;
      int idx;
      for(int i = 1; i < numCustomer; i++){
         target = customerList[i];
         idx = i;
         //ascending
         if(ascending){
            for(int j = i; j > 0 && (target.compareToFirstName(customerList[j-1])<0 || target.compareToLastName(customerList[j-1]) < 0); j--){
               customerList[j] = customerList[j-1];
               idx = j-1;
            }
         }
         //descending
         else{
            for(int j = i; j > 0 && (target.compareToFirstName(customerList[j-1])>0 || target.compareToLastName(customerList[j-1]) > 0); j--){
               customerList[j] = customerList[j-1];
               idx = j-1;
            }
         }
         customerList[idx] = target;
      }
   }
}