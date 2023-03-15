package task3.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditorialBuilding {

    private String name;

    public EditorialBuilding(String name) {
        this.name = name;
    }

}
