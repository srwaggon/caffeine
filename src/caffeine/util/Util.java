package caffeine.util;

import java.awt.Color;

public class Util{

	public static Color randomColor(){
		return new Color(
				(int)(Math.random()*255),
				(int)(Math.random()*255),
				(int)(Math.random()*255));
	}

	public static boolean coinflip(){
		return Math.random() < .5;
	}

  public static boolean oneIn(int chances) {
    return Math.random() < 1.0 / chances;
  }

	public static double pythagoras(double a, double b){
		return Math.sqrt(a*a + b*b);
	}

  public static int signOf(double num) {
    return (int) (num == 0.0 ? 0.0 : num / Math.abs(num));
  }

}
