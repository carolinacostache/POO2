package lab3_ex2;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape {
    protected String color;

    public Shape(String color){
        this.color = color;
    }

    public abstract double getArea();
    public String getType;

    public String getColor() {
        return color;
    }

    public static void shapeCount(Shape[] shapes){
        ///trebuie facuta cu counter in loc de map

        Map<String, Integer> shapeCount = new HashMap<>();
        for(Shape shape: shapes){
            shapeCount.put(shape.getColor(), shapeCount.getOrDefault(shape.getColor(), 0) + 1);
        }

        System.out.println("Shape count:");
        for(Map.Entry<String, Integer> entry: shapeCount.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void largestShape(Shape[] shapes){
        Shape largest = shapes[0];
        for(Shape shape : shapes){
            if(shape.getArea() > largest.getArea()){
                largest = shape;
            }
        }
        System.out.println("Cea mai mare este: " + largest.getClass().getSimpleName());
    }

    public static void colorShape(Shape[] shapes, String searchColor){
        boolean found = false;
        for(Shape shape: shapes){
            if(shape.getColor().equals(searchColor)){
                System.out.println("Figurile sunt:" +shape.getClass().getSimpleName() + "de culoatre" + shape.getColor());
                found = true;
            }
        }

    }


}
