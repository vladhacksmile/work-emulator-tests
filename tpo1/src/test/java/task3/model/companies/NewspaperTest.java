package task3.model.companies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.persons.Owner;

import static org.junit.jupiter.api.Assertions.*;

class NewspaperTest {
    Owner owner;
    Newspaper newspaper;

    @BeforeEach
    public void initEach() {
        owner = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        newspaper = new Newspaper("Газета", owner);
        newspaper.setNewspaperprice(10);
        try {
            newspaper.addBalanceByOwner(1000);
        } catch (IncorrectValue e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void start() throws IncorrectValue {
        assertEquals(10, newspaper.start(10));
        assertEquals(950, newspaper.getBalance());
    }

    @Test
    void startNotEnough() throws IncorrectValue {
        newspaper.setBalance(0);
        assertEquals(0, newspaper.start(10));
        assertEquals(0, newspaper.getNewspapercount());
    }

    @Test
    void startNegative() {
        assertThrows(IncorrectValue.class, () -> newspaper.start(-1));
    }

    @Test
    void sell() throws IncorrectValue {
        newspaper.start(10);
        assertEquals(10, newspaper.sell(10));
    }

    @Test
    void sellZero() throws IncorrectValue {
        assertEquals(0, newspaper.sell(10));
    }

    @Test
    void sellNegative() {
        assertThrows(IncorrectValue.class, () -> newspaper.sell(-1));
    }
}