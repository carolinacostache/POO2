package main.java.lab3_ex3_bonus;

public class Lamp extends Appliance{
    public Lamp(int power, int priority) {
        super(power, priority);

    }

    @Override
    public String getType(){
        return "Lamp";
    }
}
