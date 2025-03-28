package main.java.lab3_ex3_bonus;

public abstract class Appliance implements Switchable {
    private int power;
    private boolean status;
    private int priority;

    public Appliance(int power, int priority) {
        this.power = power;
        this.priority = priority;
        this.status = false;
    }

    public abstract String getType();

    @Override
    public void switchOn(){
        if(!status){
            status = true;
            System.out.println("THE " + getType() + " IS NOW ON!");

        } else {
            System.out.println("THE " + getType()+ " IS ALREADY ON!");
        }
    }

    @Override
    public void switchOff(){
        if(status){
            status = false;
            System.out.println("THE " + getType() + " IS NOW OFF!");

        } else {
            System.out.println("THE " + getType() + " IS ALREADY OFF!");

        }
    }

    @Override
    public boolean checkStatus() {
        return status;
    }

    public int getPower() {
        return power;
    }

    public int getPriority() {
        return priority;
    }

    public void toggle(){
        if(status){
            switchOff();
        } else {
            switchOn();
        }
    }
}
