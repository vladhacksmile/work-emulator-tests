package task3.model.companies;

import lombok.Getter;
import lombok.Setter;
import task3.exceptions.IncorrectValue;
import task3.exceptions.NotEnoughCoins;
import task3.model.entity.Company;
import task3.model.entity.EditorialBuilding;
import task3.model.persons.Owner;

import java.util.Objects;

@Getter
@Setter
public class Newspaper extends Company {
    private static int countofNewspapers;
    final private int id;
    private String name;
    final private Owner owner;
    private int newspapercount;
    private int newspaperprice;
    private final EditorialBuilding building = new EditorialBuilding("Редакция газеты");

    public Newspaper(String name, Owner owner) {
        super(name, owner);
        countofNewspapers++;
        id = countofNewspapers;
        this.name = name;
        this.owner = owner;
        owner.setJob(this); // Устанавливаем рабочее место владельцу
    }

    public int start(int count) throws NotEnoughCoins, IncorrectValue {
        if(count < 0) throw new IncorrectValue("Количество не может быть отрицательным!");
        int calcCosts = newspaperprice / 2;
        try {
            for (int i = 0; i < count; i++) {
                if (getBalance() >= calcCosts) {
                    setBalance(getBalance() - calcCosts); // Будем брать 50% на стоимость бумаги, чернил и прочего
                    this.newspapercount += count;
                } else {
//                throw new NotEnoughCoins("невозможно напечатать газету из-за отстствия монет на бумагу и чернила");
                    System.err.println("Невозможно напечатать газету из-за отстствия монет на бумагу и чернила");
                    return i;
                }
            }
        } catch(NotEnoughCoins e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public int sell(int count) throws IncorrectValue {
        if(count < 0) throw new IncorrectValue("Количество не может быть отрицательным!");
        if(getNewspapercount() > 0 && count <= getNewspapercount()) {
            int profit = count * getNewspaperprice();
            newspapercount -= count;
            addCash(profit);
            System.out.println("Продано " + count + " газет");
            System.out.println("Баланс редакции: " + getBalance() + ", количество оставшихся газет: " + getNewspapercount());
            return count;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        if (obj == this)
            return true;
        Newspaper n = (Newspaper) obj;
        return Objects.equals(id, n.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return building.getName() + " #" + getId() + " " + getName() + " владельцем которой является " + owner.getName() + ", баланс " + getBalance() + " монет," + " цена газеты " + getNewspaperprice() + " монет, количество не проданных газет " + getNewspapercount();
    }
}
