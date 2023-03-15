package task3.interfaces;

import task3.exceptions.IncorrectValue;
import task3.model.companies.Newspaper;

public interface IFabric {
    int start(int count) throws IncorrectValue;
    int sell(Newspaper newspaper, int count) throws IncorrectValue;
}
