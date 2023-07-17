/*
2023/06/09
Brian Cai
*/
class Turkey extends Animal {

  public Turkey(int age, double weight, String[] vaccines) {
    super(age, weight, vaccines);
  }

  public Turkey() {
    super(0, 1, new String[5]);
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