package task3.model.persons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.companies.Newspaper;
import task3.model.things.Telegram;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    TypeSetter worker;
    Owner owner;
    Newspaper newspaper;

    @BeforeEach
    public void initEach() {
        worker = new TypeSetter("Крабс", 100, Location.NEWSPAPER);
        owner = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        newspaper = new Newspaper("Газета", owner);
        try {
            newspaper.addBalanceByOwner(10000);
            newspaper.setNewspaperprice(10);
        } catch (IncorrectValue e) {
            throw new RuntimeException(e);
        }
        worker.setJob(newspaper);
    }

    @Test
    void payMoney() {
        worker.work(5);
        assertEquals("Спрутс выплатил Крабс 7 монет", owner.payMoney(worker));
    }

    @Test
    void payMoneyNotEnough() {
        owner.setMoney(5);
        worker.work(5);
        assertEquals("Денег недостаточно! Не могу выплатить!", owner.payMoney(worker));
    }

    @Test
    void payMoneyNoWorker() {
        worker.setJob(null);
        worker.work(5);
        assertEquals("Крабс не работает в моей компании!", owner.payMoney(worker));
    }

    @Test
    void payMoneyNullWorker() {
        assertThrows(NullPointerException.class, () -> owner.payMoney(null));
    }
}