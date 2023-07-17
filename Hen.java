/*
2023/06/09
Brian Cai
*/
class Hen extends Chicken {
  private int eggsLaid;

  public Hen(int age, double weight, String[] vaccines) {
    super(age, weight, vaccines);
    this.eggsLaid = 0;
  }

  public Hen() {
    super();
    this.eggsLaid = 0;
  }

}