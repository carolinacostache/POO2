package main.java.lab3_ex3_bonus;

public class Speaker extends Appliance{
    public Speaker(int power, int priority) {
        super(power, priority);
    }

    @Override
    public String getType(){
        return "Speaker";
    }
}
