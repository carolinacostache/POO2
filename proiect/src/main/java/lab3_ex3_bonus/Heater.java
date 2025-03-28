package main.java.lab3_ex3_bonus;

public class Heater extends Appliance{
    public Heater(int power, int priority) {
        super(power, priority);
    }

    @Override
    public String getType(){
        return "Heater";
    }
}
