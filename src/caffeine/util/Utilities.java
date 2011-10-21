package caffeine.util;

import java.awt.Color;

public class Utilities{
	
	public static Color randomColor(){
		return new Color(
				(int)(Math.random()*255),
				(int)(Math.random()*255),
				(int)(Math.random()*255));
	}

}
