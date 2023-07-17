/*
2023/06/09
Brian Cai
*/
class Chicken extends Animal {

  public Chicken() {
    // chickens get 5 vaccines in thier life time !!
    super(0, 0.5, new String[5]);
  }

  public Chicken(int age, double weight, String[] vaccines) {
    super(age, weight, vaccines);
  }

  public String toString() {
    return super.toString();
  }
  public String toStringForWriter() {
    String tempString = age + "\n" + weight + "\n";
    for (int i = 0; vaccines[i] != null && i < vaccines.length;) {
      tempString = tempString + "\n" + vaccines[i];
    }
    return tempString;
  }

}