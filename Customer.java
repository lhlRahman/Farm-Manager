import java.util.*;
import java.io.*;
/*
Class Name: Customer
Benedict Waung
Fields:
String firstName: first name of the customer
String lastName: last name of the customer
int customerID: the unique customer id
String company: the company the customer work for
int numOrder: the num of order that the customer have ordered so far
int contactInfo: the contact infomation of the customer
Order[] historyOrder: the history order of the customer

Static fields:
maxHistoryOrder: the maximun history order that the system could store
*/
public class Customer{
   //fields
   private String firstName;
   private String lastName;
   private int customerID;
   private String company;
   private int contactInfo;
   private int numOrder;
   private Order[] historyOrder;
   //static field
   private static int maxHistoryOrder;
   
   //constructor
   public Customer(String fn, String ln, int id, String ltd, int contact, boolean newCustomer){
      firstName = fn;
      lastName = ln;
      customerID = id;
      company = ltd;
      numOrder = 0;
      contactInfo = contact;
      historyOrder = new Order[maxHistoryOrder];
      //proceed only if there is a new customer
      if(newCustomer){
         //storing the customerID in a big file
         try{
            BufferedWriter out = new BufferedWriter(new FileWriter("CustomerList.txt", true));
            out.write(customerID +"\n");
            out.close();
         }
         catch(IOException iox){
            System.out.println("Problem accessing file");
         }
         //storing the rest in a separate file
         this.saveCustomer();
      }
   }
   
   
   //toString
   public String toString(){
      return "Customer ID: " + customerID +
             "\nFirst name: " + firstName +
             "\nLast name:" + lastName +
             "\nCompany name: " + company +
             "\nContact info: " + contactInfo +
             "\n# Orders: " + numOrder + "\n";
   }
   
   //accessor
   public int getCustomerID(){
      return customerID;
   }
   //mutator
   public static void setMaxHistoryOrder(int mho){
      if(mho > 0){
         maxHistoryOrder = mho;
      }
   }

   
   //other methods
   /*
   boolean saveCustomer()
   return boolean: indicate if the saving is successful
   description: save the customer info to a separate file since it will be modified frequently
   */
   public boolean saveCustomer(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter(customerID+".txt"));
         out.write(firstName+"\n");
         out.write(lastName+"\n");
         out.write(customerID+"\n");
         out.write(company+"\n");
         out.write(contactInfo+"\n");
         out.write(numOrder+"\n");
         for(int i = 0; i< numOrder; i++){
            out.write(historyOrder[i].getOrderID()+"\n");
         }
         out.close();
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
   
   /*
   boolean changeContact()
   int oldInfo: the old contact info
   int newInfo: the new contact info
   return true: indicate if the changing is successful
   description: it changes the contact info to newInfo if the oldInfo input matches the contactInfo
   */
   public boolean changeContact(int oldInfo, int newInfo){
      if (oldInfo == contactInfo){
         contactInfo = newInfo;
         return true;
      }
      return false;
   }
   /*
   void printHistoryOrder()
   description: run printHistoryOrder(1);
   */
   public void printHIstoryOrder(){
      printHistoryOrder(1);
   }
   
   /*
   void printHistoryOrder()
   int page: the page of the history order
   description: print all the historyOrder
   */
   public void printHistoryOrder(int page){
      //calculate total page
      int totalPage = numOrder/10;
      if(numOrder%10 > 0){
         totalPage++;
      }
      if(page <= totalPage&& page >0){
         System.out.println("Order history of Customer " + lastName + " (Customer ID: " + customerID + ")(Page: " +page+ "/"+totalPage+"):");
         for(int i = 10*(page-1);i < page*10 && historyOrder[i] != null; i++){
            System.out.println("" + historyOrder[i]);
         }
      }
      else{
         System.out.println("Invalid page number");
      }
   }
   
   /*
   boolean newOrder()
   Order order: the order that is just been registered
   return boolean: indicate if the order has been inserted
   */
   public boolean newOrder(Order order){
      if (order != null && numOrder < maxHistoryOrder){
         historyOrder[numOrder] = order;
         numOrder ++;
         return true;
      }
      return false;
   }
   
   //compareTo
   public int compareToCustomerID(Customer other){
      return this.customerID - other.customerID;
   }
   public int compareToFirstName(Customer other){
      return this.firstName.compareToIgnoreCase(other.firstName);
   }
   public int compareToLastName(Customer other){
      return this.lastName.compareToIgnoreCase(other.lastName);
   }
}