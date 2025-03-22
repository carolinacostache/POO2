package main.java.lab3_ex2;

public class Dreptunghi extends Shape{
    private int latime;
    private int lungime;

    public Dreptunghi(String color, int latime, int lungime) {
        super(color);
        this.latime = latime;
        this.lungime = lungime;
    }

    @Override
    public double getArea(){
        return latime * lungime;
    }

}
