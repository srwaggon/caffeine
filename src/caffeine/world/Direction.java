package caffeine.world;

public class Direction {
	double angle;
	
	public Direction() {angle = 0;}
	public Direction(double theta){angle = validate(theta);}
	
	public double radians(){return Math.toRadians(angle);}
	
	public static Direction random(){return new Direction(Math.random()*360);}
	
	public double theta(){return angle;}
	
	public String toString(){return ""+angle;}
	
	public static double validate(double theta){return theta % 360;}

}
