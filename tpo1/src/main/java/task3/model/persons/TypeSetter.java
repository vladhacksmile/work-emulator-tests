package task3.model.persons;

import task3.enums.Location;
import task3.enums.Position;

public class TypeSetter extends Worker {
    public TypeSetter(String name, int money, Location location) {
        super(name, money, location, Position.TYPESETTER);
    }
}
