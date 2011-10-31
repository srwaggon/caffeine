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

}
