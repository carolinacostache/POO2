package main.java.lab4_ex1;

public abstract class Report implements Printable {
    protected StringBuilder builder;

    public Report() {
        builder = new StringBuilder();
    }

    public abstract void generate(Object[] args);

    @Override
    public void print() {
        System.out.println(builder.toString());
    }
}
