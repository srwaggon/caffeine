package caffeine.util;

public class Angle {
	double theta;

	public Angle() {theta = 0;}
	public Angle(double angle){theta = validate(angle);}

	public double radians(){
		return Math.toRadians(theta);}

	public static Angle random(){return new Angle(Math.random()*360);}

	public double sin(){return Math.sin(radians());}
	public double cos(){return Math.cos(radians());}
	public double tan(){return Math.tan(radians());}

	public double theta(){return theta;}

	public String toString(){return ""+theta+"deg";}

	public static double validate(double theta){return theta % 360;}
	public void add(int i){theta += i;}

}
