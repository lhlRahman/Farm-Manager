/*
Class name: Crop
Benedict Waung, Habib Rahman
Fields:
int timeGrown: the time grown of the crop
double growRate: the growRate of the crop
int fertilizedDay: the day of fertilizer staying
Field field: the field of the crop

Static constant:
int BASE_DAILY_GROW: the base daily grow of a crop;
int FERTILIZER_STAY: how many days will the fertilizer staying in the soil
*/  
  public abstract class Crop
{
  
    //field
  protected int timeGrown;
  
protected double growRate;
  
protected int fertilizedDay;
  
protected Field field;
  
 
    //constant
  private static final int BASE_DAILY_GROW = 10;
  
private static final int FERTILIZER_STAYING = 7;
  
 
    //constructor
    public Crop ()
  {
    
timeGrown = 0;
    
growRate = BASE_DAILY_GROW;
    
fertilizedDay = 0;
    
field = new Field ();
  
} 
    //Mutator 
  public void setTimeGrown (int timeGrown)
  {
    
this.timeGrown = timeGrown;
  
} 
 
public void setGrowRate (double growRate)
  {
    
this.growRate = growRate;
  
} 
 
public void setFertilizedDay (int fertilizedDay)
  {
    
this.fertilizedDay = fertilizedDay;
  
} 
 
protected void setField (Field field)
  {
    
this.field = field;
  
} 
 //Accessor 
 
 public int getTimeGrown() {
    return timeGrown;
}

public double getGrowRate() {
    return growRate;
}

public int getFertilizedDay() {
    return fertilizedDay;
}

public Field getField() {
    return field;
}

    //toString
    public String toString ()
  {
    
return ("Time Grown: " + timeGrown + "\nGrowth Rate: " + growRate +
	     "\nFertilized Day: " + fertilizedDay + "\n");
  
}
  
 
    //other method
    /*
       void fertilize()
       description: fertilizing the crop
     */ 
  public void fertilize ()
  {
    
fertilizedDay = FERTILIZER_STAYING;
  
} 
 
    /*
       boolean isFertilized()
       return boolean: true if fertilizer stay in the soil, otherwise false
       description: to determine if the fertilizer still working
     */ 
    public boolean isFertilized ()
  {
    
return fertilizedDay > 0;
  
}
  
 
    /*
       void nextDay()
       description: pass the day and grow the crops 
     */ 
  public void nextDay ()
  {
    
updateGrowRate ();
    
grow ();
    
if (isFertilized ())
      {
	
fertilizedDay--;
      
}
    
field.decay ();
  
}
  
 
    /*
       void updateGrowRate()
       description: update the grow rate according to their arability and fertilization
     */ 
  public void updateGrowRate ()
  {
    
growRate = BASE_DAILY_GROW;
    
if (!isArable ())
      {
	
growRate = growRate / 2;
      
}
    
if (isFertilized ())
      {
	
growRate = growRate * 2;
      
}
  
}
  
 
    //compareTo
  public int compareToTime (Crop other)
  {
    
return this.timeGrown - other.timeGrown;
  
}
  
 
    //abstract methods
  public abstract void grow ();
  
public abstract double harvest ();
  
public abstract boolean isMature ();
  
public abstract boolean isArable ();

}
