package caffeine.world;

public class Point {
	static final Point Origin = new Point();
	
	public int x, y;
	
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point project(Direction direction, int speed){
		int dx = 0;
		int dy = 0;
		switch(direction){
		case WEST:
			dx = -1; break;
		case NORTH:
			dy = -1; break; 
		case EAST:
			dx = 1; break;
		case SOUTH:
			dy = 1; break;
		default:
			break;
		}
		dx *= speed;
		dy *= speed;
		return new Point(x + dx, y + dy);
	}
	public double distanceTo(Point other){
		return Math.sqrt((other.x - x)^2 + (other.y - y)^2);
	}
	public String toString(){
		return "(" + x + "," + y + ")";
	}
}
