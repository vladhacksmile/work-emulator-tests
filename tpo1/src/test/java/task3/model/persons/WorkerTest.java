package task3.model.persons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.enums.Location;
import task3.enums.Position;
import task3.model.companies.Newspaper;
import task3.model.things.Telegram;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    TypeSetter worker;
    Owner owner;
    Newspaper newspaper;
    Telegram telegram;

    @BeforeEach
    public void initEach() {
        worker = new TypeSetter("Крабс", 100, Location.NEWSPAPER);
        owner = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        newspaper = new Newspaper("Газета", owner);
        worker.setJob(newspaper);
        telegram = new Telegram("Телеграмма от Спрутса", "С ослами кончайте. Два миллиона  получите  в  банке.  Об  исполнении телеграфируйте. Спрутс", owner);
    }

    @Test
    void work() {
        assertEquals("Крабс напечатал 5 газет", worker.work(5));
    }

    @Test
    void workWrongLocation() {
        worker.setLocation(Location.HOME);
        assertEquals("Крабс нет на рабочем месте!", worker.work(5));
    }

    @Test
    void workNullJob() {
        worker.setJob(null);
        assertEquals("Крабс не указана работа!", worker.work(5));
    }

    @Test
    void workIncorrectNewspaperCount() {
        assertEquals("Крабс не может напечатать столько газет!", worker.work(-5));
        assertEquals("Крабс не может напечатать столько газет!", worker.work(1000));
    }

    @Test
    void setLocation() {
        assertNull(worker.setLocation(Location.NEWSPAPER));
        assertEquals(Location.HOME, worker.setLocation(Location.HOME));
    }

    @Test
    void setLocationNull() {
        assertThrows(NullPointerException.class, () -> worker.setLocation(null));
    }


    @Test
    void readTelegram() {
        String expected = "Крабс прочитал телеграмму от Спрутс: \"С ослами кончайте. Два миллиона  получите  в  банке.  Об  исполнении телеграфируйте. Спрутс\"";
        assertEquals(expected, worker.readTelegram(telegram));
    }

    @Test
    void readTelegramNull() {
        assertNull(worker.readTelegram(null));
    }

    @Test
    void callTo() {
        assertTrue(worker.callTo(owner, "привет"));
    }

    @Test
    void callToNull() {
        assertFalse(worker.callTo(null, ""));
        assertFalse(worker.callTo(owner, null));
    }


    @Test
    void meetWith() {
        owner.setLocation(Location.OFFICE);
        assertEquals("Крабс встретился с Спрутс", worker.meetWith(owner));
    }

    @Test
    void meetWithInOneLocation() {
        assertEquals("Крабс, мы же в одном месте находимся!", worker.meetWith(owner));
    }
    @Test
    void meetWithSelf() {
        assertEquals("Крабс не может встретиться с самим собой!", worker.meetWith(worker));
    }

    @Test
    void meetWithNull() {
        assertThrows(NullPointerException.class, () -> worker.meetWith(null));
    }

    @Test
    void think() {
        assertTrue(worker.think("Как поднять бабла?"));
    }

    @Test
    void thinkNull() {
        assertFalse(worker.think(null));
    }
}