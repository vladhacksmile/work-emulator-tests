package task3;

import task3.enums.Location;
import task3.exceptions.IncorrectValue;
import task3.model.companies.ClothFabric;
import task3.model.companies.SugarFabric;
import task3.model.entity.Bank;
import task3.model.entity.Company;
import task3.model.companies.Newspaper;
import task3.model.persons.Editor;
import task3.model.persons.Human;
import task3.model.persons.Owner;
import task3.model.persons.TypeSetter;
import task3.model.things.Telegram;
import task3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<TypeSetter> typeSetters = new ArrayList<TypeSetter>();

    public static void main(String[] args) throws IncorrectValue {
        Human crabs = new Human("Крабс", 500, Location.STREET);
        Human miga = new Human("Мига", 100, Location.STREET);
        Human julio = new Human("Жулио", 100, Location.HOME);
        Bank bank = new Bank("Банк", 2000000);
        Owner spruts = new Owner("Спрутс", 100000, Location.NEWSPAPER);
        Newspaper newspaper = new Newspaper("Давилонские юморески", spruts);
        newspaper.setNewspaperprice(15);
        Editor editor = new Editor("Редактор Тарас", 100, Location.NEWSPAPER);
        editor.setJob(newspaper);

        int t = 10;
        for (int i = 0; i < t; i++) {
            TypeSetter ts = new TypeSetter("Наборщик текста №" + (i + 1), 100, Location.NEWSPAPER);
            ts.setJob(newspaper);
            typeSetters.add(ts);
        }

        System.out.println("~~~~~~~~~~~ Сцена 1 ~~~~~~~~~~~");
        crabs.setLocation(Location.HOTEL);
        Telegram telegram = new Telegram("Телеграмма от Спрутса", "С ослами кончайте. Два миллиона  получите  в  банке.  Об  исполнении телеграфируйте. Спрутс", spruts);
        crabs.readTelegram(telegram);
        crabs.callTo(miga, "Приезжай ко мне!");
        crabs.callTo(julio, "Приезжай ко мне!");
        miga.meetWith(crabs);
        julio.meetWith(crabs);
        crabs.think("Может съездить в " + bank + "? А хотя, лучше сначала в редакцию газеты съезжу!");
        crabs.setLocation(Location.NEWSPAPER);

        System.out.println("~~~~~~~~~~~ Сцена 2 ~~~~~~~~~~~");
        System.out.println(newspaper);

        newspaper.setBalance(5000);

        editor.work(Utils.getRandom(1, editor.getPosition().getProductivity()));

        for(int i = 0; i < t; i++) {
            typeSetters.get(i).work(Utils.getRandom(1, typeSetters.get(i).getPosition().getProductivity()));
        }

        System.out.println(spruts);
        newspaper.sell(Utils.getRandom(1, newspaper.getNewspapercount()));
        newspaper.grab();
        System.out.println(spruts);
        spruts.payMoney(editor);

        for (int i = 0; i < t; i++) {
            spruts.payMoney(typeSetters.get(i));
        }

        System.out.println(spruts);

        System.out.println("~~~~~~~~~~~ Сцена 3 ~~~~~~~~~~~");
        spruts.setLocation(Location.COMPANY);

        SugarFabric sugarFabric = new SugarFabric("Spruts Sugar", spruts, 100);
        sugarFabric.addBalanceByOwner(5000);
        ClothFabric clothFabric = new ClothFabric("Spruts Cloth", spruts, 500);
        clothFabric.addBalanceByOwner(5000);
        sugarFabric.start(Utils.getRandom(1000));
        System.out.println(spruts);
        clothFabric.start(Utils.getRandom(500));
        System.out.println(spruts);
        sugarFabric.sell(newspaper, Utils.getRandom(1000));
        System.out.println(spruts);
        clothFabric.sell(newspaper, Utils.getRandom(500));
        System.out.println(spruts);
        sugarFabric.grab();
        System.out.println(spruts);
        clothFabric.grab();
        System.out.println(spruts);
    }
}
