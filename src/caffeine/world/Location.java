package caffeine.world;

public class Location {
	protected int mapID;
	protected int x;
	protected int y;
	
	public Location(){
		mapID = 0;
		x = 0;
		y = 0;
	}
	
	public Location(int mapID, int x, int y){
		this.mapID = mapID;
		this.x = x;
		this.y = y;
	}
	
	
	public int getMapID(){
		return mapID;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setMapID(int n){
		mapID = n;
	}
	
	public void setX(int n){
		x = n;
	}
	
	public void setY(int n){
		y = n;
	}
	
	public Location project(Direction direction, int speed){
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
		return new Location(mapID, x + dx, y + dy);
	}
	public String toString(){
		return "[" + mapID + "(" + getX() + "," + getY() + ")]";
	}
	
}
