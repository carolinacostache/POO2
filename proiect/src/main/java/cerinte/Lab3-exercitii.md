## Rezolvați exercițiile într-un pachet individual pentru fiecare (main.java.src.lab3.ex1, ../../ex2, etc.)

## I. Animal Sounds
- create an interface Animal with methods makeSound() and getName()
- create at least 3 classes of type Animal with their own values for makeSound() and getName()
- in main() (application should compile):
    - create an Animal array and populate it with at least one instance of each class
    - go trough the array by printing the name and sounds of each animal in this format:
      “{index in the array} . {type} ( {name} ): {sound}” (e.g. “1. Dog (Rex): Woof!”)

## II. Shapes and Area
- create an abstract class Shape with the following:
- methods getArea() and getType(); superclasses should be forced to override these
- an attribute ‘color’
- method to count how many shapes of each type are in a provided array
- method to find and print the largest shape in an array
- method to search for a shape by color in an array
- create at least 3 classes of type Shape (calculate the area or hard-code it in an attribute)
- in main() (application should compile):
    - create a Shape array and populate it with at least one instance of each class
    - execute a call to each of the created methods

## III. Part 1
Create a Smart Home System simulation where various appliances (Fan, Light, AC, Heater, etc.) can be controlled via a central manager.
- use an interface (e.g. Switchable) to turn on, turn off and check if an appliance is on
- use an abstract class (e.g. Appliance) with at least 2 fields (power, status) and an abstract method to return the type of the appliance
- the abstract class should contain the methods in the interface and a toggle method to turn the appliance ON or OFF
- where applicable, methods should print a specific message (e.g. “Fan is now ON.”)
- create a SmartHomeController (you decide if you want a wall-mounted, single remote or multiple remotes controller - non-instantiable vs singleton vs multiple instances) capable of:
    - turning on all appliances
    - turning off all appliances
    - count all ON devices
    - total power consumption (for ON devices)
    - print a beautified status report for all appliances


## III. Part 2
- application should compile and run
- create an array of Appliance with at least 5 appliances
- simulate the following:
    - turn everything on
    - turn everything off
    - count all ON devices
    - run a power consumption report
    - print a status report

## Bonus:
Update the application with the following capabilities:
- schedule night mode (appliances like light, fan should be off and heater, air conditioner, etc. should be on)
- an energy optimization mode – if the power consumption exceeds a given value, turn off the least important appliances (light, fan, etc.)
    - you can implement this by creating an attribute (e.g. priority) based on which you can turn off appliances until the power consumption is lower than desired