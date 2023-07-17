/*
 * 2023/06/09
 * Brian Cai
 */

class Cattle extends Cow {
  private int daysPregnant;
  private double milk;

  public Cattle(int age, double weight, String[] vaccines, int daysPregnant) {
    super(age, weight, vaccines);
    this.daysPregnant = daysPregnant;
    this.milk = 0;
  }

  public Cattle() {
    super();
    this.daysPregnant = 0;
    this.milk = 0;
  }

  public String toString() {
    return super.toString() + "Days Pregnant: " + daysPregnant + "\n" + "Milk: " + milk;
  }

  // This method is for when it is being printed on a file ;)
  public String toStringForWriter() {
    return super.toStringForWriter() + "\n" + daysPregnant + "\n" + milk;
  }

  public int getDaysPregnant() {
    return daysPregnant;
  }

  public void setDaysPregnant(int daysPregnant) {
    this.daysPregnant = daysPregnant;
  }

  public double getMilk() {
    return milk;
  }

  public void setMilk(double milk) {
    this.milk = milk;
  }

  public boolean isPregnant() {
    if (daysPregnant > 0) {
      return true;
    }
    return false;
  }

}