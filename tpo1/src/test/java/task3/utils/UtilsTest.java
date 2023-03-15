package task3.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1.SinFunction;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getPercentOf1() {
        double num = 1;
        double percent = 100;
        double expected = 1;
        Assertions.assertEquals(expected, Utils.getPercent(num, percent));
    }

    @Test
    void getPercentOfNegative() {
        double num = 100;
        double percent = -1;
        Assertions.assertEquals(NaN, Utils.getPercent(num, percent));
    }
}