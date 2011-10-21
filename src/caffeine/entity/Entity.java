package caffeine.entity;

import java.awt.*;
import java.util.ArrayList;

import caffeine.action.Action;
import caffeine.util.Utilities;
import caffeine.world.Direction;
import caffeine.world.Point;

public class Entity{
	protected boolean isAlive = true, isMoving = false;
	protected Brain brain;
	protected Color color = Utilities.randomColor();
	protected Direction facing = Direction.NORTH;
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected int speed = width/2;
	protected Point loc;
	protected String name;

	public Entity(){
		this(new RandomBrain(), new Point());
	}
	public Entity(Brain b){
		this(b, new Point());
	}
	public Entity(Point p){
		this(new RandomBrain(), p);
	}
	public Entity(Brain b, Point point){
		brain = b;
		id = numCharacters++;
		loc = point;
		name = "" + id;
		System.err.println("Spawning Entity " + name);
	}
	
	public void die(){
		isAlive = false;
		System.err.println(this + " has died.");
	}

	public Direction getFacing() {
		return facing;
	}
	public Point getCenter(){
		return loc.add(width/2,height/2);
	}
	public Point getLoc(){
		return loc;
	}
	public Action getNext(){
		return brain.next();
	}
	
	public int getSpeed(){
		return speed;
	}

	public ArrayList<Point> getVertices(){
		ArrayList<Point> vertices = new ArrayList<Point>();
		vertices.add(loc);
		vertices.add(new Point(loc.x+width-1, loc.y));
		vertices.add(new Point(loc.x, loc.y+height-1));
		vertices.add(new Point(loc.x+width-1, loc.y+height-1));
		return vertices;
	}
	public int getX(){
		return loc.x;
	}
	public int getY(){
		return loc.y;
	}
	public boolean hitTest(Entity other){
		for(Point p : other.getVertices()){
			if(hitTest(p))
				return true;
		}
		return false;
	}
	public boolean hitTest(Point p){
		return loc.x <= p.x && p.x < loc.x + width &&
				loc.y <= p.y && p.y < loc.y + height;
	}

	public boolean isAlive(){
		return isAlive;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setLoc(Point p){
		loc = p;
	}

	public void paint(Graphics g){
		g.setColor(color);
		g.drawOval(loc.x, loc.y, width, height);
		g.fillOval(loc.x, loc.y, width, height);
	}


	public void setColor(Color c){
		this.color = c;
	}
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void tick(){
		if(isAlive){
			Action a = brain.next();
			if (a != null){
				a.performOn(this);
			}
		}
	}

	@Override
	public String toString(){
		return name + "@" + loc.toString(); 
	}
}