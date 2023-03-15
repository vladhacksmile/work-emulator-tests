package task3.model.things;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.model.persons.Human;
import task3.model.persons.Owner;
import task3.model.persons.Worker;

import static org.junit.jupiter.api.Assertions.*;

class TelegramTest {

    private Worker worker1;
    private Worker worker2;
    private Telegram telegram;
    @BeforeEach
    public void initEach() {
        worker1 = new Human("Крабс", 500, Location.STREET);
        worker2 = new Human("Спрутс", 100000, Location.NEWSPAPER);
        telegram = new Telegram("Телеграмма от Спрутса", "С ослами кончайте. Два миллиона  получите  в  банке.  Об  исполнении телеграфируйте. Спрутс", worker2);
    }

    @Test
    void readTelegram() {
        String expected = "Крабс прочитал телеграмму от Спрутс: \"С ослами кончайте. Два миллиона  получите  в  банке.  Об  исполнении телеграфируйте. Спрутс\"";
        assertEquals(expected, telegram.readTelegram(worker1));
    }

    @Test
    void readTelegramNullSender() {
        assertThrows(NullPointerException.class, () -> {
            Telegram t = new Telegram("Имя", "Содержимое", null);
        });
    }

    @Test
    void createTelegramWithNull() {
        assertNull(telegram.readTelegram(null));
    }
}