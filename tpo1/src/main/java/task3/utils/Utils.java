package task3.utils;

import java.util.Random;

import static java.lang.Float.NaN;

public class Utils {

  public static int getRandom(int a, int b) {
      return a + (int) (Math.random() * b);
  }

  public static int getRandom(int n) {
      Random random = new Random();
      return random.nextInt(n);
  }

  public static double getPercent(double num, double percent) {
      if(percent < 0) return NaN;
      return (num * percent) / 100;
  }
}
