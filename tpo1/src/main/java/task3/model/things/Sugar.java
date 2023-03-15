package task3.model.things;

import lombok.Getter;

@Getter
public class Sugar extends Thing {
    private int mass;
    public Sugar(String name, int mass){
        super(name);
        this.mass = mass;
    }
}
