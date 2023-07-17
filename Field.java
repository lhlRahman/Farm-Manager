/**
 * Class Name: Field
 * Author: Habib Rahman
 * Represents a field with soil and irrigation information.
 * 
 * Fields:
 * private double pHsoil: The pH level of the soil in the field
 * private double irrigation: The irrigation level of the field
 * private static double BASE_PH: The base pH level for the field
 * 
 * Methods:
 * Field(): Default constructor for the Field class, initializes pHsoil to the base pH level and irrigation to 0
 * Field(double pHsoil, double irrigation): Constructor for the Field class with parameters
 * getPHSoil(): Getter for the pHsoil field
 * getIrrigation(): Getter for the irrigation field
 * setPHsoil(double pHsoil): Setter for the pHsoil field
 * setIrrigation(double irrigation): Setter for the irrigation field
 * decay(): Decreases the pH level of the soil
 */

public class Field {
   private double pHsoil;
   private double irrigation;
   private static double BASE_PH = 6.5;
   
   /**
    * Default constructor for the Field class.
    * Initializes pHsoil to the base pH level and irrigation to 0.
    */
   public Field() {
      pHsoil = BASE_PH;
      irrigation = 0;
   }
   
   /**
    * Constructor for the Field class with parameters.
    * @param pHsoil The pH level of the soil
    * @param irrigation The irrigation level of the field
    */
   public Field(double pHsoil, double irrigation) {
      this.pHsoil = pHsoil;
      this.irrigation = irrigation;
   }
   
   /**
    * Getter for the pHsoil field.
    * @return The pH level of the soil
    */
   public double getPHSoil() {
      return pHsoil;
   }
   
   /**
    * Getter for the irrigation field.
    * @return The irrigation level of the field
    */
   public double getIrrigation() {
      return irrigation;
   }
   
   /**
    * Setter for the pHsoil field.
    * @param pHsoil The pH level to set
    */
   public void setPHsoil(double pHsoil) {
      this.pHsoil = pHsoil;
   }

   /**
    * Setter for the irrigation field.
    * @param irrigation The irrigation level to set
    */
   public void setIrrigation(double irrigation) {
      this.irrigation = irrigation;
   }

   /**
    * Decreases the pH level of the soil.
    */
   public void decay() {
      pHsoil = pHsoil - 0.03;
   }
}