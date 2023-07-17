/*
 * 2023/06/09
 * Brian Cai
 Main management class for the livestock sector has save/load for all animals and has methods that tell transacton how much money feed costs

private Cow[] cows; array that stores cows
private Chicken[] chickens; array stores chickens
private Turkey[] turkeys; array stores turkeys
private int numTurkey; number of turkeys
private int numChicken; number of chickens
private int numCow; number of cows
private int totalEggs; total number of eggs stored individual eggs laid is not relvant and grouped into total eggs laid
private double totalMilk; total number of milk stored
private int chickenFeed; total number of chicken feed required
private int cowFeed; total number of cowfeed required
private int turkeyFeed; total number of turkey feed required
final private double CHICKENFEEDPRICE = 0.2;
final private double COWFEEDPRICE = 1.63;
final private double TURKEYFEEDPRICE = 0.15;
*/

//remmeber to make sort method go from lightest to heaviest

import java.io.*;

class Livestock {

  public void setCows(Cow[] cows) {
    this.cows = cows;
  }

  private Cow[] cows;
  private Chicken[] chickens;
  private Turkey[] turkeys;
  private int numTurkey;
  private int numChicken;
  private int numCow;
  private int totalEggs;
  private double totalMilk;
  private int chickenFeed;
  private int cowFeed;
  private int turkeyFeed;
  final private double CHICKENFEEDPRICE = 0.2;
  final private double COWFEEDPRICE = 1.63;
  final private double TURKEYFEEDPRICE = 0.15;

  public Livestock() {
  }

  public void storeEggs(int num) {
    totalEggs = totalEggs + num;
  }

  public void storeMilk(double num) {
    totalMilk = totalMilk + num;
  }

  public int getChickenFeed() {
    return chickenFeed;
  }

  public void setChickenFeed(int chickenFeed) {
    this.chickenFeed = chickenFeed;
  }

  public int getCowFeed() {
    return cowFeed;
  }

  public void setCowFeed(int cowFeed) {
    this.cowFeed = cowFeed;
  }

  public int getTurkeyFeed() {
    return turkeyFeed;
  }

  public void setTurkeyFeed(int turkeyFeed) {
    this.turkeyFeed = turkeyFeed;
  }

  public double getCHICKENFEEDPRICE() {
    return CHICKENFEEDPRICE;
  }

  public double getCOWFEEDPRICE() {
    return COWFEEDPRICE;
  }

  public double getTURKEYFEEDPRICE() {
    return TURKEYFEEDPRICE;
  }

  public int getTotalEggs() {
    return totalEggs;
  }

  public void setTotalEggs(int totalEggs) {
    this.totalEggs = totalEggs;
  }

  public double getTotalMilk() {
    return totalMilk;
  }

  public void setTotalMilk(double totalMilk) {
    this.totalMilk = totalMilk;
  }

  public Cow[] getCows() {
    return cows;
  }

  public double buyCowFeed(int amountFeed) {
    return cowFeed + amountFeed;
  }

  // returns and removed heaveist num amount of turkeys
  // same for every other animal
  public double pullOutCow(int num) {
    double totalWeight = 0;
    for (int i = numCow; i > num; i--) {
      totalWeight = totalWeight + cows[i].getWeight();
      cows[i] = null;
    }
    return totalWeight;
  }

  public Chicken[] getChickens() {
    return chickens;
  }

  public double pullOutChicken(int num) {
    double totalWeight = 0;
    for (int i = numChicken; i > num; i--) {
      totalWeight = totalWeight + chickens[i].getWeight();
      chickens[i] = null;
    }
    return totalWeight;
  }

  public Turkey[] getTurkeys() {
    return turkeys;
  }

  public double pullOutTurkey(int num) {
    double totalWeight = 0;
    for (int i = numTurkey; i > num; i--) {
      totalWeight = totalWeight + turkeys[i].getWeight();
      turkeys[i] = null;
    }
    return totalWeight;
  }

  public int getNumTurkey() {
    return numTurkey;
  }

  // checks which part of turkey has a nul object;
  public void updateNumTurkey() {
    for (int i = 0; i < turkeys.length; i++) {
      if (turkeys[i] == null) {
        this.numTurkey = i + 1;
      }
    }
  }

  public int getNumChicken() {
    return numChicken;
  }

  public void updateNumCow() {
    for (int i = 0; i < cows.length; i++) {
      if (cows[i] == null) {
        this.numCow = i + 1;
      }
    }
  }

  public int getNumCow() {
    return numCow;
  }

  public void updateNumChicken() {
    for (int i = 0; i < chickens.length; i++) {
      if (chickens[i] == null) {
        this.numChicken = i + 1;
      }
    }
  }

  // checks if required milk can be deducted from total milk then returns booleans
  // based on this
  public boolean pullOutMilk(double requiredMilk) {
    if (totalMilk > requiredMilk) {
      this.totalMilk = totalMilk - requiredMilk;
      return true;
    }
    return false;
  }

  // same with milk
  public boolean pullOutEggs(int requiredEggs) {
    if (totalEggs > requiredEggs) {
      this.totalEggs = totalEggs - requiredEggs;
      return true;
    }
    return false;
  }
  // Adds an animal to their respective array checks if array can fit any more,
  // returns false if cant fit anymore true if it can fit more, useful for the
  // interface

  public boolean addAnimal(Animal animal) {
    if (animal instanceof Cow && numCow != cows.length) {
      cows[numCow] = (Cow) animal;
      numCow++;
      return true;
    }
    if (animal instanceof Chicken && numChicken != chickens.length) {
      chickens[numChicken] = (Chicken) animal;
      numChicken++;
      return true;
    }

    if (animal instanceof Turkey && numTurkey != turkeys.length) {
      turkeys[numTurkey] = (Turkey) animal;
      numTurkey++;
      return true;
    }
    return false;
  }

  // sorts cow from lightest to heavist for pullOutCow()
  // fills in null objects with the object after and repeats until all holes
  // filled
  // utilizes bubble sort
  public void sortCow() {
    boolean sorted = false;
    Cow temp;

    for (int upperbound = numCow - 1; upperbound > 0 && !sorted; upperbound = upperbound - 1) {
      sorted = true;
      for (int i = 0; i < upperbound; i++) {
        if (cows[i] == null) {
          cows[i] = cows[i + 1];
        }
        if (cows[i].getWeight() > cows[i + 1].getWeight()) {
          sorted = false;
          temp = cows[i];
          cows[i] = cows[i + 1];
          cows[i] = temp;
        }
      }

    }
  }

  public void sortChicken() {
    boolean sorted = false;
    Chicken temp;

    for (int upperbound = numChicken - 1; upperbound > 0 && !sorted; upperbound = upperbound - 1) {
      for (int i = 0; i < upperbound; i++) {
        if (chickens[i] == null) {
          chickens[i] = chickens[i + 1];
        }
        if (chickens[i].getWeight() > chickens[i + 1].getWeight()) {
          sorted = false;
          temp = chickens[i];
          chickens[i] = chickens[i + 1];
          chickens[i] = temp;
        }
      }

    }
  }

  public void sortTurkey() {
    boolean sorted = false;
    Turkey temp;

    for (int upperbound = numTurkey - 1; upperbound > 0 && !sorted; upperbound = upperbound - 1) {
      for (int i = 0; i < upperbound; i++) {
        if (turkeys[i] == null) {
          turkeys[i] = turkeys[i + 1];
        }
        if (turkeys[i].getWeight() > turkeys[i + 1].getWeight()) {
          sorted = false;
          temp = turkeys[i];
          turkeys[i] = turkeys[i + 1];
          turkeys[i] = temp;
        }
      }

    }

  }

  public void setWeightTurkey(int num, double weight) {
    turkeys[num].setWeight(weight);
  }

  public void setWeightCow(int num, double weight) {
    cows[num].setWeight(weight);
  }

  public void setWeightChicken(int num, double weight) {
    chickens[num].setWeight(weight);
  }

  public boolean loadLivestock() {
    String tempVar;
    try {
      BufferedReader reader = new BufferedReader(new FileReader("liveStock.txt"));
      int cowsLength = Integer.parseInt(reader.readLine());
      int chickensLength = Integer.parseInt(reader.readLine());
      int turkeysLength = Integer.parseInt(reader.readLine());
      int totalEggs = Integer.parseInt(reader.readLine());
      double totalMilk = Double.parseDouble(reader.readLine());
      int chickenFeed = Integer.parseInt(reader.readLine());
      int cowFeed = Integer.parseInt(reader.readLine());
      int turkeyFeed = Integer.parseInt(reader.readLine());

      // setting arrays
      cows = new Cow[cowsLength];
      chickens = new Chicken[chickensLength];
      turkeys = new Turkey[turkeysLength];
      this.totalEggs = totalEggs;
      this.totalMilk = totalMilk;
      this.chickenFeed = chickenFeed;
      this.cowFeed = cowFeed;
      this.turkeyFeed = turkeyFeed;

      if (reader.readLine().equals("COW")) { // incase future updates contains other animals
        numCow = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numCow; i++) {
          // if cattle add days pregnant and milk
          tempVar = reader.readLine();
          if (tempVar.equals("cattle")) {
            cows[i] = new Cattle();
            cows[i].setAge(Integer.parseInt(reader.readLine()));
            cows[i].setWeight(Double.parseDouble(reader.readLine()));
            for (int numVaccine = 0; numVaccine < 7; numVaccine++) {
              cows[i].setVaccines(reader.readLine());
            }
            ((Cattle) cows[i]).setDaysPregnant(Integer.parseInt(reader.readLine()));
            ((Cattle) cows[i]).setMilk(Double.parseDouble(reader.readLine()));
          } else {
            cows[i] = new Cow();
            cows[i].setAge(Integer.parseInt(tempVar));
            cows[i].setWeight(Double.parseDouble(reader.readLine()));
            for (int numVaccine = 0; numVaccine < 7; numVaccine++) {
              cows[i].setVaccines(reader.readLine());
            }
          }

        }
      }

      if (reader.readLine().equals("CHICKEN")) {
        numChicken = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numChicken; i++) {
          tempVar = reader.readLine();
          if (tempVar.equals("hen")) {
            chickens[i] = new Hen();
            chickens[i].setAge(Integer.parseInt(reader.readLine()));
          } else {
            chickens[i] = new Chicken();
            chickens[i].setAge(Integer.parseInt(tempVar));

          }
          chickens[i].setWeight(Double.parseDouble(reader.readLine()));
          for (int numVaccine = 0; numVaccine < 5; numVaccine++) {
            chickens[i].setVaccines(reader.readLine());
          }
        }

      }
      if (reader.readLine().equals("TURKEY")) {
        numTurkey = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numTurkey; i++) {

          turkeys[i] = new Turkey();

          turkeys[i].setAge(Integer.parseInt(reader.readLine()));
          turkeys[i].setWeight(Integer.parseInt(reader.readLine()));
          for (int numVaccine = 0; numVaccine < 5; numVaccine++) {
            chickens[i].setVaccines(reader.readLine());
          }
        }
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Error File Not Found");
      return false;
    }
    return true;
  }

  // saves to "livestock.txt"
  // uses a method called .toStringForWriter() that formates everything for a
  // writer!!!
  public boolean saveLivestock() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("liveStock.txt"));
      writer.write(cows.length + "\n");
      writer.write(chickens.length + "\n");
      writer.write(turkeys.length + "\n");
      writer.write(totalEggs + "\n");
      writer.write(totalMilk + "\n");
      writer.write(chickenFeed + "\n");
      writer.write(cowFeed + "\n");
      writer.write(turkeyFeed + "\n");
      writer.write("COW" + "\n");
      writer.write(numCow + "\n");
      for (int i = 0; i < cows.length; i++) {
        if (cows[i] instanceof Cattle) {
          writer.write("cattle" + "\n");
        }
        writer.write(cows[i].toStringForWriter() + "\n");
      }
      writer.write("CHICKEN" + "\n");
      writer.write(numChicken + "\n");
      for (int i = 0; i < chickens.length; i++) {
        if (chickens[i] instanceof Hen) {
          writer.write("hen" + "\n");
        }
        writer.write(chickens[i].toStringForWriter() + "\n");
      }
      writer.write("TURKEY" + "\n");
      writer.write(numTurkey + "\n");
      for (int i = 0; i < turkeys.length; i++) {
        writer.write(turkeys[i].toStringForWriter() + "\n");
      }

    } catch (IOException e) {
      System.out.println("Error File Not Found");
      return false;
    }
    return true;
  }

}