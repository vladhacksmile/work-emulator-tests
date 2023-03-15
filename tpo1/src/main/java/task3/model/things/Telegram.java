package task3.model.things;

import task3.model.persons.Worker;

public class Telegram extends Thing {

    private String content;
    private Worker sender;

    public Telegram(String name, String content, Worker sender) throws NullPointerException {
        super(name);
        this.content = content;
        if(sender == null) throw new NullPointerException();
        this.sender = sender;
    }

    public String readTelegram(Worker p) {
        if(p == null) return null;
        String result = p.getName() + " прочитал телеграмму от " + sender.getName() + ": \"" + getContent() + "\"";
        System.out.println(result);
        return result;
    }

    public String getContent() {
        return content;
    }
}
