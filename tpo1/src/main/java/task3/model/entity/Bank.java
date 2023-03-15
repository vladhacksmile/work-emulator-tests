package task3.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Bank {
    private String name;
    private int id;
    private static int countBanks;
    private int cash;

    public Bank(String name, int cash) {
        countBanks++;
        id = countBanks;
        this.name = name;
        this.cash = cash;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != getClass())
            return false;
        if (obj == this)
            return true;
        Bank b = (Bank) obj;
        return Objects.equals(id, b.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getName();
    }
}
