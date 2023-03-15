package task3.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.companies.ClothFabric;
import task3.model.companies.Newspaper;
import task3.model.persons.Owner;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
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
    void addBalanceByOwner() throws IncorrectValue {
        newspaper.addBalanceByOwner(10000);
        assertEquals(10000, newspaper.getBalance());
        assertEquals(85000, owner.getMoney());
    }

    @Test
    void addBalanceByOwnerNegativeBalance() {
        assertThrows(IncorrectValue.class, () -> newspaper.addBalanceByOwner(-10000));
        assertEquals(0, newspaper.getBalance());
        assertEquals(95000, owner.getMoney());
    }

    @Test
    void grab() {
        newspaper.setBalance(10000);
        newspaper.grab();
        assertEquals(105000, owner.getMoney());
        assertEquals(0, newspaper.getBalance());
    }

    @Test
    void ads() throws IncorrectValue {
        assertEquals(1000, Company.ads(owner, clothFabric, newspaper, 1000, 1000));
        assertEquals(94000, owner.getMoney());
        assertEquals(1000, newspaper.getBalance());
    }

    @Test
    void adsNull() {
       assertThrows(NullPointerException.class, () -> Company.ads(null, clothFabric, newspaper, 1000, 1000));
       assertThrows(NullPointerException.class, () -> Company.ads(owner, null, newspaper, 1000, 1000));
       assertThrows(NullPointerException.class, () -> Company.ads(owner, clothFabric, null, 1000, 1000));
    }

    @Test
    void adsNegativeNumbers() {
        assertThrows(IncorrectValue.class, () -> Company.ads(owner, clothFabric, newspaper, -1, 1000));
        assertThrows(IncorrectValue.class, () -> Company.ads(owner, clothFabric, newspaper, 1000, -1));
    }

    @Test
    void adsNotEnoughMoney() throws IncorrectValue {
        assertEquals(0, Company.ads(owner, clothFabric, newspaper, 1000000, 1000));
    }

    @Test
    void addCash() {
        newspaper.addCash(10000);
        assertEquals(10000, newspaper.getBalance());
    }
}