package caffeine.util;

public class Vector {
	protected Angle angle;
	double magnitude;
	
	public Vector(){
		angle = new Angle();
		magnitude = 0;
	}
	public Vector(double dir, double magnitude){
		angle = new Angle(dir);
		this.magnitude = magnitude;
	}
	
	public Vector(Angle dir, double magnitude){
		angle = dir;
		this.magnitude = magnitude;
	}
	
	public void add(Vector other){
		if(other.magnitude() > 0){
			double ax = magnitude*angle.cos();
			double ay = magnitude*angle.sin();
			double bx = other.magnitude()*other.angle().cos();
			double by = other.magnitude()*other.angle().sin();
			double rx = ax + bx;
			double ry = ay + by;
			
			System.out.printf("%f %f %f %f %f %f\n", ax, ay, bx, by, rx, ry);
			int offset = 0;
			if(rx < 0) offset = 180;
			double newAngle = Math.toDegrees(Math.atan(ry/rx))+offset;
			angle = new Angle(newAngle);
			magnitude = Util.pythagoras(rx, ry);
		}
	}
	
	public Angle angle(){return angle;}
	
	public double magnitude(){return magnitude;}
	
	public void magnitude(double m){
		magnitude = m > 0 ? m : 0;
	}
	public String toString(){
		return "("+magnitude+"-> "+angle+")";
	}
}