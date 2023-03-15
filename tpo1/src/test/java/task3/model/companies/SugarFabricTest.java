package task3.model.companies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.persons.Owner;

import static org.junit.jupiter.api.Assertions.*;

class SugarFabricTest {
    Owner owner;
    Newspaper newspaper;
    SugarFabric sugarFabric;

    @BeforeEach
    public void initEach() {
        owner = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        sugarFabric = new SugarFabric("Spruts Sugar", owner, 500);
        try {
            sugarFabric.addBalanceByOwner(5000);
        } catch (IncorrectValue e) {
            throw new RuntimeException(e);
        }
        newspaper = new Newspaper("Газета", owner);
    }

    @Test
    void start() throws IncorrectValue {
        assertEquals(10, sugarFabric.start(10));
    }

    @Test
    void startEnough() throws IncorrectValue {
        sugarFabric.setBalance(25);
        assertEquals(5, sugarFabric.start(10));
    }

    @Test
    void startNegativeTask() {
        assertThrows(IncorrectValue.class, () -> sugarFabric.start(-1));
    }

    @Test
    void sell() throws IncorrectValue {
        sugarFabric.start(5);
        assertEquals(10, sugarFabric.sell(newspaper, 10));
    }

    @Test
    void sellNullNewspaper() {
        assertThrows(NullPointerException.class, () -> sugarFabric.sell(null, 10));
    }

    @Test
    void sellNegative() {
        assertThrows(IncorrectValue.class, () -> sugarFabric.sell(newspaper, -1));
    }

    @Test
    void sellNotEnoughForAds() throws IncorrectValue {
        owner.setMoney(100);
        assertEquals(0, sugarFabric.sell(newspaper, 10));
    }
}