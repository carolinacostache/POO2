package lab3_ex2;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Patrat("yellow", 4),
                new Dreptunghi("blue",5,3),
                new Triunghi("green",5,3)
        };

        Shape.colorShape(shapes, "blue");
        Shape.largestShape(shapes);
        Shape.shapeCount(shapes);
    }

}
