package task3.model.persons;

import lombok.Getter;
import lombok.Setter;
import task3.enums.Location;
import task3.enums.Position;
import task3.exceptions.IncorrectValue;
import task3.interfaces.IWorker;
import task3.model.companies.Newspaper;
import task3.model.things.Telegram;
import task3.model.things.Thing;

import java.util.Objects;

@Getter
@Setter
public abstract class Worker implements IWorker {
    private static int countofPeople;
    private int id;
    private String name;
    private Position position;
    private Newspaper job;
    private int money;
    private Location location;
    private Location previousLocation;
    private int countofPrinted;

    public Worker(String name, int money, Location location, Position position) {
        countofPeople++;
        id = countofPeople;
        this.name = name;
        this.money = money;
        this.position = position;
        this.location = location;
        previousLocation  = location;
    }

    public String work(int count) {
        String result = "";
        if (getJob() != null) {
            if (getLocation() == Location.NEWSPAPER) {
                if(count > getPosition().getProductivity() || count < 0) {
                    result = getName() + " не может напечатать столько газет!";
                } else {
                    try {
                        count = getJob().start(count);
                    } catch (IncorrectValue e) {
                        throw new RuntimeException(e);
                    }
                    countofPrinted += count;
                    result = this.getName() + " напечатал " + count + " газет";
                }
            } else {
                result = getName() + " нет на рабочем месте!";
            }
        } else {
            result = getName() + " не указана работа!";
        }

        System.out.println(result);
        return result;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public Location setLocation(Location location) throws NullPointerException{
        if(location == null) throw new NullPointerException();
        if(this.location == location) {
            System.out.println(name + " уже на " + location);
            return null;
        }
        previousLocation = this.location;
        System.out.println(name + " сменил локацию " + previousLocation + " на " + location);
        this.location = location;
        return location;
    }

    public String readTelegram(Telegram telegram) {
        if(telegram == null) return null;
        return telegram.readTelegram(this);
    }

    public boolean callTo(Worker p, String talk) {
        if(p != null && talk != null) {
            System.out.println(name + " позвонил " + p.name + " и сказал - " + talk);
            return true;
        } else {
            return false;
        }
    }

   public String meetWith(Worker p) throws NullPointerException {
        if(p == null) throw new NullPointerException();
        String result = "";
        if(p != this) {
            if (location == p.location) {
                result = name + ", мы же в одном месте находимся!";
            } else {
                setLocation(p.location);
                result = name + " встретился с " + p.name;
            }
        } else {
            result = name + " не может встретиться с самим собой!";
        }
        System.out.println(result);
        return result;
    }

    public boolean think(String about) {
        if (about == null) return false;
        System.out.println(name + " задумался о " + about);
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != getClass())
            return false;
        if (obj == this)
            return true;
        Worker p = (Worker) obj;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Человек #" + getId() + " " + getName() + " занимает должность " + position + ", находится в " + location + " баланс " + getMoney() + " монет";
    }

}
