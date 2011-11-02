package caffeine.util;

public class Vector {
	protected Angle angle;
	double magnitude;
	
	public Vector(){
		angle = new Angle();
		magnitude = 0;
	}
	public Vector(Angle dir, double magnitude){
		angle = dir;
		this.magnitude = magnitude;
	}
	
	public void add(Vector other){
		double ax = magnitude*angle.cos();
		double ay = magnitude*angle.sin();
		double bx = other.magnitude()*other.angle().cos();
		double by = other.magnitude()*other.angle().sin();
		double rx = ax + bx;
		double ry = ay + by;
		int offset = 0;
		if(rx < 0) offset = 180;
		double newAngle = Math.toDegrees(Math.atan(ry/rx))+offset;
		angle = new Angle(newAngle);
		magnitude = Util.pythagoras(rx, ry);
	}
	
	public Angle angle(){return angle;}
	
	public double magnitude(){return magnitude;}
	
	public String toString(){
		return "("+magnitude+"->"+angle+")";
	}
}