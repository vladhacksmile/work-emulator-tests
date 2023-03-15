package task3.model.companies;

import task3.enums.Color;
import task3.exceptions.IncorrectValue;
import task3.exceptions.NotEnoughCoins;
import task3.interfaces.IFabric;
import task3.model.entity.Company;
import task3.model.persons.Owner;
import task3.model.things.Cloth;
import task3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClothFabric extends Company implements IFabric {
    private final List<Cloth> clothes = new ArrayList<>();
    private int clothPrice;

    public ClothFabric(String name, Owner owner, int clothPrice) {
        super(name, owner);
        this.clothPrice = clothPrice;
    }

    public int start(int task) throws IncorrectValue {
        if(task < 0) throw new IncorrectValue("Task не может быть отрицательным!");
        int count = 0;
        System.out.println("Фабрика начала производство одежды");
        for(int i = 0; i < task; i++) {
            try {
                if (getBalance() >= 5) {
                    setBalance(getBalance() - 5);
                    Cloth t = new Cloth("Худи \"Spruts\"") {
                        @Override
                        public Color getColor() {
                            int id = Utils.getRandom(3);
                            if (id == 0) {
                                return Color.BLACK;
                            } else if (id == 1) {
                                return Color.WHITE;
                            } else {
                                return Color.ORANGE;
                            }
                        }
                    };
                    clothes.add(t);
                    count++;
                } else {
//                    throw new NotEnoughCoins("Для производства толстовок нужны деньги");
                    System.err.println("Для производства толстовок нужны деньги");
                    return i;
                }
            } catch (NotEnoughCoins e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Фабрика одежды произвела " + count + " толстовок");
        return count;
    }

    public int sell(Newspaper newspaper, int inCount) throws IncorrectValue {
        if(newspaper == null) throw new NullPointerException();
        if(inCount < 0) throw new IncorrectValue("Количество не может быть отрицательным!");
        if (getOwner().getMoney() >= inCount * clothPrice) {
            int count = 0;
            try {
                count = ads(getOwner(), this, newspaper, inCount, inCount);
            } catch (IncorrectValue e) {
                return 0;
            }
            int sell = 0;
            for (int i = 0; i < count; i++) {
                try {
                    System.out.println("Было продано " + clothes.get((clothes.size() - i - 1)).getName() + " цвета " + clothes.get(clothes.size() - i - 1).getColor());
                    clothes.remove(clothes.size() - i - 1);
                    addCash(clothPrice);
                    sell++;
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Фабрика по производству худи не может удовлетворить спрос покупателей! Производство продолжается!");
                    try {
                        start(inCount);
                    } catch (IncorrectValue ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    i--;
                }
            }
            System.out.println("Продано " + count + " худи (" + sell + ")");
            System.out.println("Баланс компании: " + getBalance() + ", количество оставшихся худи: " + (clothes.size() - 1));
            return count;
        }
        System.err.println("Владельцу нужны средства на рекламу!");
        return 0;
    }
}