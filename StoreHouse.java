import java.util.*;
import java.io.*;
/*
Class name: StoreHouse
Benedict Waung
Fields:
double maxWheatCapacity: the max capacity for wheat
double maxPotatoCapacity: the max capacity for potato
double maxCornCapacity: the max capacity for corn
double wheatStored: the wheat that was stored
double potatoStored: the potato that was stored
double cornStored: the corn that was stored
*/
public class StoreHouse{
   //field
   private double maxWheatCapacity;
   private double maxPotatoCapacity;
   private double maxCornCapacity;
   private double wheatStored;
   private double potatoStored;
   private double cornStored;
   
   //constructor
   public StoreHouse(double mwc, double mpc, double mcc){
      maxWheatCapacity = mwc;
      maxPotatoCapacity = mpc;
      maxCornCapacity = mcc;
   }
   
   //toString
   public String toString(){
      return "Wheat stored: " + wheatStored + " kg(max: " + maxWheatCapacity + 
             " kg)\nPotato stored: " + potatoStored + " kg(max: " + maxPotatoCapacity +
             " kg)\nCorn stored: " + cornStored + "kg(max: " + maxCornCapacity + " kg)\n";
   }
   
   //other methods
   /*
   boolean storeWheat()
   double num: the amount of wheat to be stored
   return boolean: true if limit exceed, other false
   description: store the wheat into the storage
   */
   public boolean storeWheat(double num){
      if(maxWheatCapacity >= wheatStored+num){
         wheatStored += num;
         return true;
      }
      return false;
   }
   
   /*
   boolean storePotato()
   double num: the amount of potato to be stored
   return boolean: true if limit exceed, other false
   description: store the potato into the storage
   */
   public boolean storePotato(double num){
      if(maxPotatoCapacity >= potatoStored+num){
         potatoStored += num;
         return true;
      }
      return false;
   }
   
   /*
   boolean storeCorn()
   double num: the amount of corn to be stored
   return boolean: true if limit exceed, other false
   description: store the corn into the storage
   */
   public boolean storeCorn(double num){
      if(maxCornCapacity >= cornStored+num){
         cornStored += num;
         return true;
      }
      return false;
   }
   
   /*
   boolean pullOutWheat()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out wheat from the storage
   */
   public boolean pullOutWheat(double num){
      if(wheatStored >= num){
         wheatStored -= num;
         return true;
      }
      return false;
   }
   
   /*
   boolean pullOutPotato()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out potato from the storage
   */
   public boolean pullOutPotato(double num){
      if(potatoStored >= num){
         potatoStored -= num;
         return true;
      }
      return false;
   }
   
   /*
   boolean pullOutCorn()
   double num: the amount of wheat to be taken
   return boolean: true if sufficient amount, otherwise false
   description: pull out corn from the storage
   */
   public boolean pullOutCorn(double num){
      if(cornStored >= num){
         cornStored -= num;
         return true;
      }
      return false;
   }
   
   //saving
   /*
   boolean saveStoreHouse
   return boolean: indicate if the saving is successful
   description: save the info in storehouse to a file
   */
   public boolean saveStoreHouse(){
      try{
         BufferedWriter out = new BufferedWriter(new FileWriter("StoreHouse.txt"));
         out.write(maxWheatCapacity+"\n");
         out.write(maxPotatoCapacity+"\n");
         out.write(maxCornCapacity+"\n");
         out.write(wheatStored+"\n");
         out.write(potatoStored+"\n");
         out.write(cornStored+"\n");
         out.close();
         return true;
      }
      catch(IOException iox){
         System.out.println("Problem accessing file");
      }
      return false;
   }
}