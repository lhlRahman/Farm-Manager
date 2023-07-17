/*
Class name: Wheat (extends Crop)
Author: Habib Rahman
Fields:
double yield: the yield of the crops

Static constant:
double MAXPH: the max pH soil value for the crop
double MINPH: the min pH soil value for the crop
double MAXIRRIGATION: the max irrigation for the crop
double MINIRRIGATION: the min irrigation for the crop
double GROWTHTIME: the min time for crop to be mature
double BASE_YIELD: the base yield when the crop is just planted
*/
public class Wheat extends Crop {
   // Fields
   private double yield;
   
   // Constants
   private static final double MAXPH = 7.0;
   private static final double MINPH = 6.0;
   private static final double MAXIRRIGATION = 150.0;
   private static final double MINIRRIGATION = 90.0;
   private static final double GROWTHTIME = 150;
   private static final double BASE_YIELD = 10;

   // Constructor
   public Wheat() {
      super();
      yield = BASE_YIELD;
   }

   // Accessor
   public double getYield() {
      return yield;
   }
   
   // Mutator
   public void setYield(double yield) {
      this.yield = yield;
   }
   
   // toString
   public String toString() {
      return "Crop type: Wheat\nYield: " + yield + "kg\n" + super.toString();
   }
   
   // Other methods
   
   /*
    * void grow()
    * Description: Crop grows
    */
   public void grow() {
      timeGrown++;
      yield += growRate;
   }
   
   /*
    * double harvest()
    * Return: The yield of the crops
    * Description: Harvest the crops and reset the crop
    */
   public double harvest() {
      double result = 0;
      if (isMature()) {
         result = yield;
         yield = BASE_YIELD;
         timeGrown = 0;
      }
      return result;
   }
   
   /*
    * boolean isMature()
    * Return: True if mature, otherwise false
    * Description: Determine if the crop is mature
    */
   public boolean isMature() {
      return timeGrown >= GROWTHTIME;
   }
   
   /*
    * boolean isArable()
    * Return: True if it matches all the conditions, otherwise false
    * Description: Determine if the field is arable for this crop
    */
   public boolean isArable() {
      return MAXPH >= field.getPHSoil() && field.getPHSoil() >= MINPH && MAXIRRIGATION >= field.getIrrigation() && field.getIrrigation() >= MINIRRIGATION;
   }
}
