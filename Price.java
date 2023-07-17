/*
Class name: Price
Benedict Waung
fields:
double wheatPrice: wheat price per kg
double potatoPrice: potato price per kg
double cornPrice: corn price per kg
double cowPrice: cow price per kg
double chickenPrice: chicken price per kg
double turkeyPrice: turkey price per kg
double eggPrice: egg price per 1 egg
double milkPrice milk price per 1 L
*/
public class Price{
   //fields
   private double wheatPrice;
   private double potatoPrice;
   private double cornPrice;
   private double cowPrice;
   private double chickenPrice;
   private double turkeyPrice;
   private double eggPrice;
   private double milkPrice;
   
   //constructor
   public Price(double wp, double pp, double cornp, double cowp, double chickenp, double tp, double ep, double mp){
      wheatPrice = wp;
      potatoPrice = pp;
      cornPrice = cornp;
      cowPrice = cowp;
      chickenPrice = chickenp;
      turkeyPrice = tp;
      eggPrice = ep;
      milkPrice = mp;
   }
   
   //accessor
   public double getWheatPrice(){
      return wheatPrice;
   }
   public double getPotatoPrice(){
      return potatoPrice;
   }
   public double getCornPrice(){
      return cornPrice;
   }
   public double getCowPrice(){
      return cowPrice;
   }
   public double getChickenPrice(){
      return chickenPrice;
   }
   public double getTurkeyPrice(){
      return turkeyPrice;
   }
   public double getEggPrice(){
      return eggPrice;
   }
   public double getMilkPrice(){
      return milkPrice;
   }
   //mutator
   public boolean setWheatPrice(double num){
      if(num > 0){
         wheatPrice = num;
         return true;
      }
      return false;
   }
   public boolean setPotatoPrice(double num){
      if(num > 0){
         potatoPrice = num;
         return true;
      }
      return false;
   }
   public boolean setCornPrice(double num){
      if(num > 0){
         cornPrice = num;
         return true;
      }
      return false;
   }
   public boolean setCowPrice(double num){
      if(num > 0){
         cowPrice = num;
         return true;
      }
      return false;
   }
   public boolean setChickenPrice(double num){
      if(num > 0){
         chickenPrice = num;
         return true;
      }
      return false;
   }
   public boolean setTurkeyPrice(double num){
      if(num > 0){
         turkeyPrice = num;
         return true;
      }
      return false;
   }
   public boolean setEggPrice(double num){
      if(num > 0){
         eggPrice = num;
         return true;
      }
      return false;
   }
   public boolean setMilkPrice(double num){
      if(num > 0){
         milkPrice = num;
         return true;
      }
      return false;
   }
   
   //toString
   public String toString(){
      return "Wheat price per kg: $" + wheatPrice +
             "\nPotato price per kg: $" + potatoPrice +
             "\nCorn price per kg: $" + cornPrice +
             "\nCow price per kg: $" + cowPrice +
             "\nChicken price per kg: $" + chickenPrice +
             "\nTurkey price per kg: $" + turkeyPrice +
             "\nEgg price per 1 egg: $" + eggPrice +
             "\nMilk price per L: $" + milkPrice;
   }
  
}