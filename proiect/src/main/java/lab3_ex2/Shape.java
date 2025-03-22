package main.java.lab3_ex2;


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
        int counterTriunghi = 0;
        int counterDreptunghi = 0;
        int counterPatrat = 0;
        int totalShapes = shapes.length;

        for(Shape shape : shapes){
            if ( shape instanceof Triunghi){
                counterTriunghi++;
            } else if ( shape instanceof Dreptunghi){
                counterDreptunghi++;
            } else if ( shape instanceof Patrat){
                counterPatrat++;
            }
        }

        System.out.println(" Sunt in total " + totalShapes + " figuri dintre care:");
        System.out.println("-Triunghiuri: " + counterTriunghi);
        System.out.println("-Dreptunghiuri: " + counterDreptunghi);
        System.out.println("-Patrate: " + counterPatrat);




    }

    public static void largestShape(Shape[] shapes){
        Shape largest = shapes[0];
        for(Shape shape : shapes){
            if(shape.getArea() > largest.getArea()){
                largest = shape;
            }
        }
        System.out.println("Cea mai mare figura este " + largest.getClass().getSimpleName() + " si are aria de " + largest.getArea() + " m^2.");
    }

    public static void colorShape(Shape[] shapes, String searchColor){
        boolean found = false;
        System.out.println("Figurile de culoarea " + searchColor + " sunt:");
        for(Shape shape: shapes){
            if(shape.getColor().equals(searchColor)){
                System.out.println("-" + shape.getClass().getSimpleName());
                found = true;
            }
        }
        if(!found) {
            System.out.println(" Nu exista!");
        }

    }


}
