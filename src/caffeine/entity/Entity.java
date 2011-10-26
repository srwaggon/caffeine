package caffeine.entity;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import caffeine.Game;
import caffeine.action.Action;
import caffeine.rule.Rule;
import caffeine.util.Utilities;
import caffeine.view.Sprite;
import caffeine.view.Sprited;
import caffeine.world.Direction;
import caffeine.world.Location;
import caffeine.world.Point;
import caffeine.world.Tile;

public class Entity implements Sprited{
	protected boolean isAlive = true, isMoving = false;
	protected Brain brain;
	protected Color color = Utilities.randomColor();
	protected Direction facing = Direction.NORTH;
	protected static int numCharacters = 0;
	protected int id = 0;
	protected int height = 32;
	protected int width = 32;
	protected int speed = width/2;
	protected Location loc;
	protected String name;

	public Entity(){
		this(new Location());
	}
	public Entity(Location loc){
		brain = new Brain();
		id = numCharacters++;
		this.loc = loc;
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
	public Location getCenter(){
		return new Location(
				loc.getMapID(),
				loc.getX() + width/2,
				loc.getY() + height/2);
	}
	public Location getLoc(){
		return loc;
	}
	public Action next(){
		return brain.next(this);
	}
	
	public int getSpeed(){
		return speed;
	}
	public Tile getTile(){
		return Game.getInstance().getWorld().get(loc.getMapID()).getTileAt(loc.getX(), loc.getY());
	}

	// TODO revise.  strongly.
	public ArrayList<Point> getBounds(){
		ArrayList<Point> vertices = new ArrayList<Point>();
		int x = loc.getX();
		int y = loc.getY();
		vertices.add(new Point(x, y));
		vertices.add(new Point(x+width-1, y));
		vertices.add(new Point(x, y+height-1));
		vertices.add(new Point(x+width-1, y+height-1));
		return vertices;
	}
	
	public int getX(){
		return loc.getX();
	}
	public int getY(){
		return loc.getY();
	}
	/* TODO
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
	*/

	public boolean isAlive(){
		return isAlive;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setLoc(Location loc){
		this.loc = loc;
	}

	public Sprite getSprite(){
		return new Sprite(color, new RoundRectangle2D.Double(loc.getX(), loc.getY(), width, height, 15, 15));
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
		for(Rule r : Game.getInstance().getRules()){
			if(r.appliesTo(this))
				r.applyOn(this);
		}
		if(isAlive){
			next().performOn(this);
		}
	}

	@Override
	public String toString(){
		return name + "@" + loc.toString(); 
	}
}