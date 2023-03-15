package task3.model.companies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.persons.Owner;

import static org.junit.jupiter.api.Assertions.*;

class ClothFabricTest {
    Owner owner;
    Newspaper newspaper;
    ClothFabric clothFabric;

    @BeforeEach
    public void initEach() {
        owner = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        clothFabric = new ClothFabric("Spruts Cloth", owner, 500);
        try {
            clothFabric.addBalanceByOwner(5000);
        } catch (IncorrectValue e) {
            throw new RuntimeException(e);
        }
        newspaper = new Newspaper("Газета", owner);
    }

    @Test
    void start() throws IncorrectValue {
        assertEquals(10, clothFabric.start(10));
    }

    @Test
    void startEnough() throws IncorrectValue {
        clothFabric.setBalance(25);
        assertEquals(5, clothFabric.start(10));
    }

    @Test
    void startNegativeTask() {
        assertThrows(IncorrectValue.class, () -> clothFabric.start(-1));
    }

    @Test
    void sell() throws IncorrectValue {
        clothFabric.start(5);
        assertEquals(10, clothFabric.sell(newspaper, 10));
    }

    @Test
    void sellNullNewspaper() {
        assertThrows(NullPointerException.class, () -> clothFabric.sell(null, 10));
    }

    @Test
    void sellNegative() {
        assertThrows(IncorrectValue.class, () -> clothFabric.sell(newspaper, -1));
    }

    @Test
    void sellNotEnoughForAds() throws IncorrectValue {
        owner.setMoney(100);
        assertEquals(0, clothFabric.sell(newspaper, 10));
    }
}