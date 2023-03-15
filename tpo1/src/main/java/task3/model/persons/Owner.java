package task3.model.persons;

import task3.enums.Location;
import task3.enums.Position;
import task3.interfaces.IOwner;
import task3.utils.Utils;

public class Owner extends Worker implements IOwner {
    public Owner(String name, int money, Location location) {
        super(name, money, location, Position.OWNER);
    }

    @Override
    public String payMoney(Worker worker) throws NullPointerException {
        if(worker == null) throw new NullPointerException();
        String result = "";
        if(getJob() != null && getJob() == worker.getJob()) {
            int count = (int) Utils.getPercent((worker.getCountofPrinted() * getJob().getNewspaperprice()), worker.getPosition().getProcent());
            if (getMoney() >= count) {
                addMoney(-count);
                worker.addMoney(count);
                result = getName() + " выплатил " + worker.getName() + " " + count + " монет";
            } else {
                result = "Денег недостаточно! Не могу выплатить!";
            }
        } else {
            result = worker.getName() + " не работает в моей компании!";
        }
        System.out.println(result);
        return result;
    }

}
