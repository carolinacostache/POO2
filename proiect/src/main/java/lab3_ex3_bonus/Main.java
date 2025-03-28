package main.java.lab3_ex3_bonus;

public class Main {
    public static void main(String[] args) {
        SmartHomeController bedroom = new SmartHomeController(" BEDROOM " );
        SmartHomeController kitchen = new SmartHomeController(" KITCHEN" );

        bedroom.addAppliance(new Heater(100,1));
        bedroom.addAppliance(new TV(200,2));
        bedroom.addAppliance(new Fan(50, 3));
        bedroom.addAppliance(new Lamp(20,3));
        bedroom.addAppliance(new Speaker(70,1));

        for( Appliance appliance : bedroom.getAppliances()) {
            if (appliance.getType().equals("TV")) {
                appliance.switchOn();
            }
            if (appliance.getType().equals("Fan")) {
                appliance.switchOn();
            }

        }

        bedroom.totalPowerConsumption();
        bedroom.powerStatusReport();
        bedroom.powerOptimization(100);

        bedroom.switchOnAll();
        bedroom.switchOffAll();
        bedroom.nightMode();



    }
}
