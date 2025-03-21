package lab3_ex2;

public class Triunghi extends Shape {
    private int latime;
    private int inaltime;

    public Triunghi(String color, int latime, int inaltime) {
        super(color);
        this.latime = latime;
        this.inaltime = inaltime;
    }

    @Override
    public double getArea() {
        return latime * inaltime;
    }
}