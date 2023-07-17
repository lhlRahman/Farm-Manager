/*
2023/06/09
Brian Cai
All animals 
*/
abstract class Animal {
  protected int age;
  protected double weight;
  protected String vaccines[];

  public Animal(int age, double weight, String[] vaccines) {
    this.age = age;
    this.weight = weight;
    this.vaccines = vaccines;
  }

  public Animal(int age, double weight) {
    this.age = age;
    this.weight = weight;
  }

  public Animal compareToAge(Animal other) {
    if (other != null && other.age > this.age) {
      return other;
    }
    return this;

  }

  public Animal compareToWeight(Animal other) {
    if (other != null && other.weight > this.weight) {
      return other;
    }
    return this;

  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String[] getVaccines() {
    return vaccines;
  }

  // sets first null value to vaccine;
  public void setVaccines(String vaccine) {
    boolean isSet = false;
    for (int i = 0; i < vaccines.length && isSet == false; i++) {
      if (vaccines[i] == null) {
        this.vaccines[i] = vaccine;
        isSet = true;
      }

    }
  }

  public String toString() {
    String tempString = "Age: " + age + "\n" + "Weight: " + weight + "\n" + "Vaccines:";
    for (int i = 0; vaccines[i] != null && i < vaccines.length;) {
      tempString = tempString + "\n" + vaccines[i];
    }
    return tempString;
  }

  // This method is for when it is being printed on a file ;)
  abstract public String toStringForWriter();

  public boolean iscfullVaccinated() {
    if (vaccines[vaccines.length - 1] == null) {
      return false;
    }
    return true;
  }
  
}