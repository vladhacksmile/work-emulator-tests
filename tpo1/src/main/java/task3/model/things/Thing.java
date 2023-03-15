package task3.model.things;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Thing {

    private String name;

    public Thing(String name){
        this.name = name;
    }
}