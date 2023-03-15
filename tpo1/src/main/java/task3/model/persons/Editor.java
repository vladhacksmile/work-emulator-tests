package task3.model.persons;

import task3.enums.Location;
import task3.enums.Position;

public class Editor extends Worker {
    public Editor(String name,  int money, Location location){
        super(name, money, location, Position.EDITOR);
    }
}
