/*
Class name: Corn (extends Crop)
Benedict Waung
Fields:
double yield: the yield of the crops

Static constant:
double MAXPH: the max pH soil value for the crop
double MINPH: the min pH soil value for the crop
double MAXIRRIGATION: the max irrigation for the crop
double MINIRRIGATION: the min irrigation for the crop
double GROWTHTIME: the min time for crop to be mature
double BASE_YIELD: the base yield when the crop just planted
*/
public class Corn extends Crop{
   //fields
   private double yield;
   
   //constant   
   private static final double MAXPH = 6.2;
   private static final double MINPH = 5.8;
   private static final double MAXIRRIGATION = 550.0; 
   private static final double MINIRRIGATION = 500.0;
   private static final double GROWTHTIME = 90;
   private static final double BASE_YIELD = 10 ;

   //constructor
   public Corn(){
      super();
      yield = BASE_YIELD;
   }

   //accessor
   public double getYield() {
      return yield;
   }
   //mutator
   public void setYield(double yield) {
      this.yield = yield;
   }
   
   //toString
   public String toString(){
      return "Crop type: Corn\nYield: " + yield + "kg\n" + super.toString();
   }
   
   //other method
   /*
   void grow();
   description: crop grows
   */
   public void grow(){
      timeGrown++;
      yield += growRate;
   }
   
   /*
   double harvest
   return double: the yield of the crops
   description: harvest the crops and reset the crop
   */
   public double harvest(){
      double result= 0;
      if(isMature()){
         result = yield;
         yield = BASE_YIELD;
         timeGrown = 0;
      }
      return result;
   }
   /*
   boolean isMature()
   return boolean: true if mature, otherwise false
   description: determine if the crop is mature
   */
   public boolean isMature(){
      return timeGrown >= GROWTHTIME;
   }
   
   /*
   boolean isArable()
   return boolean: true if it matches all the conditions, otherwise false
   description: determine if the field is arable for this crop
   */
   public boolean isArable(){
      return MAXPH >= field.getPHSoil() && field.getPHSoil() >= MINPH && MAXIRRIGATION >= field.getIrrigation() && field.getIrrigation() >= MINIRRIGATION;
   }
}

