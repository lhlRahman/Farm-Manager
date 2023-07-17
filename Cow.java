/*
2023/06/09
Brian Cai
fields:

*/
class Cow extends Animal {

  public Cow() {
    super(0, 31.7, new String[7]);// weight based of newborn calf cows and cattle get around 7 shots in thier life
  }

  public Cow(int age, double weight, String[] vaccines) {
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