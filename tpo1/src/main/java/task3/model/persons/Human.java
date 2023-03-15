package task3.model.persons;

import task3.enums.Location;
import task3.enums.Position;

public class Human extends Worker {
    public Human(String name, int money, Location location) {
        super(name, money, location, Position.UNKNOWN);
    }
}
