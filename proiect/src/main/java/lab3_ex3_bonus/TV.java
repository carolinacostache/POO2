package main.java.lab3_ex3_bonus;

public class TV extends Appliance{
    public TV(int power, int priority) {
        super(power, priority);
    }

    @Override
    public String getType(){
        return "TV";
    }
}
