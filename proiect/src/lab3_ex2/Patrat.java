package lab3_ex2;

public class Patrat extends Shape{
    private int latime;

    public Patrat(String color, int latime) {
        super(color);
        this.latime = latime;
    }

    @Override
    public double getArea(){
        return latime * latime;
    }


}
