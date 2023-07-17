import java.io.*;

/*
Class name: CropManager
Author: Habib Rahman
Fields:
int maxCrop: The max amount of crop
int fertilizer: amount of fertilizer
double fertilizerPrice: price per fertilizer
Crop[] crops: an array of crops
int cropCount: amount of crops

*/

public class CropManager {
   // Fields
   private int maxCrop;
   private int fertilizer;
   private double fertilizerPrice;
   private Crop[] crops;
   private int cropCount;

   // Constructor
   public CropManager(int maxCrop) {
      this.maxCrop = maxCrop;
      this.fertilizer = 0;
      this.fertilizerPrice = 0.0;
      this.crops = new Crop[maxCrop];
      this.cropCount = 0;
   }

   // Accessor methods 
   
   public int getMaxCrop() {
      return maxCrop;
   }

   public int getFertilizer() {
      return fertilizer;
   }

   public double getFertilizerPrice() {
      return fertilizerPrice;
   }

   public int getCropCount() {
      return cropCount;
   }

   // Mutator methods
   public void setMaxCrop(int maxCrop) {
      this.maxCrop = maxCrop;
   }

   public void setFertilizer(int fertilizer) {
      this.fertilizer = fertilizer;
   }

   public void setFertilizerPrice(double fertilizerPrice) {
      this.fertilizerPrice = fertilizerPrice;
   }

   public void setCropCount(int cropCount) {
      this.cropCount = cropCount;
   }

   // Method to load crop data from a file
   public boolean load(String filename) {
      try {
         BufferedReader in = new BufferedReader(new FileReader(filename));
         int numOfCrops = Integer.parseInt(in.readLine());
         for (int i = 0; i < numOfCrops; i++) {
            String cropType = in.readLine();
            if (cropType.equals("Potato")) {
               crops[i] = new Potato();
            } else if (cropType.equals("Corn")) {
               crops[i] = new Corn();
            } else if (cropType.equals("Wheat")) {
               crops[i] = new Wheat();
            }
            crops[i].setTimeGrown(Integer.parseInt(in.readLine()));
            crops[i].setGrowRate(Double.parseDouble(in.readLine()));
            crops[i].setFertilizedDay(Integer.parseInt(in.readLine()));
         }
         in.close();
         return true;
      } catch (IOException iox) {
         System.out.println("Problem accessing file");
      }
      return false;
   }

   // Method to save crop data to a file
   public boolean save(String filename) {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));
         out.write(cropCount + "\n");
         for (int i = 0; i < cropCount; i++) {
            out.write(crops[i].getClass().getSimpleName() + "\n");
            out.write(crops[i].getTimeGrown() + "\n");
            out.write(crops[i].getGrowRate() + "\n");
            out.write(crops[i].getFertilizedDay() + "\n");
         }
         out.close();
         return true;
      } catch (IOException iox) {
         System.out.println("Problem accessing file");
      }
      return false;
   }

   // Method to buy a certain amount of fertilizer
   public double buyFertilizer(int amount) {
       return amount * fertilizerPrice;
      }
   

   // Method to fertilize all crops
   public boolean fertilizeAllCrops() {
      if (fertilizer > 0){
         fertilizer += -1;
         for (int i = 0; i < crops.length; i++){
            crops[i].fertilize();
         }
         return true;
      }
      return false;
   }

   // Method to discard a crop at a given index
   // Return true if successful, false otherwise
   public boolean discard(int index) {
      if (index >= 0 && index < cropCount) {
         for (int i = index; i < cropCount - 1; i++) {
            crops[i] = crops[i + 1];
         }
         crops[cropCount - 1] = null;
         cropCount--;
         return true;
      }
      return false;
   }

   // Method to harvest all potatoes and return the total yield
   public double harvestPotato() {
      double totalYield = 0.0;
      for (int i = 0; i < cropCount; i++) {
         if (crops[i] instanceof Potato) {
            totalYield += ((Potato) crops[i]).harvest();
         }
      }
      return totalYield;
   }

   // Method to harvest all corn and return the total yield
   public double harvestCorn() {
      double totalYield = 0.0;
      for (int i = 0; i < cropCount; i++) {
         if (crops[i] instanceof Corn) {
            totalYield += ((Corn) crops[i]).harvest();
         }
      }
      return totalYield;
   }

   // Method to print all types of crops being managed
   public void printAllCropTypes() {
      for (int i = 0; i < cropCount; i++) {
         System.out.println(crops[i].toString());
      }
   }

   // Method to plant a certain number of potatoes
   public boolean plantPotato(int numCrops) {
      if (cropCount + numCrops <= maxCrop) {
         for (int i = 0; i < numCrops; i++) {
            crops[cropCount] = new Potato();
            cropCount++;
         }
         return true;
      }
      return false;
   }

   // Method to plant a certain number of corn
   public boolean plantCorn(int numCrops) {
      if (cropCount + numCrops <= maxCrop) {
         for (int i = 0; i < numCrops; i++) {
            crops[cropCount] = new Corn();
            cropCount++;
         }
         return true;
      }
      return false;
   }

   // Method to print the status of all crops
   public void printCropStatus() {
      for (int i = 0; i < cropCount; i++) {
         System.out.println(crops[i].toString());
      }
   }

   // Method to return the oldest potato
   public Potato oldestPotato() {
      Potato oldest = null;
      for (int i = 0; i < cropCount; i++) {
         if (crops[i] instanceof Potato) {
            if (oldest == null || crops[i].timeGrown > oldest.timeGrown) {
               oldest = (Potato) crops[i];
            }
         }
      }
      return oldest;
   }

   // Method to return the oldest corn
   public Corn oldestCorn() {
      Corn oldest = null;
      for (int i = 0; i < cropCount; i++) {
         if (crops[i] instanceof Corn) {
            if (oldest == null || crops[i].timeGrown > oldest.timeGrown) {
               oldest = (Corn) crops[i];
            }
         }
      }
      return oldest;
   }

   // Method to return the oldest crop
   public Crop oldestCrop() {
      Crop oldest = null;
      for (int i = 0; i < cropCount; i++) {
         if (oldest == null || crops[i].timeGrown > oldest.timeGrown) {
            oldest = crops[i];
         }
      }
      return oldest;
   }
}