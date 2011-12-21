package caffeine.util;

import java.awt.Color;

public class Util{

	public static Color randomColor(){
		return new Color(
				(int)(Math.random()*255),
				(int)(Math.random()*255),
				(int)(Math.random()*255));
	}

	public static boolean coinFlip(){
		return Math.random() <= .5;
	}

	public static double pythagoras(double a, double b){
		return Math.sqrt(a*a + b*b);
	}

}
