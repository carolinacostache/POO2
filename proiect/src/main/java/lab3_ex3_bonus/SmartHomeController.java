package main.java.lab3_ex3_bonus;

import java.util.ArrayList;
import java.util.List;


/// multiple instances
class SmartHomeController {
    private List<Appliance> appliances;
    private String name;

    public SmartHomeController( String name) {
        this.appliances = new ArrayList<>();
        this.name = name;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }
    public void switchOnAll(){
        System.out.println("~~~~~ALL APPLIANCES ARE TURNING ON!~~~~~");
        for(Appliance appliance : appliances){
            appliance.switchOn();
        }
        System.out.println("~~~~~ALL APPLIANCES ARE TURNED ON!~~~~~");
        System.out.println("-------------------------------------------------------");

    }

    public void switchOffAll(){
        System.out.println("~~~~~ALL APPLIANCES ARE TURNING OFF!~~~~~");
        System.out.println("-------------------------------------------------------");

        for(Appliance appliance : appliances){
            appliance.switchOff();
        }
        System.out.println("~~~~~ALL APPLIANCES ARE TURNED OFF!~~~~~");
        System.out.println("-------------------------------------------------------");

    }

    public int appliancesOnCount() {
        int count = 0;
        for(Appliance appliance : appliances){
            if(appliance.checkStatus()) {
                count++;
            }
        }
        return count;
    }

    public int totalPowerConsumption() {
        int totalPowerConsumption = 0;
        for(Appliance appliance : appliances){
            if(appliance.checkStatus()) {
                totalPowerConsumption += appliance.getPower();
            }
        }
        return totalPowerConsumption;
    }

    public void powerStatusReport() {
        System.out.println(" ~~~~~REPORT STATUS FOR ALL APPLIANCES IN THE HOME CONTROLLER~~~~~ ");
        for(Appliance appliance : appliances){
            String stats = appliance.checkStatus() ? "ON" : "OFF";
            System.out.println(appliance.getType() + " IS " + stats + " !");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("TOTAL ON DEVICES: " + appliancesOnCount());
        System.out.println("-------------------------------------------------------");
        System.out.println("POWER CONSUMPTION: " + totalPowerConsumption());
        System.out.println("-------------------------------------------------------");

    }

    public void powerOptimization( int powerThreshold) {
        System.out.println("~~~~~POWER SAVING MODE~~~~~");
        System.out.println("-------------------------------------------------------");

        int currentPower = totalPowerConsumption();


        if(currentPower>powerThreshold) {
            System.out.println("TOTAL POWER CONSUMPTION " + currentPower + " EXCEEDS LIMIT!");
            System.out.println("-------------------------------------------------------");
            appliances.sort((a1, a2) -> Integer.compare(a1.getPriority(),a2.getPriority()));
            for(Appliance appliance : appliances) {
                if(currentPower <= powerThreshold) break;
                if(appliance.checkStatus()) {
                    appliance.switchOff();
                    currentPower -= appliance.getPower();
                    System.out.println("NEW POWER CONSUMPTION " + currentPower);
                    System.out.println("-------------------------------------------------------");
                }

            }
        } else {
            System.out.println("TOTAL POWER CONSUMPTION: " + currentPower + " DOES NOT EXCEED LIMIT!");
            System.out.println("-------------------------------------------------------");
        }
    }

    public void nightMode(){
        System.out.println("~~~~~NIGHT MODE~~~~~");
        for (Appliance appliance : appliances) {
            if(appliance.getType().equals("Fan") || appliance.getType().equals("Lamp") || appliance.getType().equals("TV")) {
                appliance.switchOff();
            } else {
                appliance.switchOn();
            }
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("NIGHT MODE SUCCESSFULLY TURNED ON!");
    }
}
