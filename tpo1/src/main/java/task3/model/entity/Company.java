package task3.model.entity;

import lombok.Getter;
import lombok.Setter;
import task3.enums.Color;
import task3.exceptions.IncorrectValue;
import task3.exceptions.NotEnoughCoins;
import task3.interfaces.IFabric;
import task3.model.companies.ClothFabric;
import task3.model.companies.Newspaper;
import task3.model.companies.SugarFabric;
import task3.model.persons.Owner;
import task3.model.persons.Worker;
import task3.model.things.Cloth;
import task3.model.things.Sugar;
import task3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Company {
    private String name;
    private Owner owner;
    private int balance;


    public Company(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public void addBalanceByOwner(int balance) throws IncorrectValue {
        if (balance < 0) {
            throw new IncorrectValue("баланс компании " + getName());
        }
        owner.addMoney(-balance);
        this.balance += balance;
    }

    public void grab() {
        owner.addMoney(balance);
        System.out.println(owner.getName() + " забрал " + balance + " монет из бюджета " + getName());
        balance = 0;
    }

    public abstract int start(int count) throws IncorrectValue;

//    public int advertising(int budget, Newspaper newspaper) {
//        int ads = ads(owner, this, newspaper, budget, budget); // Рассчитываем рандомно кол-во людей, которые захотят купить товар
////        Utils.getRandom(1, (budget / 2));
//        return ads;
//    }

    public void addCash(int cash) {
        System.out.println("В бюджет компании добавлено " + cash + " монет");
        balance += cash;
    }

    public static int ads(Worker p, Company company, Newspaper newspaper, int sum, int count) throws NullPointerException, IncorrectValue {
        if(p == null || company == null || newspaper == null) throw new NullPointerException();
        if(sum < 0 || count < 0) throw new IncorrectValue(" сумма на рекламу и количество газет!");

        if (p.getMoney() >= sum) {
            System.out.println(p.getName() + " рекламирует компанию " + company.getName());
            p.addMoney(-sum);
            newspaper.addCash(sum);
            return count;
        } else {
            System.out.println("Недостаточно денег на рекламу!");
            return 0;
        }
    }
}