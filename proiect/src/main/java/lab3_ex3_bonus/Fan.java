package main.java.lab3_ex3_bonus;

public class Fan extends Appliance{
    public Fan(int power, int priority) {
        super(power, priority);
    }

    @Override
    public String getType(){
        return "Fan";
    }
}
