package task3.model.things;

import task3.enums.Color;

public class Cloth extends Thing {
    public Cloth(String name){
        super(name);
    }

    public Color getColor() {
        return Color.ORANGE;
    }
}
