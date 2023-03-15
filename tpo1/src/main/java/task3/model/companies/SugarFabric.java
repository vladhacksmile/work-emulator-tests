package task3.model.companies;

import task3.exceptions.IncorrectValue;
import task3.exceptions.NotEnoughCoins;
import task3.interfaces.IFabric;
import task3.model.entity.Company;
import task3.model.persons.Owner;
import task3.model.things.Sugar;
import task3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SugarFabric extends Company implements IFabric {
    private List<Sugar> packets = new ArrayList<>();
    private int sugarPrice;

    public SugarFabric(String name, Owner owner, int sugarPrice) {
        super(name, owner);
        this.sugarPrice = sugarPrice;
    }

    public int start(int task) throws IncorrectValue {
        if(task < 0) throw new IncorrectValue("Task не может быть отрицательным!");
        int count = 0;
        System.out.println("Фабрика начала производство сахара");
        for(int i = 0; i < task; i++) {
            try {
                if (getBalance() >= 5) {
                    setBalance(getBalance() - 5);
                    Sugar t = new Sugar("Сахар \"Спрутовский\"", 1000);
                    packets.add(t);
                    count++;
                } else {
//                    throw new NotEnoughCoins("Для производства сахара нужны деньги");
                    System.err.println("Для производства сахара нужны деньги");
                    return i;
                }
            } catch (NotEnoughCoins e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Фабрика сахара произвела " + count + " пакетов сахара");
        return count;
    }

    public int sell(Newspaper newspaper, int inCount) throws IncorrectValue {
        if(newspaper == null) throw new NullPointerException();
        if(inCount < 0) throw new IncorrectValue("Количество не может быть отрицательным!");
        if(getOwner().getMoney() >= inCount * sugarPrice) {
            int count = 0;
            try {
                count = ads(getOwner(), this, newspaper, inCount, inCount);
            } catch (IncorrectValue e) {
                return 0;
            }
            for (int i = 0; i < count; i++) {
                try {
                    System.out.println("Было продано " + packets.get((packets.size() - i - 1)).getName() + " массой " + packets.get(packets.size() - i - 1).getMass());
                    packets.remove(packets.size() - i - 1);
                    addCash(sugarPrice);
                } catch (IndexOutOfBoundsException e){
                    System.err.println("Фабрика по производству сахара не может удовлетворить спрос покупателей! Производство продолжается!");
                    start(inCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    i--;
                }
            }
            System.out.println("Продано " + count + " пакетов сахара");
            System.out.println("Баланс компании: " + getBalance() + ", количество оставшихся пакетов сахара: " + (packets.size() - 1));
            return count;
        }
        System.err.println("Владельцу нужны средства на рекламу!");
        return 0;
    }
}
